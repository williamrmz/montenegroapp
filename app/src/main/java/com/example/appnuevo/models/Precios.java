package com.example.appnuevo.models;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Precios {
    private String idprecio;
    private String undm;
    private String pcompra;
    private String pventa;
    private String sundm;
    private String cant;

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
        this.cant = cant;
    }

    public String toString(){
        NumberFormat nf = new DecimalFormat("##.###");
        String cantidad =  nf.format(Double.parseDouble(cant));
        return undm + " cant : " +  cantidad +  " | precio: " + pventa;
    }


}
