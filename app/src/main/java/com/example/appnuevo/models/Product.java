package com.example.appnuevo.models;

import java.util.ArrayList;

public class Product {


    private String idproducto;
    private String nombre;
    private String categoria;
    private ArrayList<Precios> precios;

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Precios> getPrecios() {
        return precios;
    }

    public void setPrecios(ArrayList<Precios> precios) {
        this.precios = precios;
    }
}
