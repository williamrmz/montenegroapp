package com.example.appnuevo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private static final String TAG = "API";
    Context context;
    ArrayList<Product> productsList;
    private RecyclerViewClickInterface recyclerViewClickInterfaceProduct;

    public ProductsAdapter(Context context, RecyclerViewClickInterface recyclerViewClickInterfaceProduct) {
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
        Product product = productsList.get(position);
        holder.nombre.setText(product.getNombre());
        holder.categoria.setText(product.getCategoria());

        if (product.getPrecios() != null){
            int cantidadPrecios = product.getPrecios().size();
            ArrayList<Precios> precios = new ArrayList<Precios>();
            for (int i=0; i<cantidadPrecios; i++){
                Precios prices = new Precios();
                prices.setIdprecio(product.getPrecios().get(i).getIdprecio());
                prices.setNombre(product.getPrecios().get(i).getNombre());
                prices.setPcompra(product.getPrecios().get(i).getPcompra());
                prices.setPventa(product.getPrecios().get(i).getPventa());
                prices.setPorcentaje(product.getPrecios().get(i).getPorcentaje());
                prices.setCantidadunidad(product.getPrecios().get(i).getCantidadunidad());
                precios.add(prices);
                //Log.e(TAG, "SPINNER : "+prices.getPrecio());
                //Log.e(TAG, "SPINNER : "+ gson.toJson( precios));
            }
            //Gson gson = new Gson();
            //Log.e(TAG, "SPINNER : "+ gson.toJson(precios));
            ArrayAdapter<Precios> adaptador = new ArrayAdapter<Precios>(context, R.layout.support_simple_spinner_dropdown_item, precios);
            adaptador.setDropDownViewResource( R.layout.support_simple_spinner_dropdown_item);
            holder.precios.setAdapter(adaptador);

            holder.precios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    Precios precioinfo = (Precios) adapterView.getSelectedItem();
                    //Log.e(TAG, "SPINNER : "+precioinfo.getPrecioVenta());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
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
                    recyclerViewClickInterfaceProduct.onItemClick(getAdapterPosition());
                }
            });
        }

    }


}