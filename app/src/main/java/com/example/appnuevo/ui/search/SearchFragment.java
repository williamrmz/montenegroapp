package com.example.appnuevo.ui.search;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.adapters.ProductsAdapter;
import com.example.appnuevo.adapters.ProductsSelectedAdapter;
import com.example.appnuevo.ui.dialogs.DialogSearchProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchFragment extends Fragment {

    private static final String TAG = "API";

    private RecyclerView recyclerView;
    private ProductsSelectedAdapter productsSelectedAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewSearch);
        productsSelectedAdapter = new ProductsSelectedAdapter(this.getContext());
        recyclerView.setAdapter(productsSelectedAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogSearchProduct dl = new DialogSearchProduct();
                dl.show(getFragmentManager(), "TAG");
            }
        });

        return view;
    }


}