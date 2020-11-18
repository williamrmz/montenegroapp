package com.example.appnuevo.models;

public class Cliente {

    private int idcliente;
    private String nombre;
    private String apellidos;
    private String dni;

    public Cliente(int idcliente, String nombre, String dni) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.dni = dni;
    }

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
