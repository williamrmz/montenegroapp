package com.example.appnuevo.ui.search;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.adapters.ProductsAdapter;
import com.example.appnuevo.adapters.ProductsSelectedAdapter;
import com.example.appnuevo.models.ProductSelect;
import com.example.appnuevo.ui.dialogs.DialogSearchProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private static final String TAG = "API";


    private RecyclerView recyclerView;
    private ProductsSelectedAdapter productsSelectedAdapter;
    DialogSearchProduct dialogSearchProduct;
    public ProductSelect productSelect;
    public ArrayList<ProductSelect> productSelects;
    private FloatingActionButton boton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewSelect);
        productsSelectedAdapter = new ProductsSelectedAdapter(this.getContext());
        recyclerView.setAdapter(productsSelectedAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        boton = view.findViewById(R.id.fab);


        //FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogSearchProduct dl = new DialogSearchProduct();
                dl.setTargetFragment(SearchFragment.this, 1);
                /*dl.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //Log.e(TAG, "SE CERRÓ : " + productSelects.toString());
                    }
                });*/
                dl.show(getFragmentManager(), "DialogSearhProduct");
            }
        });
    }
}