package com.example.admin.add_practica2_jaime.POJO;

/**
 * Created by Admin on 18/11/2015.
 */
public class Ingrediente {
    private long id;
    private String nombre;

    public Ingrediente() {
    }

    public Ingrediente(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    @Override
    public String toString() {
        return "Ingrediente\n" +
                "  id=" + id + '\n'+
                "  nombre='" + nombre + '\''+'\n';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ingrediente that = (Ingrediente) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
