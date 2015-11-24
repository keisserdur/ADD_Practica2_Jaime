package com.example.admin.add_practica2_jaime.Gestores;

import android.content.Context;
import android.database.Cursor;
import com.example.admin.add_practica2_jaime.Ayudantes.Ayudante;
import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.POJO.Ingrediente;
import com.example.admin.add_practica2_jaime.POJO.Receta;
import com.example.admin.add_practica2_jaime.POJO.RecetaIngrediente;


/**
 * Created by Admin on 19/11/2015.
 */
public class GestorALL {
    private Ayudante abd;
    private long id_r,id_i,id_ri;
    private GestorReceta gr;
    private GestorIngrediente gi;
    private GestorRecetaIngrediente gri;

    public GestorALL (Context c){
        abd = new Ayudante(c);
        gr=new GestorReceta(c);
        gi=new GestorIngrediente(c);
        gri=new GestorRecetaIngrediente(c);
    }

    //CRUD Create-Read-Update-Delete

    //Insertar una nueva receta, con ingredientes
    public void Receta(Receta r){
        gr.open();
        id_r=gr.insert(r);
        gr.close();
    }
    public void Ingrediente4Receta(Ingrediente i,long cantidad){
        gi.open();
        id_i=gi.insert(i);
        RecetaIngrediente(cantidad);
        gi.close();
    }
    private void RecetaIngrediente (long cantidad){
        gri.open();
        gri.insert(new RecetaIngrediente(0, id_r, id_i, cantidad));
        gri.close();
    }

    //Insertar ingrediente
    public void Ingrediente(Ingrediente i){
        gi.open();
        gi.insert(i);
        gri.close();
    }

    //Insertar ingrediente ya existente a una receta
    public void Ingrediente2Receta(Receta r,Ingrediente i,long cantidad){
        gri.open();
        RecetaIngrediente ri =new RecetaIngrediente(0,r.getId(),i.getId(),cantidad);
        gri.insert(ri);
        gri.close();
    }

    //Los Read en los gestores propios de cada clase
    //Los Update en los gestores propios de cada clase

    //Borra una receta y toda las relaciones de ingredientes
    public void delete(long RecetaID){
        Receta r=new Receta();
        r.setId(RecetaID);
        delete(r);
    }

    public void delete(Receta r){
        gr.open();
        gri.open();

        Cursor c=gri.query(Contrato.TablaRecetarioIngredientes.RECETA+"=?",new String[]{r.getId()+""});
        while(c.moveToNext()){
            id_ri=c.getLong(c.getColumnIndex(Contrato.TablaRecetarioIngredientes._ID));
            gri.delete(id_ri);
        }
        gr.delete(r);

        gr.close();
        gri.close();
    }

}
