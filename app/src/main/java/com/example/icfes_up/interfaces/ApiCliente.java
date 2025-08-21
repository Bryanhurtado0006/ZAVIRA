package com.example.icfes_up.interfaces;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiCliente {

    private static final String BASE_URL = "http://localhost:3333/login/estudiante"; // Cambia por tu URL real
    private static Retrofit retrofit = null;

    public static Retrofit getCliente() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
