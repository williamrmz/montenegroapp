package com.example.appnuevo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appnuevo.apis.ApiClient;
import com.example.appnuevo.models.LoginRequest;
import com.example.appnuevo.models.LoginResponse;
import com.example.appnuevo.models.Usuario;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "MONTENEGRO";
    EditText user, password, tipo;
    Button btnLogin;
    ArrayList<Usuario> listaUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.edUser);
        password = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);

    }

    public void login(View view){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUser(user.getText().toString());
        loginRequest.setPassword(password.getText().toString());
        loginRequest.setTipo("1");

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        //Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(user.getText().toString() , password.getText().toString(), "1");
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    ArrayList<Usuario> coleccion = loginResponse.getData();
                    boolean estado = loginResponse.isStatus();
                    if (estado){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        Toast.makeText(LoginActivity.this,coleccion.get(0).getNombre()+" Logeado", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(LoginActivity.this,"Datos incorrectos", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this,"Hubo un problema : " + response.errorBody(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                Toast.makeText(LoginActivity.this,"No hay conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

}