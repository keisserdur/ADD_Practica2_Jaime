package com.example.admin.add_practica2_jaime.Gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.admin.add_practica2_jaime.Ayudantes.Ayudante;
import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.POJO.RecetaIngrediente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18/11/2015.
 */
public class GestorRecetaIngrediente {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorRecetaIngrediente(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void close() {
        abd.close();
    }

    //CRUD-Create-Read-Update-Delete
    public long insert (RecetaIngrediente ri){
        ContentValues valores=new ContentValues();
        valores.put(Contrato.TablaRecetarioIngredientes.INGREDIENTE,ri.getIngrediente());
        valores.put(Contrato.TablaRecetarioIngredientes.CANTIDAD,ri.getCantidad());
        valores.put(Contrato.TablaRecetarioIngredientes.RECETA, ri.getReceta());

        long id =bd.insert(Contrato.TablaRecetarioIngredientes.TABLA,null,valores);
        return id;
    }
    public Cursor query(){
        return query(null,null);
    }
    public Cursor query(String condicion,String[] params){
        Cursor c=bd.query(Contrato.TablaRecetarioIngredientes.TABLA, null, condicion, params, null, null, null);
        return c;
    }
    public int update(RecetaIngrediente ri){
        ContentValues valores=new ContentValues();
        valores.put(Contrato.TablaRecetarioIngredientes.CANTIDAD,ri.getCantidad());
        int result=bd.update(Contrato.TablaRecetarioIngredientes.TABLA, valores, Contrato.TablaRecetarioIngredientes._ID + "=?", new String[]{ri.getId() + ""});
        return result;
    }
    public int delete(long id){
        int result=bd.delete(Contrato.TablaRecetarioIngredientes.TABLA, Contrato.TablaRecetarioIngredientes._ID + "=?", new String[]{id + ""});
        return result;
    }
    public int delete(RecetaIngrediente ri){
        return delete(ri.getId());
    }

    public List<RecetaIngrediente> select(){
        return select(null,null);
    }
    public List<RecetaIngrediente> select(String condicion,String[] params){
        List<RecetaIngrediente> l=new ArrayList<RecetaIngrediente>();
        Cursor c=query(condicion,params);
        while(c.moveToNext()) {
            l.add(getRow(c));
        }
        return l;
    }
    public RecetaIngrediente getRow (Cursor c){
        RecetaIngrediente ri=new RecetaIngrediente();
        ri.setId(c.getLong(c.getColumnIndex(Contrato.TablaRecetarioIngredientes._ID)));
        ri.setIngrediente(c.getLong(c.getColumnIndex(Contrato.TablaRecetarioIngredientes.INGREDIENTE)));
        ri.setCantidad(c.getLong(c.getColumnIndex(Contrato.TablaRecetarioIngredientes.CANTIDAD)));
        ri.setReceta(c.getLong(c.getColumnIndex(Contrato.TablaRecetarioIngredientes.RECETA)));
        return ri;
    }
}
