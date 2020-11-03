package com.example.appnuevo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.models.Product;

import java.util.ArrayList;

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
        holder.preciocompra.setText(product.getPrecio_compra());
        holder.precioventa.setText(product.getPrecio_venta());
        holder.categoria.setText(product.getNombre_categoria());
        holder.idproducto.setText(product.getCodigo());
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, categoria, preciocompra, precioventa, idproducto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNameSearch);
            categoria = itemView.findViewById(R.id.tvCategorySearch);
            preciocompra = itemView.findViewById(R.id.tvPurchaseSearch);
            precioventa = itemView.findViewById(R.id.tvSalesPurchase);
            idproducto = itemView.findViewById(R.id.tvCodeSearch);
        }
    }
}