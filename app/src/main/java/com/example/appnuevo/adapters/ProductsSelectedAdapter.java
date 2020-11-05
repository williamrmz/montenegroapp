package com.example.appnuevo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.models.Product;

import java.util.ArrayList;

public class ProductsSelectedAdapter extends RecyclerView.Adapter<ProductsSelectedAdapter.ViewHolder> {

    private static final String TAG = "API";
    Context context;
    ArrayList<Product> productsSelectedList;

    public ProductsSelectedAdapter(Context context) {
        this.context = context;
        productsSelectedList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_products_selected, parent, false);
        return new ProductsSelectedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Product product = productsSelectedList.get(position);
        Log.e(TAG, "ASDASDSAD : ");
    }

    @Override
    public int getItemCount() {
        return productsSelectedList.size();
    }

    public void agregarProducto(Product product) {
        productsSelectedList.add(product);
        //Log.e(TAG, "ASDASDSAD : "+productsSelectedList.get(0).getPrecios().get(0).getCantidadunidad());
        Log.e(TAG, "CANTIDAD : "+productsSelectedList.size());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio, categoria, total;
        EditText cantidad;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNameSelected);
            categoria = itemView.findViewById(R.id.tvCategorySelected);
            precio = itemView.findViewById(R.id.tvPriceSelected);
            cantidad = itemView.findViewById(R.id.etCantSelected);
            total = itemView.findViewById(R.id.tvTotalSelected);
        }
    }
}
