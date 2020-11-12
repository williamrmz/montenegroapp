package com.example.appnuevo.ui.sales;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.adapters.SalesAdapter;
import com.example.appnuevo.apis.ApiClient;
import com.example.appnuevo.interfaces.SaleItemClickInterface;
import com.example.appnuevo.models.Venta;
import com.example.appnuevo.models.VentaResponse;
import com.example.appnuevo.ui.dialogs.DetallesDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesFragment extends Fragment implements SaleItemClickInterface {

    private static final String TAG = "API";

    private RecyclerView recyclerView;
    private SalesAdapter salesAdapter;
    private int page;
    private boolean aptoParaCargar;
    //ArrayList<Venta> ventaList;
    VentaResponse ventaResponse;
    ArrayList<Venta> contList;

    public SalesFragment() {
        this.contList = new ArrayList<>();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewSales);
        salesAdapter = new SalesAdapter(this.getContext(), this);
        recyclerView.setAdapter(salesAdapter);
        recyclerView.setHasFixedSize(true);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        GridLayoutManager layoutManager  = new GridLayoutManager(this.getContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy>0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar){
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount){
                            Log.e(TAG, "LLEGAMOS AL FINAL : ");
                            aptoParaCargar = false;
                            page += 1;
                            listSales(page);
                        }
                    }
                }
            }
        });

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        aptoParaCargar = true;
        page = 1;
        listSales(page);
    }


    public void listSales(int page){
        Call<VentaResponse> call = ApiClient.getUserService().listSales(1, page);
        call.enqueue(new Callback<VentaResponse>() {
            @Override
            public void onResponse(Call<VentaResponse> call, Response<VentaResponse> response) {
                aptoParaCargar = true;
                if (response.isSuccessful()){
                    ventaResponse = response.body();
                    //ventalist va agregando al adaptador, no debe acumularse o se suman otra vez
                    ArrayList<Venta> ventaList = ventaResponse.getData();

                    //contlist para para que vaya acumulando con las entradas
                    contList.addAll(ventaResponse.getData());
                    salesAdapter.addSales(ventaList);
                }else {
                    Log.e(TAG, "Error en la respuesta : "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<VentaResponse> call, Throwable t) {
                aptoParaCargar = true;
                Log.e(TAG, "Error de conexion : "+ t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void sendDetail(int pos) {
        //Venta venta = contList.get(pos);
        //Log.e(TAG, "POSICIÓN : "+ pos + " DATOS : "+ venta);
        Venta venta = contList.get(pos);
        //Log.e(TAG, "DETALLE : " + venta.getDetalleVentas());
        DetallesDialog detallesDialog = new DetallesDialog(venta.getDetalleVentas());
        detallesDialog.show(getFragmentManager(), "detalledialog");
    }
}