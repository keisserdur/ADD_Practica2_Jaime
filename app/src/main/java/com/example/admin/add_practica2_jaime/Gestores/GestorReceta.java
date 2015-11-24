package com.example.admin.add_practica2_jaime.Gestores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.admin.add_practica2_jaime.Ayudantes.Ayudante;
import com.example.admin.add_practica2_jaime.Contratos.Contrato;
import com.example.admin.add_practica2_jaime.POJO.Receta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 18/11/2015.
 */
public class GestorReceta {
    private Ayudante abd;
    private SQLiteDatabase bd;

    public GestorReceta (Context c){
        abd=new Ayudante(c);
    }
    public void open() {
        bd = abd.getWritableDatabase();
    }
    public void close() {
        abd.close();
    }

    //CRUD
    public Cursor query(){
        return query(null,null);
    }
    public Cursor query(String condicion,String[] params){
        Cursor c=bd.query(Contrato.TablaReceta.TABLA,null,condicion,params,null,null,Contrato.TablaReceta.NOMBRE);
        return c;
    }
    public long insert(Receta r){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaReceta.NOMBRE,r.getNombre());
        valores.put(Contrato.TablaReceta.FOTO,r.getFoto());
        valores.put(Contrato.TablaReceta.INSTRUCION,r.getInstruccion());

        long id= bd.insert(Contrato.TablaReceta.TABLA, null, valores);
        return id;
    }
    public int delete(long id){
        int estado= bd.delete(Contrato.TablaReceta.TABLA, Contrato.TablaReceta._ID + "=?", new String[]{id + ""});
        return estado;
    }
    public int delete(Receta r){
        return delete(r.getId());
    }
    public int update(Receta r){
        ContentValues valores = new ContentValues();
        valores.put(Contrato.TablaReceta.NOMBRE,r.getNombre());
        valores.put(Contrato.TablaReceta.FOTO,r.getFoto());
        valores.put(Contrato.TablaReceta.INSTRUCION, r.getInstruccion());

        int actualizaciones=bd.update(Contrato.TablaReceta.TABLA,valores,Contrato.TablaReceta._ID+"=?",new String[]{r.getId()+""});
        return actualizaciones;
    }

    public List<Receta> select(){
        return select(null);
    }
    public List<Receta> select(String condicion){
        return select(condicion,null);
    }
    public List<Receta> select(String condicion,String[] params){
        Log.v("XAPP", "Va a generar la lista");
        Cursor c=bd.query(Contrato.TablaReceta.TABLA, null, condicion, params, null, null, null);
        Receta r;
        List<Receta> list=new ArrayList<Receta>();
        Log.v("XAPP","Va a entrar en el while");
        while (c.moveToNext()) {
            r = getRow(c);
            list.add(r);
        }
        c.close();
        return list;
    }

    public Receta getRow(Cursor c){
        Receta r=new Receta();
        r.setNombre(c.getString(c.getColumnIndex(Contrato.TablaReceta.NOMBRE)));
        r.setFoto(c.getString(c.getColumnIndex(Contrato.TablaReceta.FOTO)));
        r.setInstruccion(c.getString(c.getColumnIndex(Contrato.TablaReceta.INSTRUCION)));
        return r;
    }
}
