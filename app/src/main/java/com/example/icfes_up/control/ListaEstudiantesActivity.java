package com.example.icfes_up.control;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icfes_up.R;
import com.example.icfes_up.interfaces.ApiClient;
import com.example.icfes_up.interfaces.ApiService;
import com.example.icfes_up.interfaces.UsuarioResponse;
import com.example.icfes_up.lenny_testi.SplashAnimation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaEstudiantesActivity extends AppCompatActivity {

    private static final String TAG = "ListaEstudiantes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estudiantes);

        // ⚠️ Desactivado: consumo de API perfil
        /*
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        apiService.perfilEstudiante().enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    UsuarioResponse usuario = response.body();
                    Toast.makeText(ListaEstudiantesActivity.this,
                            usuario.getNombreUsuario() + " " + usuario.getApellido(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ListaEstudiantesActivity.this, "Error código: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error perfil: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(ListaEstudiantesActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure perfil", t);
            }
        });
        */

        // ⚠️ Desactivado: delay hacia SplashAnimation
        /*
        new Handler().postDelayed(() -> {
            startActivity(new Intent(ListaEstudiantesActivity.this, SplashAnimation.class));
            finish();
        }, 2000);
        */
    }
}
