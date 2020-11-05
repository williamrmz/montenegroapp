package com.example.appnuevo.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Precios {
    private String idprecio;
    private String nombre;
    private String pcompra;
    private String pventa;
    private String porcentaje;
    private String cantidadunidad;


    public String getIdprecio() {
        return idprecio;
    }

    public void setIdprecio(String idprecio) {
        this.idprecio = idprecio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPcompra() {
        return pcompra;
    }

    public void setPcompra(String pcompra) {
        this.pcompra = pcompra;
    }

    public String getPventa() {
        return pventa;
    }

    public void setPventa(String pventa) {
        this.pventa = pventa;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getCantidadunidad() {
        return cantidadunidad;
    }

    public void setCantidadunidad(String cantidadunidad) {
        this.cantidadunidad = cantidadunidad;
    }

    public String toString(){
        NumberFormat nf = new DecimalFormat("##.###");
        String cantidad =  nf.format(Double.parseDouble(cantidadunidad));
        return nombre + " cant : " +  cantidad +  " | precio: " + pventa;
    }
}
