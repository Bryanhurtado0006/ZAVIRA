package com.example.icfes_up.logueo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icfes_up.R;
import com.example.icfes_up.interfaces.ApiClient;
import com.example.icfes_up.interfaces.ApiService;
import com.example.icfes_up.model.LoginRequest;
import com.example.icfes_up.interfaces.UsuarioResponse;
import com.example.icfes_up.interfaces.PerfilResponse;
import com.example.icfes_up.lenny_testi.SplashAnimation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inicio_Sesion extends AppCompatActivity {

    private static final String TAG = "Inicio_Sesion";

    private EditText etDocumento, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        etDocumento = findViewById(R.id.input_documento);
        etPassword = findViewById(R.id.input_contrasena);
        btnLogin = findViewById(R.id.btn_iniciar_sesion);

        btnLogin.setOnClickListener(v -> {
            String documento = etDocumento.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (documento.isEmpty()) {
                etDocumento.setError("Ingresa tu número de documento");
                etDocumento.requestFocus();
                return;
            }

            if (password.isEmpty()) {
                etPassword.setError("Ingresa tu contraseña");
                etPassword.requestFocus();
                return;
            }

            // Llamada a la API
            loginUser(documento, password);
        });
    }

    private void loginUser(String documento, String password) {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);
        LoginRequest request = new LoginRequest(documento, password);

        apiService.loginEstudiante(request).enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                Log.d(TAG, "Código respuesta login: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    String token = response.body().getToken();

                    if (token != null && !token.isEmpty()) {
                        // Guardar token
                        SharedPreferences prefs = getSharedPreferences("icfes_prefs", MODE_PRIVATE);
                        prefs.edit().putString("token", token).apply();

                        Toast.makeText(Inicio_Sesion.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                        // ✅ Luego de login, consultamos perfil con el token
                        getPerfil(token);

                    } else {
                        Toast.makeText(Inicio_Sesion.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Token vacío en la respuesta");
                    }
                } else {
                    Toast.makeText(Inicio_Sesion.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error login: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(Inicio_Sesion.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure login", t);
            }
        });
    }

    private void getPerfil(String token) {
        ApiService apiService = ApiClient.getClient(this).create(ApiService.class);

        apiService.perfilEstudiante("Bearer " + token).enqueue(new Callback<PerfilResponse>() {
            @Override
            public void onResponse(Call<PerfilResponse> call, Response<PerfilResponse> response) {
                Log.d(TAG, "Código respuesta perfil: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    PerfilResponse perfil = response.body();
                    Log.d(TAG, "Perfil recibido: " + perfil.getMensaje());

                    // ✅ Ya tenemos el perfil, ahora sí ir al SplashAnimation
                    startActivity(new Intent(Inicio_Sesion.this, SplashAnimation.class));
                    finish();
                } else {
                    Toast.makeText(Inicio_Sesion.this, "Error al obtener perfil", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error perfil: " + response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PerfilResponse> call, Throwable t) {
                Toast.makeText(Inicio_Sesion.this, "Error de conexión perfil: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure perfil", t);
            }
        });
    }
}
