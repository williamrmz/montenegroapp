package com.example.appnuevo.ui.dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.appnuevo.adapters.ProductsAdapter;
import com.example.appnuevo.adapters.ProductsSelectedAdapter;
import com.example.appnuevo.apis.ApiClient;
import com.example.appnuevo.interfaces.RecyclerViewClickInterface;
import com.example.appnuevo.models.Precios;
import com.example.appnuevo.models.Product;
import com.example.appnuevo.models.ProductResponse;
import com.example.appnuevo.models.ProductSelect;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchProductDialog extends DialogFragment implements RecyclerViewClickInterface {

    private static final String TAG = "API";

    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;
    private EditText buscador;
    ArrayList<Product> products;
    private ProductsSelectedAdapter productsSelectedAdapter;
    public ProductSelect productSelect;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogframent_search_product, container, true);
        //Log.e(TAG, "SEARCHPRODUCTFRAGMENT CREATED");

        recyclerView = view.findViewById(R.id.recyclerViewSearch);
        productsAdapter = new ProductsAdapter(this.getContext(), this);
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        buscador = view.findViewById(R.id.etBuscador);

        buscador.addTextChangedListener(new TextWatcher() {

            //BUSQUEDA DEENTRO DEL TEXTWATCHER
            private void search(String input) {
                Call<ProductResponse> call = ApiClient.getUserService().searchProductMatch(input);
                call.enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        if(response.isSuccessful()){
                            productsAdapter.limpiarProducts();
                            ProductResponse productResponse = response.body();

                            boolean estado = productResponse.isStatus();
                            if (estado){
                                products = productResponse.getData();
                                productsAdapter.adicionarListaProducts(products);

                                /*Gson gson = new Gson();
                                String datosPaser = gson.toJson(response.body().getData().get(0).getPrecios());
                                Log.e(TAG, "DATOS GENERALES: "+ datosPaser);*/

                            } else {
                                Product product = new Product();
                                product.setNombre_producto("Sin coincidencias");
                                productsAdapter.adicionarVacio(product);
                            }
                        } else {
                            Log.e(TAG, "NO SE PUDO CONECTAR : " + response.errorBody());
                        }
                    }
                    @Override
                    public void onFailure(Call<ProductResponse> call, Throwable t) {
                        Log.e(TAG, "ONFAILURE SALES : " + t.toString());
                    }
                });
            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }


            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String proudcto = buscador.getText().toString();

                if (proudcto.length() > 3){
                    this.search(proudcto);
                }else { productsAdapter.limpiarProducts(); }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        productsSelectedAdapter = new ProductsSelectedAdapter(getContext());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onItemClick(int position, Object preciodata) {
        if (preciodata == null){

            getDialog().dismiss();
        }else {
            Product datoseleccionado = products.get(position);
            Precios precios = (Precios) preciodata;
            productSelect = new ProductSelect();
            productSelect.setIdproducto(datoseleccionado.getIdproducto());
            productSelect.setNombre_producto(datoseleccionado.getNombre_producto());
            productSelect.setNombre_categoria(datoseleccionado.getNombre_categoria());
            productSelect.setIdprecio(precios.getIdprecio());
            productSelect.setUndm(precios.getUndm());
            productSelect.setPcompra(precios.getPcompra());
            productSelect.setPventa(precios.getPventa());
            productSelect.setSundm(precios.getSundm());
            productSelect.setCant(precios.getCant());

            sendResult(1);
            getDialog().dismiss();
        }

    }

    @Override
    public void onLongItemClick(int postion) {
    }

    private void sendResult(int REQUEST_CODE) {
        Intent intent = new Intent();
        Gson gson = new Gson();
        intent.putExtra("lakey", gson.toJson(productSelect));
        //intent.puta("lakey", productSelect);
        getTargetFragment().onActivityResult(getTargetRequestCode(), REQUEST_CODE, intent);
    }

}