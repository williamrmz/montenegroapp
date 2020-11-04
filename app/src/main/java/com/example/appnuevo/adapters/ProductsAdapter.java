package com.example.appnuevo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.models.Precios;
import com.example.appnuevo.models.Product;
import com.example.appnuevo.models.ProductResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private static final String TAG = "API";
    Context context;
    ArrayList<Product> productsList;

    public ProductsAdapter(Context context) {
        this.context = context;
        productsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productsList.get(position);
        holder.nombre.setText(product.getNombre());
        holder.categoria.setText(product.getCategoria());

        int cantidadPrecios = product.getPrecios().size();

        Gson gson = new Gson();
        String datosSpinner = gson.toJson(product.getPrecios());

                //holder.precios.setText(product.getPrecios().get(0).getPrecio());}

        ArrayList<Precios> precios = new ArrayList<Precios>();
        for (int i=0; i<cantidadPrecios; i++){
            Precios prices = new Precios();
            prices.setIdprecio(product.getPrecios().get(i).getIdprecio());
            prices.setNombre(product.getPrecios().get(i).getNombre());
            prices.setPrecio(product.getPrecios().get(i).getPrecio());
            Log.e(TAG, "SPINNER : "+prices.getPrecio());
        }

        //ArrayAdapter<Precios> adaptador = new ArrayAdapter<Precios>(context, R.layout.support_simple_spinner_dropdown_item, Integer.parseInt(gson.toJson(precios)));

        //holder.precios.setAdapter(adaptador);



    }


    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public void adicionarListaProducts(ArrayList<Product> products) {
        productsList.addAll(products);
        notifyDataSetChanged();
    }

    public void limpiarProducts() {
        this.productsList.clear();
        notifyDataSetChanged();
    }

    public void adicionarVacio(Product product) {
        productsList.add(product);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, categoria;
        Spinner precios;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNameSearch);
            categoria = itemView.findViewById(R.id.tvCategorySearch);
            precios = itemView.findViewById(R.id.spnPrecios);
        }
    }
}