package com.example.appnuevo.adapters;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appnuevo.R;
import com.example.appnuevo.interfaces.SaleItemClickInterface;
import com.example.appnuevo.models.Venta;

import java.util.ArrayList;

public class SalesAdapter extends RecyclerView.Adapter<SalesAdapter.ViewHolder> {

    private static final String TAG = "API";
    Context context;
    ArrayList<Venta> ventasList;
    SaleItemClickInterface saleItemClickInterface;

    public SalesAdapter(Context context, SaleItemClickInterface sici) {
        this.context = context;
        ventasList = new ArrayList<>();
        this.saleItemClickInterface = sici;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_sales, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Venta venta = ventasList.get(position);
        holder.idVenta.setText(String.valueOf(venta.getIdventa()));
        holder.fecha.setText(venta.getFecha_venta());
        holder.doc.setText(venta.getTipo_documento());
    }

    @Override
    public int getItemCount() {
        return ventasList.size();
    }

    public void addSales(ArrayList<Venta> ventaList) {
        ventasList.addAll(ventaList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView idVenta, fecha, doc;
        public ViewHolder(View itemView) {
            super(itemView);
            idVenta = itemView.findViewById(R.id.tvIdVentaSale);
            fecha = itemView.findViewById(R.id.tvFechaVenta);
            doc = itemView.findViewById(R.id.tvTipoDoc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saleItemClickInterface.sendDetail(getAdapterPosition());
                }
            });

            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    //menu.setHeaderTitle("CRUD");
                    saleItemClickInterface.chargeSale(getAdapterPosition());
                    menu.add(0,1,0,"PDF");
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return false;
                }
            });
        }

    }


}
