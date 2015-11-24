package com.example.admin.add_practica2_jaime.POJO;

import android.database.Cursor;

import com.example.admin.add_practica2_jaime.Contratos.Contrato;

/**
 * Created by Admin on 18/11/2015.
 */
public class Receta {
    private long id;
    private String nombre,foto,instruccion;

    public Receta() {
    }

    public Receta(long id, String nombre, String foto, String instruccion) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.instruccion = instruccion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getInstruccion() {
        return instruccion;
    }

    public void setInstruccion(String instruccion) {
        this.instruccion = instruccion;
    }

    public Receta getRow(Cursor c){
        Receta r=new Receta();
        r.setNombre(c.getString(c.getColumnIndex(Contrato.TablaReceta.NOMBRE)));
        r.setFoto(c.getString(c.getColumnIndex(Contrato.TablaReceta.FOTO)));
        r.setInstruccion(c.getString(c.getColumnIndex(Contrato.TablaReceta.INSTRUCION)));
        return r;
    }

    @Override
    public String toString() {
        return "Receta\n" +
                "  id=" + id +
                "  nombre='" + nombre + '\''+'\n' +
                "  foto='" + foto + '\''+'\n'  +
                "  instruccion='" + instruccion + '\''+'\n'  ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Receta receta = (Receta) o;

        return id == receta.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
