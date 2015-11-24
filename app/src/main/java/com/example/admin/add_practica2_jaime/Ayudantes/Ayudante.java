package com.example.admin.add_practica2_jaime.Ayudantes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.admin.add_practica2_jaime.Contratos.Contrato;

/**
 * Created by Admin on 18/11/2015.
 */
public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="receta.sqlite";
    public static final int DATABASE_VERSION=1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        Log.v("XAPP","Va a crear la tabla receta");
        sql = "create table " + Contrato.TablaReceta.TABLA + " (" +
                Contrato.TablaReceta._ID +" integer primary key autoincrement, " +
                Contrato.TablaReceta.NOMBRE + " text, " +
                Contrato.TablaReceta.FOTO + " text, " +
                Contrato.TablaReceta.INSTRUCION + " text)";
        db.execSQL(sql);

        Log.v("XAPP", "Va a crear la tabla ingrediente");
        sql = "create table " + Contrato.TablaIngredientes.TABLA + " (" +
                Contrato.TablaIngredientes._ID +" integer primary key autoincrement, " +
                Contrato.TablaIngredientes.NOMBRE + " text)";
        db.execSQL(sql);

        Log.v("XAPP", "Va a crear la tabla recetaingrediente");
        sql = "create table " + Contrato.TablaRecetarioIngredientes.TABLA + " (" +
                Contrato.TablaRecetarioIngredientes._ID +" integer primary key autoincrement, " +
                Contrato.TablaRecetarioIngredientes.RECETA + " integer, " +
                Contrato.TablaRecetarioIngredientes.INGREDIENTE + " integer, " +
                Contrato.TablaRecetarioIngredientes.CANTIDAD + " integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
