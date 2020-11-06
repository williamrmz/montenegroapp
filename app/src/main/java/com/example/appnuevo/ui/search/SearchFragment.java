package com.example.appnuevo.ui.search;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.adapters.ProductsAdapter;
import com.example.appnuevo.adapters.ProductsSelectedAdapter;
import com.example.appnuevo.models.ProductSelect;
import com.example.appnuevo.ui.dialogs.DialogSearchProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private static final String TAG = "API";

    private RecyclerView recyclerView;
    private ProductsSelectedAdapter productsSelectedAdapter;
    private FloatingActionButton boton;
    private ArrayList<ProductSelect> products;
    private ProductSelect productSelect;


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

        Intent intent = new Intent();
        Gson gson = new Gson();
        intent.putExtra("STRING_RESULT", gson.toJson(productSelect));


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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Make sure fragment codes match up
        if (requestCode == 1) {
            String editTextString = data.getStringExtra("lakey");
            Gson gson = new Gson();
            ProductSelect producto = gson.fromJson(editTextString, ProductSelect.class);

            /*productSelect = new ProductSelect();
            productSelect.setIdproducto(producto.getIdproducto());
            productSelect.setNombre_producto(producto.getNombre_producto());
            productSelect.setNombre_categoria(producto.getNombre_categoria());
            productSelect.setIdprecio(producto.getIdprecio());
            productSelect.setNombre_precio(producto.getNombre_precio());
            productSelect.setPcompra(producto.getPcompra());
            productSelect.setPventa(producto.getPventa());
            productSelect.setPorcentaje(producto.getPorcentaje());
            productSelect.setCantidadunidad(producto.getCantidadunidad());*/

            //productsSelectedAdapter.agregarProducto(productSelect);;
            productsSelectedAdapter.agregarProducto(producto);

        }else{
            Log.e(TAG, "nada : " );
        }
    }
}