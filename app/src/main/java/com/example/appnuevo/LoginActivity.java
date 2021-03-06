package com.example.appnuevo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appnuevo.apis.ApiClient;
import com.example.appnuevo.models.LoginRequest;
import com.example.appnuevo.models.LoginResponse;
import com.example.appnuevo.models.Usuario;
import com.example.appnuevo.ui.dialogs.LoadingDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "API";
    EditText user, password, route;
    Button btnLogin;
    static public Usuario usuario;
    static public String IP;
    LoadingDialog loadingDialog;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.edUser);
        password = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        route = findViewById(R.id.etRoute);
        loadingDialog = new LoadingDialog(LoginActivity.this);

        sharedPref = getPreferences(Context.MODE_PRIVATE);

        //guardarPreferencias(user.getText().toString(), password.getText().toString());

        String usuario = sharedPref.getString("user", null);
        String contra = sharedPref.getString("password", null);

        if (usuario != null && contra !=null){
            user.setText(usuario);
            password.setText(contra);
        }


        String ruta = sharedPref.getString("route", null);
        if (ruta != null) {
            route.setText(ruta);

            IP = ruta;
        } else {
            route.setText("Ingrese IP");
        }



        route.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("route", route.getText().toString());
                editor.apply();
                IP = route.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void login(View view){

        Log.e(TAG, "EL IP"+ IP);
        loadingDialog.startLoadingDialog();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUser(user.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        loginRequest.setTipo("1");

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<LoginResponse> call,@NonNull Response<LoginResponse> response) {
                if(response.isSuccessful()){
                   LoginResponse loginResponse = response.body();
                    ArrayList<Usuario> coleccion = loginResponse.getData();
                    boolean estado = loginResponse.isStatus();
                    boolean license = loginResponse.isLicense();
                    if (estado){
                        if (license) {
                            //guardando usuario final
                            guardarPreferencias(user.getText().toString(), password.getText().toString());
                            usuario = coleccion.get(0);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            Toast.makeText(LoginActivity.this,coleccion.get(0).getNombre()+" Logeado", Toast.LENGTH_LONG).show();
                            loadingDialog.dismissDialog();
                        } else {
                            Toast.makeText(LoginActivity.this,"La licensia a expirado", Toast.LENGTH_SHORT).show();
                            loadingDialog.dismissDialog();
                        }


                    }else {
                        loadingDialog.dismissDialog();
                        Toast.makeText(LoginActivity.this,"Datos incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    loadingDialog.dismissDialog();
                    Toast.makeText(LoginActivity.this,"Hubo un problema : " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<LoginResponse> call, Throwable t) {
                loadingDialog.dismissDialog();
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(LoginActivity.this,"No hay conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void guardarPreferencias(String user, String password) {
        //sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user", user);
        editor.putString("password", password);
        editor.apply();
    }

}