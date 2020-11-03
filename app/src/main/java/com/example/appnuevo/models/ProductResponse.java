package com.example.appnuevo.models;

import java.util.ArrayList;

public class ProductResponse {

    private ArrayList<Product> data;
    private boolean status;

    public ArrayList<Product> getData() { return data; }

    public void setData(ArrayList<Product> data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

