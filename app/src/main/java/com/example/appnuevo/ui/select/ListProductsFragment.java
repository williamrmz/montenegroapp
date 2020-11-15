package com.example.appnuevo.ui.select;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import com.example.appnuevo.models.DetalleVenta;
import com.example.appnuevo.models.ProductSelect;
import com.example.appnuevo.models.Request;
import com.example.appnuevo.models.Venta;
import com.example.appnuevo.pdfs.TickectPDF;
import com.example.appnuevo.ui.dialogs.LoadingDialog;
import com.example.appnuevo.ui.dialogs.SearchProductDialog;
import com.example.appnuevo.ui.sales.SalesFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProductsFragment extends Fragment {

    private static final String TAG = "API";

    private RecyclerView recyclerView;
    private ProductsSelectedAdapter productsSelectedAdapter;
    private FloatingActionButton boton, accept;
    LoadingDialog loadingDialog;
    Venta venta;
    TickectPDF tickectPDF;
    double precioTotal;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_selected, container, false);

        loadingDialog = new LoadingDialog(getActivity());

        recyclerView = view.findViewById(R.id.recyclerViewSelect);
        productsSelectedAdapter = new ProductsSelectedAdapter(this.getContext());
        recyclerView.setAdapter(productsSelectedAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        boton = view.findViewById(R.id.fab);
        accept = view.findViewById(R.id.accept);

        new ItemTouchHelper(itemTouch).attachToRecyclerView(recyclerView);

        tickectPDF = new TickectPDF(getContext());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //FragmentManager fm = getActivity().getSupportFragmentManager();
                SearchProductDialog dl = new SearchProductDialog();
                dl.setTargetFragment(ListProductsFragment.this, 1);
                dl.show(getFragmentManager(), "DialogSearhProduct");
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(productsSelectedAdapter.getItemCount() >=1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setMessage("¿Desea agregar esta venta?")
                            .setCancelable(false)
                            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    try {
                                        registerSale();
                                        finalize();
                                        loadingDialog.startLoadingDialog();
                                    } catch (Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();


                }else {
                    Toast.makeText(getContext(),"No se ha ingresado productos " , Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void registerSale(){
        venta = new Venta();
        venta.setIdcliente(3067);
        venta.setTipo_documento("Boleta");
        venta.setNum_documento("Desdeapp");
        venta.setIdusuario(Integer.parseInt(LoginActivity.usuario.getIdusuario()));
        venta.setSerie_documento("Seriedesdeapp");
        venta.setFecha_venta(String.valueOf(android.text.format.DateFormat.format("yyyy-MM-dd hh:mm:ss", new java.util.Date())));
        venta.setDetalleVentas(productsSelectedAdapter.listProducts());
        venta.setProductSelects(productsSelectedAdapter.listProducts());


        Call<Venta> call = ApiClient.getUserService().registerSale(venta);
        call.enqueue(new Callback<Venta>() {
            @Override
            public void onResponse(Call<Venta> call, Response<Venta> response) {
                if (response.isSuccessful()){
                    //le asigno como parametro la venta que estoy trayendo como respuesta
                    startPDF(response.body());
                    //se limpia la lista
                    productsSelectedAdapter.clearList();
                    //se cierra spinner
                    loadingDialog.dismissDialog();
                    //abre pdf
                    tickectPDF.appViewPDF(getActivity());
                    Toast.makeText(getContext(),"Se Registró Venta " , Toast.LENGTH_SHORT).show();
                }
                else {
                    loadingDialog.dismissDialog();
                    Toast.makeText(getContext(),"No se pudo registrar ", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Venta> call, Throwable t) {
                loadingDialog.dismissDialog();
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
        }else{
            Log.e(TAG, "nada : " );
        }
    }

    //para eliminar ventas con deslizar
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

    private ArrayList<String[]> printSell(ArrayList<DetalleVenta> selects ){
        ArrayList<String[]> rows = new ArrayList<>();
        //ArrayList<ProductSelect> productSelects =  selects; //venta.getProductSelects();
        double totalCont=0;
        for(int i=0; i<selects.size(); i++){
            rows.add(new String[]{
                    String.valueOf(selects.get(i).getCantidad()),
                    String.valueOf(selects.get(i).getUndm()),
                    String.valueOf(selects.get(i).getNombre_producto()),
                    String.format("%.2f",selects.get(i).getPventa()),
                    String.format("%.2f", selects.get(i).getPventa() * selects.get(i).getCantidad()),
                    //String.valueOf(detalleVenta.get(i).getPrecio_unitario() * detalleVenta.get(i).getCantidad())
            });
            totalCont += selects.get(i).getPventa() * selects.get(i).getCantidad();

        }
        precioTotal = totalCont;
        return rows;
    }

    public void startPDF(Venta sale){
        String[] header = {"Cant", "UM", "Descripción", "Precio", "Total"};
        String shortText = "NOTA PEDIDO N° "+sale.getIdventa();
        String lognText = "FECHA EMISION: " +sale.getFecha_venta();

        tickectPDF.openDocument();
        tickectPDF.addMetaData("Montenegro", "Ventas", "William");
        tickectPDF.addTitles("DISTRIBUIDORA & COMERCIONALIZADORA","ELIZABETH S.R.L.",
                "NICOLAS CUGLIVAN 210-074602962 - 948023073 " +
                        "COMERCIALIZACIÓN DE ARROZ Y AZUCAR - ABARRATOES EN GENERAL");
        tickectPDF.addParagraph(shortText);
        tickectPDF.addDateParagraph(lognText);
        //crear la tabla con el header y los celdas de la tabla
        tickectPDF.createTable(header, printSell(sale.getDetalleVentas()));
        tickectPDF.addParagraph("Total a Pagar :               S/."+ String.format("%.2f", precioTotal));
        tickectPDF.addParagraph("Cajero : "+ LoginActivity.usuario.getNombre());
        tickectPDF.closeDocument();
    }

}