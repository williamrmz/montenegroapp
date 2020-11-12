package com.example.appnuevo.ui.dialogs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appnuevo.R;
import com.example.appnuevo.adapters.DetailAdapter;
import com.example.appnuevo.adapters.ProductsAdapter;
import com.example.appnuevo.models.DetalleVenta;

import java.util.ArrayList;

public class DetallesDialog extends DialogFragment {

    private static final String TAG = "API";

    private RecyclerView recyclerView;
    private DetailAdapter detailAdapter;
    ArrayList<DetalleVenta> detalleVentasList;

    public DetallesDialog(ArrayList<DetalleVenta> detalleVentasList) {
        this.detalleVentasList = detalleVentasList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_details, container, false);
        //Log.e(TAG, "DATOS GENERALES: "+ detalleVentasList);
        recyclerView = view.findViewById(R.id.recyclerViewSales);
        detailAdapter = new DetailAdapter(getContext());
        recyclerView.setAdapter(detailAdapter);
        //recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //detailAdapter.addListDetails(detalleVentasList);
        return view;
    }
}