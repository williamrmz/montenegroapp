package com.example.appnuevo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.interfaces.RecyclerViewClickInterface;
import com.example.appnuevo.models.Precios;
import com.example.appnuevo.models.Product;

import java.util.ArrayList;

public class SearchProductsAdapter extends RecyclerView.Adapter<SearchProductsAdapter.ViewHolder> {

    private static final String TAG = "API";
    Context context;
    ArrayList<Product> productsList;
    private RecyclerViewClickInterface recyclerViewClickInterfaceProduct;
    ArrayList<Precios> precios;
    ArrayAdapter<Precios> adaptador;

    public SearchProductsAdapter(Context context, RecyclerViewClickInterface recyclerViewClickInterfaceProduct) {
        this.context = context;
        productsList = new ArrayList<>();
        this.recyclerViewClickInterfaceProduct = recyclerViewClickInterfaceProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_products, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position %2 == 1){
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#f5f5f0"));
        }

        Product product = productsList.get(position);
        holder.nombre.setText(product.getNombre_producto());
        holder.categoria.setText(product.getNombre_categoria());

        //si encuentro producto con precios carga el spinner
        if (product.getPrecios() != null){
            //reemplaza al bucle, ya que trae un arreglo de objetos el getprecios
            precios = product.getPrecios();
            adaptador = new ArrayAdapter<Precios>(context, R.layout.support_simple_spinner_dropdown_item, precios);
            adaptador.setDropDownViewResource( R.layout.support_simple_spinner_dropdown_item);
            holder.precios.setAdapter(adaptador);
        } else {
            //limpiar el spinner si ya se cargó algo previamente
            if(adaptador != null){
                adaptador.clear();
                holder.precios.setAdapter(adaptador);
            }
        }
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

            //evento con la interface
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // precios.getSelectedItem() objeto del view seleccionado
                    recyclerViewClickInterfaceProduct.onItemClick(getAdapterPosition() , precios.getSelectedItem());
                }
            });
        }
    }


}