package com.example.icfes_up.interfaces;

import com.example.icfes_up.model.LoginRequest;
import com.example.icfes_up.model.UsuarioResponse;
import com.example.icfes_up.model.PerfilResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/loginEstudiante")
    Call<UsuarioResponse> loginEstudiante(@Body LoginRequest request);

    @GET("/perfilEstudiante")
    Call<PerfilResponse> perfilEstudiante();  // ✅ corregido
}
