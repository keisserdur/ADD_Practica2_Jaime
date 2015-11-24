package com.example.admin.add_practica2_jaime.Interfaz;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.Gestores.GestorIngrediente;
import com.example.admin.add_practica2_jaime.Gestores.GestorReceta;
import com.example.admin.add_practica2_jaime.Gestores.GestorRecetaIngrediente;
import com.example.admin.add_practica2_jaime.POJO.Ingrediente;
import com.example.admin.add_practica2_jaime.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VerReceta extends Activity {
    private long id;
    private GestorReceta gr;
    private GestorIngrediente gi;
    private GestorRecetaIngrediente gri;
    private TextView tvNombre,tvInstrucciones,tvIngredientes;
    private ImageView iv;
    private List<Ingrediente> li;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_receta);
        Intent i=getIntent();
        Bundle b=i.getExtras();
        id=b.getLong("IdCursor");
        li=new ArrayList<Ingrediente>();

        gr=new GestorReceta(this);
        gi=new GestorIngrediente(this);
        gri=new GestorRecetaIngrediente(this);
    }

    @Override
    protected void onResume() {
        gr.open();
        gi.open();
        gri.open();
        if(id!=-1)
            ini();
        super.onResume();
    }

    @Override
    protected void onPause() {
        gr.close();
        gi.close();
        gri.close();
        super.onPause();
    }

    private void ini() {

        iv= (ImageView) findViewById(R.id.imageView3);
        tvNombre= (TextView) findViewById(R.id.textView4);
        tvInstrucciones= (TextView) findViewById(R.id.textView6);
        tvIngredientes= (TextView) findViewById(R.id.textView8);


        Cursor r=gr.query(Contrato.TablaReceta._ID+"=?",new String[]{""+id});


        Cursor ri=gri.query(Contrato.TablaRecetarioIngredientes.RECETA+"=?",new String[]{""+id});

        Cursor i;
        ri.moveToFirst();
        Ingrediente ingre;
        while(ri.moveToNext()) {
            int idi = ri.getInt(ri.getColumnIndex(Contrato.TablaRecetarioIngredientes.INGREDIENTE));
            i = gi.query(Contrato.TablaIngredientes._ID + "=?",
                    new String[]{"" + idi});
            i.moveToFirst();
            ingre=new Ingrediente();
            ingre.setNombre(i.getString(i.getColumnIndex(Contrato.TablaIngredientes.NOMBRE)));
            li.add(ingre);
        }

        r.moveToFirst();
        ri.moveToFirst();

        if(r.getString(r.getColumnIndex(Contrato.TablaReceta.FOTO))!=null){
            File photo=new File(r.getString(r.getColumnIndex(Contrato.TablaReceta.FOTO)));
            Uri uri=Uri.parse(photo.toString());
            iv.setImageURI(uri);
        }else {
            iv.setBackgroundResource(R.drawable.pordefecto);
        }

        tvNombre.setText(r.getString(r.getColumnIndex(Contrato.TablaReceta.NOMBRE)));
        tvInstrucciones.setText(r.getString(r.getColumnIndex(Contrato.TablaReceta.INSTRUCION)));

        for(Ingrediente in:li){
            tvIngredientes.append("\n"+in.getNombre());
        }

    }

}
