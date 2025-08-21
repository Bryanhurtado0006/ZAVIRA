package com.example.icfes_up.control;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icfes_up.R;
import com.example.icfes_up.interfaces.ApiCliente;
import com.example.icfes_up.interfaces.ResApiIcfes;
import com.example.icfes_up.lenny_testi.SplashAnimation;
import com.example.icfes_up.model.LoginRequest;
import com.example.icfes_up.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText edtDocumento, edtPassword;
    private Button btnLogin;
    private ResApiIcfes api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar vistas
        edtDocumento = findViewById(R.id.input_correo); // número de documento
        edtPassword = findViewById(R.id.input_contrasena);
        btnLogin = findViewById(R.id.btn_iniciar_sesion);

        // Inicializar API
        api = ApiCliente.getCliente().create(ResApiIcfes.class);

        // Click en login
        btnLogin.setOnClickListener(v -> login());
    }

    private void login() {
        String documento = edtDocumento.getText().toString().trim();
        String contrasena = edtPassword.getText().toString().trim();

        if (documento.isEmpty() || contrasena.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        // Crear objeto de login
        LoginRequest loginRequest = new LoginRequest(documento, contrasena);

        // Consumir API
        api.login(loginRequest).enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(MainActivity.this, "Login correcto: " + response.body().getNombre(), Toast.LENGTH_SHORT).show();

                    // Abrir siguiente pantalla (SplashAnimation u otra)
                    Intent intent = new Intent(MainActivity.this, SplashAnimation.class);
                    startActivity(intent);
                    finish(); // cerrar login
                } else {
                    Toast.makeText(MainActivity.this, "Documento o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de conexión: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
