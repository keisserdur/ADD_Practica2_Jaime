package com.example.admin.add_practica2_jaime.Editar;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.admin.add_practica2_jaime.Adapters.AdapterRecetas;
import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.Gestores.GestorReceta;
import com.example.admin.add_practica2_jaime.POJO.Receta;
import com.example.admin.add_practica2_jaime.R;

public class Editar extends Activity {
    private EditText et1,et2;
    private GestorReceta gr;
    private Long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        Bundle b=getIntent().getExtras();
        id=b.getLong("IdCursor");
        gr=new GestorReceta(this);
    }
    @Override
    protected void onResume() {
        gr.open();
        if(id!=-1)
            ini();
        super.onResume();
    }

    @Override
    protected void onPause() {
        gr.close();
        super.onPause();
    }

    private void ini(){
        et1= (EditText) findViewById(R.id.editText3);
        et2= (EditText) findViewById(R.id.editText5);
        Cursor r=gr.query(Contrato.TablaReceta._ID+"=?",new String[]{""+id});
        r.moveToFirst();
        et1.setText(r.getString(r.getColumnIndex(Contrato.TablaReceta.NOMBRE)));
        et2.setText(r.getString(r.getColumnIndex(Contrato.TablaReceta.INSTRUCION)));
    }
    /***************************************************************************************/
    public void volver(View v){
        finish();
    }
    public void aceptar(View v){
        Receta r=new Receta();
        r.setId(id);
        r.setNombre(et1.getText().toString());
        r.setInstruccion(et2.getText().toString());
        gr.update(r);
        setResult(RESULT_OK);
        finish();
    }
}
