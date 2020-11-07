package com.example.appnuevo.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.LoginActivity;
import com.example.appnuevo.R;
import com.example.appnuevo.adapters.ProductsSelectedAdapter;
import com.example.appnuevo.models.ProductSelect;
import com.example.appnuevo.models.Usuario;
import com.example.appnuevo.models.Venta;
import com.example.appnuevo.ui.dialogs.DialogSearchProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private static final String TAG = "API";

    private RecyclerView recyclerView;
    private ProductsSelectedAdapter productsSelectedAdapter;
    private FloatingActionButton boton, accept;
    private ArrayList<ProductSelect> products;
    private ProductSelect productSelect;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_selected, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewSelect);
        productsSelectedAdapter = new ProductsSelectedAdapter(this.getContext());
        recyclerView.setAdapter(productsSelectedAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        boton = view.findViewById(R.id.fab);
        accept = view.findViewById(R.id.accept);

        //Intent intent = new Intent();
       // Gson gson = new Gson();
        //intent.putExtra("STRING_RESULT", gson.toJson(productSelect));

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DialogSearchProduct dl = new DialogSearchProduct();
                dl.setTargetFragment(SearchFragment.this, 1);
                dl.show(getFragmentManager(), "DialogSearhProduct");
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            registerSale();
            }
        });
    }

    public void registerSale(){
        Venta venta = new Venta();
        //venta.setIdcliente();
        LoginActivity loginActivity = new LoginActivity();
        Log.e(TAG, "usuario : "+loginActivity.idusuario );
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            String editTextString = data.getStringExtra("lakey");
            Gson gson = new Gson();
            //gson ayuda a deserealizar el objeto enviado con la clase
            ProductSelect producto = gson.fromJson(editTextString, ProductSelect.class);
            productsSelectedAdapter.agregarProducto(producto);
        }else{
            Log.e(TAG, "nada : " );
        }
    }
}