package com.example.appnuevo.dialogs;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.adapters.ProductsAdapter;
import com.example.appnuevo.apis.ApiClient;
import com.example.appnuevo.models.Precios;
import com.example.appnuevo.models.Product;
import com.example.appnuevo.models.ProductResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogSearchProduct extends AppCompatDialogFragment {


    private static final String TAG = "API";

    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;
    private EditText buscador;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View rootView = inflater.inflate(R.layout.dialogframent_search_product, container);
        View view = inflater.inflate(R.layout.dialogframent_search_product, container, false);
        Log.e(TAG, "SEARCHPRODUCTFRAGMENT CREATED");

        recyclerView = view.findViewById(R.id.recyclerViewSearch);
        productsAdapter = new ProductsAdapter(this.getContext());
        recyclerView.setAdapter(productsAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        buscador = view.findViewById(R.id.etBuscador);

        buscador.addTextChangedListener(new TextWatcher() {

            private void search(String input, int page) {
                Call<ProductResponse> call = ApiClient.getUserService().searchProductMatch(input, page);
                call.enqueue(new Callback<ProductResponse>() {
                    @Override
                    public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                        if(response.isSuccessful()){
                            productsAdapter.limpiarProducts();
                            ProductResponse productResponse = response.body();

                            boolean estado = productResponse.isStatus();
                            if (estado){
                                ArrayList<Product> products = productResponse.getData();
                                productsAdapter.adicionarListaProducts(products);

                                /*Gson gson = new Gson();
                                String datosPaser = gson.toJson(response.body().getData().get(0).getPrecios());
                                Log.e(TAG, "DATOS GENERALES: "+ datosPaser);*/

                            } else {
                                Product product = new Product();
                                product.setNombre("Sin coincidencias");
                                productsAdapter.adicionarVacio(product);
                            }


                            //Log.e(TAG, "DATOS GENERALES: "+ datosPaser);


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
                    this.search(proudcto.toString(), 0);
                }else { productsAdapter.limpiarProducts(); }
            }



            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //this.searchProduct();
        return view;
    }

    /*
    public void searchProduct(){
        Call<ProductResponse> call = ApiClient.getUserService().searchProductMatch("CHIZI");
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    ProductResponse productResponse = response.body();
                    ArrayList<Product> products = productResponse.getData();
                    //Log.e(TAG, "DATA : "+products.toString());}
                    productsAdapter.adicionarListaProducts(products);
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

     */
}