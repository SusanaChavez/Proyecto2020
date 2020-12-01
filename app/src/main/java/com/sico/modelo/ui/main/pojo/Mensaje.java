package com.sico.modelo.ui.main.pojo;

import java.util.Date;

public class Mensaje {
    private int id;
    private String destino;
    private Date fecha;
    private String texto;
    private String detalle;

    public Mensaje(int id, String destino, String detalle) {
        this.id = id;
        this.destino = destino;
        this.detalle = detalle;
    }

    public Mensaje(String destino, Date fecha, String texto, String detalle) {
        //       this.id = id;
        this.destino = destino;
        this.fecha = fecha;
        this.texto = texto;
        this.detalle = detalle;
    }

    public int getId() {return id; }

    public String getDestino() {return destino; }

    public Date getFecha() {return fecha; }

    public String getTexto() {return texto; }

    public String getDetalle() {return detalle; }

    @Override
    public String toString() {return texto; }
}
