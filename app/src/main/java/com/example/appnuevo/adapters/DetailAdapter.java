package com.example.appnuevo.adapters;

import android.content.Context;
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

    public DetailAdapter(Context context) {
        this.context = context;
        detalleVentasList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //DetalleVenta detalleVenta = detalleVentasList.get(position);
        //holder.nombre.setText(detalleVenta.getNombre_producto());
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
        EditText nombre, precio, cantidad, total;
        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.tvNombreDetail);
            precio = itemView.findViewById(R.id.tvPrecioDetail);
            cantidad = itemView.findViewById(R.id.tvCantidadDetail);
            total = itemView.findViewById(R.id.tvTotalDetail);
        }
    }
}
