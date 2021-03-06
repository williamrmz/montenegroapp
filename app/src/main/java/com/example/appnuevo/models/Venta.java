package com.example.appnuevo.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Date;

public class Venta {
    private int idventa;
    private int idcliente;
    private String fecha_venta;
    private String tipo_documento;
    private String num_documento;
    private int idusuario;
    private String serie_documento;
    private ArrayList<DetalleVenta> detalleVentas;
    private ArrayList<ProductSelect> productSelects;
    private ArrayList<Cliente> cliente;


    public int getIdventa() {
        return idventa;
    }

    public void setIdventa(int idventa) {
        this.idventa = idventa;
    }

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


    public ArrayList<DetalleVenta> getDetalleVentas() {
        return detalleVentas;
    }

    public void setDetalleVentas(ArrayList<DetalleVenta> detalleVentas) {
        this.detalleVentas = detalleVentas;
    }

    public ArrayList<ProductSelect> getProductSelects() {
        return productSelects;
    }

    public void setProductSelects(ArrayList<ProductSelect> productSelects) {
        this.productSelects = productSelects;
    }

    public ArrayList<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(ArrayList<Cliente> cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idcliente=" + idcliente +
                ", fecha_venta='" + fecha_venta + '\'' +
                ", tipo_documento='" + tipo_documento + '\'' +
                ", num_documento='" + num_documento + '\'' +
                ", idusuario=" + idusuario +
                ", serie_documento='" + serie_documento + '\'' +
                ", detalleVentas=" + detalleVentas +
                '}';
    }
}
