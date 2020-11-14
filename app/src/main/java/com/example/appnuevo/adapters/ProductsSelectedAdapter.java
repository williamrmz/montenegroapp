package com.example.appnuevo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.models.ProductSelect;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ProductsSelectedAdapter extends RecyclerView.Adapter<ProductsSelectedAdapter.ViewHolder> {

    private static final String TAG = "API";
    Context context;
    ArrayList<ProductSelect> productsSelectedList;

    public ProductsSelectedAdapter(Context context) {
        this.context = context;
        productsSelectedList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_products_selected, parent, false);
        return new ProductsSelectedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position %2 == 1){
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#f5f5f0"));
        }

        ProductSelect productSelect = productsSelectedList.get(position);
        holder.nombre.setText(productSelect.getNombre_producto());
        holder.categoria.setText(productSelect.getNombre_categoria());
        holder.precio.setText(productSelect.getPventa());
        holder.cantidad.setText("1");
        holder.paquete.setText(productSelect.getUndm() + " " + productSelect.getCant());

        DecimalFormat df = new DecimalFormat("#.00");
        String preciototal= df.format(Double.parseDouble(holder.precio.getText().toString()) * Double.parseDouble(holder.cantidad.getText().toString()));
        holder.total.setText(preciototal);


        holder.precio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                int  espacio_texto_total = holder.precio.getText().toString().length();
                int  espacio_texto_total2 = holder.cantidad.getText().toString().length();
                if(espacio_texto_total >= 1 && espacio_texto_total2>=1){
                    double precio_parse = Double.parseDouble(holder.precio.getText().toString());
                    double cantidad_parse = Double.parseDouble(holder.cantidad.getText().toString());
                    double tota_parse = precio_parse * cantidad_parse;
                    holder.total.setText(df.format(tota_parse));

                    productSelect.setCantidad(holder.cantidad.getText().toString());
                } else {
                    holder.total.setText("0.0");
                }
            }
        });

        holder.cantidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                int  espacio_texto_total = holder.cantidad.getText().toString().length();
                int  espacio_texto_total2 = holder.precio.getText().toString().length();
                if(espacio_texto_total >= 1 && espacio_texto_total2>=1){
                    double precio_parse = Double.parseDouble(holder.precio.getText().toString());
                    double cantidad_parse = Double.parseDouble(holder.cantidad.getText().toString());
                    double tota_parse = precio_parse * cantidad_parse;
                    holder.total.setText(df.format(tota_parse));

                    productSelect.setCantidad(holder.cantidad.getText().toString());
                } else {
                    holder.total.setText("0.0");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productsSelectedList.size();
    }

    public void agregarProducto(ProductSelect productSelect) {
        productsSelectedList.add(productSelect);
        notifyDataSetChanged();
    }

    public ArrayList listProducts(){
        return productsSelectedList;
    }


    public void clearList() {
        this.productsSelectedList.clear();
        notifyDataSetChanged();
    }

    public void remove(int position){
        this.productsSelectedList.remove(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //CardviewProductsSelectedBinding cardviewProductsSelectedBinding;
        TextView nombre, categoria;
        EditText cantidad, precio,total, paquete ;
            public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNameSelected);
            categoria = itemView.findViewById(R.id.tvCategorySelected);
            precio = itemView.findViewById(R.id.tvPriceSelected);
            cantidad = itemView.findViewById(R.id.etCantSelected);
            total = itemView.findViewById(R.id.tvTotalSelected);
            paquete = itemView.findViewById(R.id.tvPaquete);
        }
    }

}
