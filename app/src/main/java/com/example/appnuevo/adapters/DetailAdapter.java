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
import com.example.appnuevo.models.DetalleVenta;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    private static final String TAG = "API";
    Context context;
    ArrayList<DetalleVenta> detalleVentasList;

    public DetailAdapter(Context context, ArrayList<DetalleVenta> detalleVentasList) {
        this.context = context;
        this.detalleVentasList = detalleVentasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       DetalleVenta detalleVenta = detalleVentasList.get(position);

        if(detalleVentasList.size() >= 1){
            holder.nombre.setText(detalleVenta.getNombre_producto());
            holder.cantidad.setText("Canitdad : " + String.valueOf(detalleVenta.getCantidad()));
            holder.precio.setText("Precio : " + String.valueOf(detalleVenta.getPrecio_unitario()));
            double totalventa = detalleVenta.getCantidad() * detalleVenta.getPrecio_unitario();
            holder.total.setText("Total S/." + String.valueOf(totalventa));
        } else {
            holder.nombre.setText("No hay detalles");
        }
    }

    @Override
    public int getItemCount() {
        return detalleVentasList.size();
    }

    public void addListDetails(ArrayList<DetalleVenta> detalleVentasList) {
        detalleVentasList.addAll(detalleVentasList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, precio, cantidad, total;
        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreDetail);
            precio = itemView.findViewById(R.id.tvPrecioDetail);
            cantidad = itemView.findViewById(R.id.tvCantidadDetail);
            total = itemView.findViewById(R.id.tvTotalDetail);
        }
    }
}
