package com.example.icfes_up.logueo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icfes_up.databinding.ActivityMenuLogueoBinding;
import com.example.icfes_up.lenny_testi.SplashAnimation;

public class Menu_Logueo extends AppCompatActivity {

    private ActivityMenuLogueoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Verificar si ya existe un token
        SharedPreferences prefs = getSharedPreferences("icfes_prefs", MODE_PRIVATE);
        String token = prefs.getString("token", null);

        if (token != null && !token.isEmpty()) {
            // Usuario ya logueado, ir directo a SplashAnimation
            startActivity(new Intent(this, SplashAnimation.class));
            finish(); // Evitar volver a este activity
            return;
        }

        // Si no hay token, mostrar el menú de login/registro
        binding = ActivityMenuLogueoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegistroAdministrador.setOnClickListener(v -> {
            startActivity(new Intent(this, Registro_Administrador.class));
        });

        binding.btnRegistroUsuario.setOnClickListener(v -> {
            startActivity(new Intent(this, Registro_Usuario.class));
        });

        binding.btnInicioSesion.setOnClickListener(v -> {
            startActivity(new Intent(this, Inicio_Sesion.class));
        });
    }
}
