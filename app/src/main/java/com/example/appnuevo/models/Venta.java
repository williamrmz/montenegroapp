package com.example.appnuevo.models;

import java.util.Date;

public class Venta {
    private int idcliente;
    private String fecha_venta;
    private String tipo_documento;
    private String num_documento;
    private int idusuario;
    private String serie_documento;

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getNum_documento() {
        return num_documento;
    }

    public void setNum_documento(String num_documento) {
        this.num_documento = num_documento;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getSerie_documento() {
        return serie_documento;
    }

    public void setSerie_documento(String serie_documento) {
        this.serie_documento = serie_documento;
    }
}
