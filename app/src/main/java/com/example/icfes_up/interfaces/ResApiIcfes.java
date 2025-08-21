package com.example.icfes_up.interfaces;

import com.example.icfes_up.model.LoginRequest;
import com.example.icfes_up.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ResApiIcfes {
    @POST("login")
    Call<UsuarioResponse> login(@Body LoginRequest request);
}
