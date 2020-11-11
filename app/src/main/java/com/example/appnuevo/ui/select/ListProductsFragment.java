package com.example.appnuevo.ui.select;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.LoginActivity;
import com.example.appnuevo.R;
import com.example.appnuevo.adapters.ProductsSelectedAdapter;
import com.example.appnuevo.apis.ApiClient;
import com.example.appnuevo.models.ProductSelect;
import com.example.appnuevo.models.Request;
import com.example.appnuevo.models.Venta;
import com.example.appnuevo.ui.dialogs.SearchProductDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductsFragment extends Fragment {

    private static final String TAG = "API";

    private RecyclerView recyclerView;
    private ProductsSelectedAdapter productsSelectedAdapter;
    private FloatingActionButton boton, accept;


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

        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                SearchProductDialog dl = new SearchProductDialog();
                dl.setTargetFragment(ListProductsFragment.this, 1);
                dl.show(getFragmentManager(), "DialogSearhProduct");
            }
        });

        if(productsSelectedAdapter.getItemCount() >=1){
            accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    registerSale();
                }
            });
        } else {
            Toast.makeText(getContext(),"No se ha ingresado productos " , Toast.LENGTH_SHORT).show();
        }


    }

    public void registerSale(){
        Gson gson = new Gson();
        Venta venta = new Venta();
        venta.setIdcliente(3067);
        venta.setTipo_documento("Boleta");
        venta.setNum_documento("Desdeapp");
        venta.setIdusuario(Integer.parseInt(LoginActivity.usuario.getIdusuario()));
        venta.setSerie_documento("Seriedesdeapp");

        venta.setDetalleVentas(productsSelectedAdapter.listProducts());
        //venta.setJson(gson.toJson(productsSelectedAdapter.listProducts()));

        Log.e(TAG, "VENTA "+ gson.toJson(venta));
        Log.e(TAG, "VENTA "+ venta);

        Call<Request> call = ApiClient.getUserService().registerSale(venta);
        call.enqueue(new Callback<Request>() {
            @Override
            public void onResponse(Call<Request> call, Response<Request> response) {
                if (response.isSuccessful()){
                    //Request request = response.body();
                    //Log.e(TAG, "REQUEST : " +  request.getRequest().toString());}
                    productsSelectedAdapter.clearList();
                    Toast.makeText(getContext(),"Se Registró Venta " , Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getContext(),"No se pudo registrar ", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Request> call, Throwable t) {
                Toast.makeText(getContext(),"Error en conexión", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            String productPut = data.getStringExtra("lakey");
            Gson gson = new Gson();
            //gson ayuda a deserealizar el objeto enviado con la clase
            ProductSelect producto = gson.fromJson(productPut, ProductSelect.class);
            productsSelectedAdapter.agregarProducto(producto);
            //Log.e(TAG, "XD "+producto);
        }else{
            Log.e(TAG, "nada : " );
        }
    }

    ItemTouchHelper.SimpleCallback itemTouch = new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            productsSelectedAdapter.remove(viewHolder.getAdapterPosition());
        }
    };

}