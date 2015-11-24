package com.example.admin.add_practica2_jaime.Editar;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.Gestores.GestorIngrediente;
import com.example.admin.add_practica2_jaime.POJO.Ingrediente;
import com.example.admin.add_practica2_jaime.R;

public class EditarIngrediente extends Activity {

    private Ingrediente i;
    private GestorIngrediente gi;
    private EditText et;
    private long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ingrediente);
        Bundle b=getIntent().getExtras();
        et= (EditText) findViewById(R.id.textView12);
        id=b.getLong("IdCursor");

        gi=new GestorIngrediente(this);
        gi.open();
        Cursor c=gi.query(Contrato.TablaIngredientes._ID+"=?",new String[]{""+id});
        c.moveToFirst();
        et.setText(c.getString(c.getColumnIndex(Contrato.TablaIngredientes.NOMBRE)));
    }
    public void volver(View v){
        gi.close();
        finish();
    }
    public void cambiar(View v){
        i=new Ingrediente();
        i.setId(id);
        i.setNombre(et.getText().toString());
        gi.update(i);
        gi.close();
        finish();
    }

}
