package com.example.appnuevo.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Precios {
    private String idprecio;
    private String nombre_precio;
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

    public String getNombre_precio() {
        return nombre_precio;
    }

    public void setNombre_precio(String nombre_precio) {
        this.nombre_precio = nombre_precio;
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
        return nombre_precio + " cant : " +  cantidad +  " | precio: " + pventa;
    }


}
