package com.example.admin.add_practica2_jaime.Gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.admin.add_practica2_jaime.Ayudantes.Ayudante;
import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.POJO.Ingrediente;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18/11/2015.
 */
public class GestorIngrediente {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorIngrediente(Context c) {
        abd = new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void close() {
        abd.close();
    }

    //CRUD-Create-Read-Update-Delete
    public long insert (Ingrediente i){
        ContentValues valores=new ContentValues();
        valores.put(Contrato.TablaIngredientes.NOMBRE,i.getNombre());
        long id =bd.insert(Contrato.TablaIngredientes.TABLA,null,valores);
        return id;
    }
    public Cursor query(){
        return query(null,null);
    }
    public Cursor query(String condicion,String[] params){
        Cursor c=bd.query(Contrato.TablaIngredientes.TABLA, null, condicion, params, null, null, Contrato.TablaIngredientes.NOMBRE);
        return c;
    }
    public int update(Ingrediente i){
        ContentValues valores=new ContentValues();
        valores.put(Contrato.TablaIngredientes.NOMBRE,i.getNombre());
        int result=bd.update(Contrato.TablaIngredientes.TABLA, valores, Contrato.TablaIngredientes._ID + "=?", new String[]{i.getId() + ""});
        return result;
    }
    public int delete(long id){
        int result=bd.delete(Contrato.TablaIngredientes.TABLA, Contrato.TablaIngredientes._ID + "=?", new String[]{id + ""});
        return result;
    }
    public int delete(Ingrediente i){
        return delete(i.getId());
    }

    public List<Ingrediente> select(){
        return select(null,null);
    }
    public List<Ingrediente> select(String condicion,String[] params){
        List<Ingrediente> l=new ArrayList<Ingrediente>();
        Cursor c=query(condicion,params);
        while(c.moveToNext()) {
            l.add(getRow(c));
        }
        return l;
    }
    public Ingrediente getRow (Cursor c){
        Ingrediente ri=new Ingrediente();
        ri.setId(c.getLong(c.getColumnIndex(Contrato.TablaIngredientes._ID)));
        ri.setNombre(c.getString(c.getColumnIndex(Contrato.TablaIngredientes.NOMBRE)));
        return ri;
    }
}
