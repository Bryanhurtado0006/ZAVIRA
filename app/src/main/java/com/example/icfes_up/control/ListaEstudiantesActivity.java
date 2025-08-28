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
import com.example.icfes_up.model.Usuario;
import com.example.icfes_up.lenny_testi.SplashAnimation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaEstudiantesActivity extends AppCompatActivity {

    private static final String TAG = "ListaEstudiantes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_estudiantes);

        // Consumir API y mostrar estudiantes
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        apiService.perfilEstudiante().enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Usuario> estudiantes = response.body();
                    for(Usuario u : estudiantes){
                        // Mensaje de ejemplo para cada estudiante
                        Toast.makeText(ListaEstudiantesActivity.this,
                                u.getNombreUsuario() + " " + u.getApellido(),
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ListaEstudiantesActivity.this, "Error código: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error lista: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(ListaEstudiantesActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure lista", t);
            }
        });

        // Delay de 2 segundos antes de abrir SplashAnimation
        new Handler().postDelayed(() -> {
            startActivity(new Intent(ListaEstudiantesActivity.this, SplashAnimation.class));
            finish(); // Cierra ListaEstudiantesActivity
        }, 2000); // 2000ms = 2 segundos
    }
}
