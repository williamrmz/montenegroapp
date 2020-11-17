package com.example.appnuevo.models;

public class Cliente {

    private int idcliente = 3067;
    private String nombre = "CLIENTE";
    private String apellidos;
    private String dni = "SN";

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String toString() {
        return nombre.toUpperCase() +"    |   "+dni.trim();
    }
}
