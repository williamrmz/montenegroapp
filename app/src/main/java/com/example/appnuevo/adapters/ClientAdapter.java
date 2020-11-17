package com.example.appnuevo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appnuevo.R;
import com.example.appnuevo.models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClientAdapter extends ArrayAdapter<Cliente> {
    private ArrayList<Cliente> clientesFull;
    public ClientAdapter(@NonNull Context context, @NonNull ArrayList<Cliente> clientes) {
        super(context, 0, clientes);
        clientesFull = new ArrayList<>(clientes);
    }

    /*@NonNull
    @Override
    public Filter getFilter() {
        return clientsFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if ( convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout)
        }
    }

    private Filter clientsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            ArrayList<Cliente> suggetions = new ArrayList<>();
            if(charSequence == null || charSequence.length()==0){
                suggetions.addAll(clientesFull);
            } else {
                String filterPattern = charSequence.toString();

                for (Cliente cliente : clientesFull){
                    if (cliente.getNombre().contains(filterPattern)) {
                        suggetions.add(cliente);
                    }
                }
            }
            results.values = suggetions;
            results.count = suggetions.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            clear();
            addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Cliente) resultValue).getNombre();
        }
    };

    */
}
