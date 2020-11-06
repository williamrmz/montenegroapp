package com.example.appnuevo.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProductSelect {
    private String idproducto;
    private String nombre_producto;
    private String nombre_categoria;
    private String idprecio;
    private String nombre_precio;
    private String pcompra;
    private String pventa;
    private String porcentaje;
    private String cantidadunidad;

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

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

    @Override
    public String toString() {
        NumberFormat nf = new DecimalFormat("##.###");
        String cantidad =  nf.format(Double.parseDouble(cantidadunidad));

        return "ProductSelect{" +
                "idproducto='" + idproducto + '\'' +
                ", nombre_producto='" + nombre_producto + '\'' +
                ", nombre_categoria='" + nombre_categoria + '\'' +
                ", idprecio='" + idprecio + '\'' +
                ", nombre_precio='" + nombre_precio + '\'' +
                ", pcompra='" + pcompra + '\'' +
                ", pventa='" + pventa + '\'' +
                ", porcentaje='" + porcentaje + '\'' +
                ", cantidadunidad='" + cantidad + '\'' +
                '}';
    }
}
