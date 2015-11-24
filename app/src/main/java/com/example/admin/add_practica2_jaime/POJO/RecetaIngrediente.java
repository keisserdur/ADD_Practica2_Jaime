package com.example.admin.add_practica2_jaime.POJO;

/**
 * Created by Admin on 18/11/2015.
 */
public class RecetaIngrediente {
    private long id,receta,ingrediente,cantidad;

    public RecetaIngrediente() {
    }

    public RecetaIngrediente(long id, long receta, long ingrediente, long cantidad) {
        this.id = id;
        this.receta = receta;
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getReceta() {
        return receta;
    }

    public void setReceta(long receta) {
        this.receta = receta;
    }

    public long getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(long ingrediente) {
        this.ingrediente = ingrediente;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "RecetaIngrediente\n" +
                "  id=" + id + "\n"+
                "  receta=" + receta + "\n"+
                "  ingrediente=" + ingrediente +"\n"+
                "  cantidad=" + cantidad ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecetaIngrediente that = (RecetaIngrediente) o;

        return id == that.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
