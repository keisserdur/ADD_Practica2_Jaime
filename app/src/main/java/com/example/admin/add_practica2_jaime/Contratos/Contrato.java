package com.example.admin.add_practica2_jaime.Contratos;

import android.provider.BaseColumns;

/**
 * Created by Admin on 18/11/2015.
 */
public class Contrato {
    private Contrato(){
    }

    public static abstract class TablaReceta implements BaseColumns{
        public static final String TABLA="Recetario";
        public static final String NOMBRE="Nombre";
        public static final String FOTO="Foto";
        public static final String INSTRUCION="Instruccion";
    }
    public static abstract class TablaIngredientes implements BaseColumns{
        public static final String TABLA="Ingredientes";
        public static final String NOMBRE="Nombre";
    }
    public static abstract class TablaRecetarioIngredientes implements BaseColumns{
        public static final String TABLA="RecetarioIngredientes";
        public static final String RECETA="Receta";
        public static final String INGREDIENTE="Ingrediente";
        public static final String CANTIDAD="Cantidad";
    }
}
