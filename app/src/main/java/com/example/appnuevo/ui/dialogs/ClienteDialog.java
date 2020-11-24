package com.example.appnuevo.ui.dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.appnuevo.R;
import com.example.appnuevo.apis.ApiClient;
import com.example.appnuevo.models.Cliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ClienteDialog extends DialogFragment {
    private static final String TAG = "API";
    EditText nombre, dni;
    Button btnAdd;
    LoadingDialog loadingDialog;
    Cliente cliente;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_cliente, container, true);


        nombre = view.findViewById(R.id.etNombreClient);
        dni = view.findViewById(R.id.etDniClient);
        loadingDialog = new LoadingDialog(getActivity());
        btnAdd = view.findViewById(R.id.btnAddClient);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombretexto = nombre.getText().toString();
                String dnitexto = dni.getText().toString();

                if (nombretexto.isEmpty()) {
                    Toast.makeText(getContext(),"Elija un nombre", Toast.LENGTH_SHORT).show();
                } else {
                    if (dnitexto.isEmpty()) {
                        addClient(nombretexto, "SN");
                    } else {
                        addClient(nombretexto, dnitexto);
                    }
                }
            }
        });

        return view;
    }

    public void addClient(String nombre, String dni){
        loadingDialog.startLoadingDialog();
        Call<Cliente> call = ApiClient.getUserService().addClient(nombre, dni);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if (response.isSuccessful()){

                    cliente = response.body();
                    loadingDialog.dismissDialog();
                    sendResult(2);
                    dismiss();
                    //Log.e(TAG, "DATOS CLIENTE: "+ cliente.getIdcliente());
                    Toast.makeText(getContext(),"Se agregó cliente", Toast.LENGTH_SHORT).show();
                } else {
                    loadingDialog.dismissDialog();
                    Toast.makeText(getContext(),"No se pudo agregar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                loadingDialog.dismissDialog();
                Toast.makeText(getContext(),"Error en conexión", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void sendResult(int REQUEST_CODE) {
        Intent intent = new Intent();
        intent.putExtra("idcliente", String.valueOf(cliente.getIdcliente()));
        intent.putExtra("nombre", cliente.getNombre());
        getTargetFragment().onActivityResult(getTargetRequestCode(), REQUEST_CODE, intent);
    }


}
