package com.example.appnuevo.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ProductSelect {
    private String idproducto;
    private String nombre_producto;
    private String nombre_categoria;
    private String idprecio;
    private String undm;
    private String pcompra;
    private String pventa;
    private String sundm;
    private String cant;
    private String cantidad = "1";

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

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

    public String getUndm() {
        return undm;
    }

    public void setUndm(String undm) {
        this.undm = undm;
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

    public String getSundm() {
        return sundm;
    }

    public void setSundm(String sundm) {
        this.sundm = sundm;
    }

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        NumberFormat nf = new DecimalFormat("##.###");
        this.cant = nf.format(Double.parseDouble(cant));
    }


    @Override
    public String toString() {
        NumberFormat nf = new DecimalFormat("##.###");
        //String cantunidad =  nf.format(Double.parseDouble(cant));
        //String cant = nf.format()

        return "ProductSelect{" +
                "idproducto='" + idproducto + '\'' +
                ", nombre_producto='" + nombre_producto + '\'' +
                ", nombre_categoria='" + nombre_categoria + '\'' +
                ", idprecio='" + idprecio + '\'' +
                ", undm='" + undm + '\'' +
                ", pcompra='" + pcompra + '\'' +
                ", pventa='" + pventa + '\'' +
                ", sundm='" + sundm + '\'' +
                ", cant='" + cant + '\'' +
                ", cantidad='" + cantidad + '\'' +
                '}';
    }
}
