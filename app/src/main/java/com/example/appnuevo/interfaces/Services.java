package com.example.appnuevo.interfaces;

import com.example.appnuevo.models.LoginRequest;
import com.example.appnuevo.models.LoginResponse;
import com.example.appnuevo.models.ProductResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Services {

    @POST("login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    //@POST("login")
    //Call<LoginResponse> userLogin(@Query("user") String user, @Query("password") String password, @Query("tipo") String tipo);

    @POST("producto/busqueda")
    Call<ProductResponse> searchProductMatch(@Query("texto") String texto, @Query("page") int page);
}
