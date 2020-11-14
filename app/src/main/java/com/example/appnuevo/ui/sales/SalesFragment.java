package com.example.appnuevo.ui.sales;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.appnuevo.LoginActivity;
import com.example.appnuevo.R;
import com.example.appnuevo.adapters.SalesAdapter;
import com.example.appnuevo.apis.ApiClient;
import com.example.appnuevo.interfaces.SaleItemClickInterface;
import com.example.appnuevo.models.DetalleVenta;
import com.example.appnuevo.models.Venta;
import com.example.appnuevo.models.VentaResponse;
import com.example.appnuevo.pdfs.TickectPDF;
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
    VentaResponse ventaResponse;
    ArrayList<Venta> contList;
    Venta venta;
    TickectPDF tickectPDF;
    double precioTotal;



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
        tickectPDF = new TickectPDF(getContext());

        return view;

    }


    private ArrayList<String[]> getClients(){
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"1", "AEAEA", "Prueba1"});
        rows.add(new String[]{"2", "Miguel", "Prueba2"});
        rows.add(new String[]{"3", "William", "Prueba3"});
        rows.add(new String[]{"1", "AEAEA", "Prueba1"});
        rows.add(new String[]{"1", "AEAEA", "Prueba1"});

        return rows;
    }

    private ArrayList<String[]> getDetail(){
        ArrayList<String[]> rows = new ArrayList<>();
        ArrayList<DetalleVenta> detalleVenta = venta.getDetalleVentas();
        double totalCont=0;
        for(int i=0; i<detalleVenta.size(); i++){
            rows.add(new String[]{
                    String.valueOf(detalleVenta.get(i).getCantidad()),
                    String.valueOf(detalleVenta.get(i).getUndm()),
                    String.valueOf(detalleVenta.get(i).getNombre_producto()),
                    String.valueOf(detalleVenta.get(i).getPrecio_unitario()),
                    String.valueOf(detalleVenta.get(i).getPrecio_unitario() * detalleVenta.get(i).getCantidad())
            });
            totalCont += detalleVenta.get(i).getPrecio_unitario() * detalleVenta.get(i).getCantidad();
        }
        precioTotal = totalCont;
        return rows;
    }

    public void startPDF(){
        String[] header = {"Cant", "UM", "Descripción", "Precio", "Total"};
        String shortText = "NOTA PEDIDO N° "+venta.getIdventa();
        String lognText = "FECHA EMISION: " +venta.getFecha_venta();

        tickectPDF.openDocument();
        tickectPDF.addMetaData("Montenegro", "Ventas", "William");
        tickectPDF.addTitles("DISTRIBUIDORA & COMERCIONALIZADORA","ELIZABETH S.R.L.",
                "NICOLAS CUGLIVAN 210-074602962 - 948023073" +
                        "COMERCIALIZACIÓN DE ARROZ Y AZUCAR - ABARRATOES EN GENERAL");
        tickectPDF.addParagraph(shortText);
        tickectPDF.addParagraph(lognText);
        tickectPDF.createTable(header, getDetail()
        );
        tickectPDF.addParagraph("Total a Pagar :               S/."+precioTotal);
        tickectPDF.addParagraph("Cajero : "+ LoginActivity.usuario.getNombre());
        tickectPDF.closeDocument();
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
    public void sendDetail(int pos) {
        //se guarda una venta para enviar datos al pdf
        venta = contList.get(pos);

        DetallesDialog detallesDialog = new DetallesDialog(venta.getDetalleVentas());
        detallesDialog.show(getFragmentManager(), "detalledialog");
    }

    @Override
    public void chargeSale(int pos) {
        venta = contList.get(pos);
        Log.e(TAG, venta.toString());
        this.startPDF();
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1: //EXAMEN 1
            {
                tickectPDF.appViewPDF(this.getActivity());
            }
            break;
        }
        return super.onContextItemSelected(item);
    }
}