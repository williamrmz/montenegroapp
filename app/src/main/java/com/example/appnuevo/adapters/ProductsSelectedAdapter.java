package com.example.appnuevo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.models.Product;
import com.example.appnuevo.models.ProductSelect;
import com.example.appnuevo.ui.dialogs.SearchProductViewModel;
import com.example.appnuevo.ui.search.SearchFragment;

import java.util.ArrayList;

public class ProductsSelectedAdapter extends RecyclerView.Adapter<ProductsSelectedAdapter.ViewHolder> {

    private static final String TAG = "API";
    Context context;
    ArrayList<ProductSelect> productsSelectedList;

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
        ProductSelect productSelect = productsSelectedList.get(position);
        Log.e(TAG, "DATO : "+productSelect.toString());
    }

    @Override
    public int getItemCount() {
        return productsSelectedList.size();
    }

    public void agregarProducto(ProductSelect productSelect) {
        productsSelectedList.add(productSelect);
        Log.e(TAG, "DATO : "+productsSelectedList.toString());

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
