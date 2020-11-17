package com.example.appnuevo.models;

import java.util.ArrayList;

public class ResponseAPI {
    private ArrayList<Cliente> clientes;
    private boolean status;

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
