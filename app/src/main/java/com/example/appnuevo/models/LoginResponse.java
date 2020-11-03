package com.example.appnuevo.models;

import java.util.ArrayList;

public class LoginResponse {

    private ArrayList<Usuario> data;
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<Usuario> getData() {
        return data;
    }

    public void setData(ArrayList<Usuario> data) {
        this.data = data;
    }


}

