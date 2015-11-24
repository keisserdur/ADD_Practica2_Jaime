package com.example.admin.add_practica2_jaime.Interfaz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.Gestores.GestorIngrediente;
import com.example.admin.add_practica2_jaime.Gestores.GestorReceta;
import com.example.admin.add_practica2_jaime.Gestores.GestorRecetaIngrediente;
import com.example.admin.add_practica2_jaime.POJO.Ingrediente;
import com.example.admin.add_practica2_jaime.POJO.Receta;
import com.example.admin.add_practica2_jaime.POJO.RecetaIngrediente;
import com.example.admin.add_practica2_jaime.R;

import java.util.ArrayList;
import java.util.List;

public class AddReceta extends Activity {

    private GestorReceta gr;
    private GestorIngrediente gi;
    private GestorRecetaIngrediente gri;
    private final int INGREDIENTES=1;
    private EditText etNombre,etInstrucciones;
    private TextView etIngredientes;
    private Receta r;
    private Ingrediente ingredientes;
    private List<Ingrediente> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_receta);
        gr=new GestorReceta(this);
        gi=new GestorIngrediente(this);
        gri=new GestorRecetaIngrediente(this);
        lista=new ArrayList<Ingrediente>();
    }

    @Override
    protected void onResume() {
        ini();
        super.onResume();
    }



    @Override
    protected void onPause() {
        super.onPause();
    }
    private void ini() {
        etNombre= (EditText) findViewById(R.id.editText);
        etInstrucciones= (EditText) findViewById(R.id.editText2);
        etIngredientes= (TextView) findViewById(R.id.textView11);
    }

    public void addReceta (View v){
        r=new Receta();
        r.setNombre(etNombre.getText().toString());
        r.setInstruccion(etInstrucciones.getText().toString());

        gr.open();
        long idr=gr.insert(r);
        gr.close();

        RecetaIngrediente ri;

        for(Ingrediente ingre: lista) {
            ri = new RecetaIngrediente();
            ri.setReceta(idr);
            ri.setIngrediente(ingre.getId());
            ri.setCantidad(1);
            Log.v("ZXCV","añade receta-ingrediente:"+ri.toString());
            gri.open();
            gri.insert(ri);
            gri.close();
        }
        setResult(RESULT_OK);
        finish();
    }
    public void addIngredientes (View v){
        Intent i=new Intent(this,VerIngrediente.class);
        startActivityForResult(i, INGREDIENTES);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK && requestCode==INGREDIENTES) {
            Bundle b = data.getExtras();
            String ingrediente = b.getString("elemento");

            gi.open();

            Cursor c = gi.query(Contrato.TablaIngredientes.NOMBRE + "=?",
                    new String[]{"" + ingrediente});
            c.moveToFirst();

            ingredientes=new Ingrediente();
            ingredientes.setNombre(c.getString(c.getColumnIndex(Contrato.TablaIngredientes.NOMBRE)));
            ingredientes.setId(c.getLong(c.getColumnIndex(Contrato.TablaIngredientes._ID)));

            gi.close();

            lista.add(ingredientes);
            etIngredientes.append("\n" + ingredientes.getNombre());

        }
    }
}
