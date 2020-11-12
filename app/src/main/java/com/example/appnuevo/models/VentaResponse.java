package com.example.appnuevo.models;

import java.util.ArrayList;

public class VentaResponse {

    private ArrayList<Venta> data;
    private boolean status;

    public ArrayList<Venta> getData() {
        return data;
    }

    public void setData(ArrayList<Venta> data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
