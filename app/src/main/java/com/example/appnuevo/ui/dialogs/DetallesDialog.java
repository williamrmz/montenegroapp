package com.example.appnuevo.ui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appnuevo.R;
import com.example.appnuevo.adapters.DetailAdapter;
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
        View view = inflater.inflate(R.layout.dialogfragment_details, container, true);
        recyclerView = view.findViewById(R.id.recyclerViewDetails);
        detailAdapter = new DetailAdapter(this.getContext(), detalleVentasList);
        recyclerView.setAdapter(detailAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //detailAdapter.addListDetails(detalleVentasList);
        //Log.e(TAG, "DETALLELIST "  + detalleVentasList.size());
        return view;
    }

}
