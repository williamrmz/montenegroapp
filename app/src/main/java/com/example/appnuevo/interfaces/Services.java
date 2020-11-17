package com.example.appnuevo.interfaces;

import com.example.appnuevo.models.LoginRequest;
import com.example.appnuevo.models.LoginResponse;
import com.example.appnuevo.models.ProductResponse;
import com.example.appnuevo.models.Venta;
import com.example.appnuevo.models.VentaResponse;
import com.example.appnuevo.models.ResponseAPI;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Services {

    @POST("login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    //@POST("login")
    //Call<LoginResponse> userLogin(@Query("user") String user, @Query("password") String password, @Query("tipo") String tipo);

    @POST("producto/busqueda")
    Call<ProductResponse> searchProductMatch(@Query("texto") String texto);


    @POST("ventas")
    Call<Venta> registerSale(@Body Venta venta);

    @GET("ventas")
    Call<VentaResponse> listSales(@Query("idusuario") int idusuario, @Query("page") int page);

    @GET("cliente")
    Call<ResponseAPI> listClients();

}
