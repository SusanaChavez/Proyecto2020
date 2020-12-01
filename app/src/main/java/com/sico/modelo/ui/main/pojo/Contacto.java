package com.sico.modelo.ui.main.pojo;

public class Contacto {
    private final String id;
    private final String nombre;
    private final String telefono;
    private final String foto;

    public Contacto(String id, String nombre, String telefono, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.foto = foto;
    }

    public String getId() {return id;}

    public String getNombre() {return nombre;}

    public String getTelefono() {return telefono;}

    public String getFoto() {return foto;}

    @Override
    public String toString(){return nombre;}
}
