package com.example.icfes_up.interfaces;

import com.example.icfes_up.model.LoginRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Header;
public interface ApiService {

    // Login devuelve UsuarioResponse
    @POST("loginEstudiante")
    Call<UsuarioResponse> loginEstudiante(@Body LoginRequest request);

    // Perfil devuelve PerfilResponse
    @GET("/perfilEstudiante")
    Call<PerfilResponse> perfilEstudiante(@Header("Authorization") String token);

}
