package com.example.icfes_up.Mundos.ui.lista;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icfes_up.R;
import com.example.icfes_up.databinding.ActivityMundos2Binding;

public class Mundos_activity extends AppCompatActivity {

    private ActivityMundos2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMundos2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.btnComenzar.setOnClickListener(v -> {
            //  oculto la bienvenida y mostramos el framento
            binding.layoutBienvenida.setVisibility(View.GONE);
            binding.containerFragmentMundos.setVisibility(View.VISIBLE);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerFragmentMundos, new ListaMundosFragment())
                    .addToBackStack(null)
                    .commit();
        });
    }
}

