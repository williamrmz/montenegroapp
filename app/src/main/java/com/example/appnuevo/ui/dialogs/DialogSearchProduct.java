package com.example.appnuevo.ui.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.appnuevo.ui.search.SearchFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogSearchProduct extends DialogFragment implements RecyclerViewClickInterface {

    private static final String TAG = "API";

    private RecyclerView recyclerView;
    private ProductsAdapter productsAdapter;
    private EditText buscador;
    ArrayList<Product> products;
    private ProductsSelectedAdapter productsSelectedAdapter;

    //private SearchProductViewModel model;
    public ProductSelect productSelect;
    private DialogInterface.OnDismissListener onDismissListener;


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
                    this.search(proudcto.toString(), 0);
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
        //model = new ViewModelProvider(requireActivity()).get(SearchProductViewModel.class);
    }

    @Override
    public void onItemClick(int position, Object preciodata) {
        Product datoseleccionado = products.get(position);
        Precios precios = (Precios) preciodata;
        productSelect = new ProductSelect();
        productSelect.setIdproducto(datoseleccionado.getIdproducto());
        productSelect.setNombre_producto(datoseleccionado.getNombre_producto());
        productSelect.setNombre_categoria(datoseleccionado.getNombre_categoria());
        productSelect.setIdprecio(precios.getIdprecio());
        productSelect.setNombre_precio(precios.getNombre_precio());
        productSelect.setPcompra(precios.getPcompra());
        productSelect.setPventa(precios.getPventa());
        productSelect.setPorcentaje(precios.getPorcentaje());
        productSelect.setCantidadunidad(precios.getCantidadunidad());

        //Toast.makeText(getContext(), precios.toString(), Toast.LENGTH_SHORT).show();
        //productsSelectedAdapter.agregarProducto(productSelect);
        //model.setProducts(productSelect);
        getDialog().dismiss();
    }

    @Override
    public void onLongItemClick(int postion) {
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            SearchFragment searchFragment = new SearchFragment();
            searchFragment.productSelect = productSelect;
            Log.e(TAG, "product : " + searchFragment.productSelect);
            onDismissListener.onDismiss(dialog);
        }
    }

}