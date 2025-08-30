package com.example.icfes_up.interfaces;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private Context context;

    public AuthInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        SharedPreferences prefs = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = prefs.getString("token", null);

        Request.Builder builder = chain.request().newBuilder();
        if (token != null) {
            builder.addHeader("Authorization", "Bearer " + token);
        }

        return chain.proceed(builder.build());
    }
}
