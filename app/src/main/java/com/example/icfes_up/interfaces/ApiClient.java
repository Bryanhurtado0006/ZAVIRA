package com.example.icfes_up.interfaces;

import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://zavira-sena-v6.onrender.com/";
    private static Retrofit retrofit;

    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            SharedPreferences prefs = context.getSharedPreferences("icfes_prefs", Context.MODE_PRIVATE);
            String token = prefs.getString("token", null);

            OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
            if (token != null) {
                clientBuilder.addInterceptor(new AuthInterceptor(token));
            }

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(clientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
