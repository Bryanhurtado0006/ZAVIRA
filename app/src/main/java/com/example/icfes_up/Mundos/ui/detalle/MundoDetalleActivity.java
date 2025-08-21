package com.example.icfes_up.Mundos.ui.detalle;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.icfes_up.Mundos.competencia.detail.CompetenciaDetailFragment;
import com.example.icfes_up.Mundos.ui.bienvenida.BienvenidaMundoFragment;
import com.example.icfes_up.R;
import com.google.android.material.appbar.MaterialToolbar;

public class MundoDetalleActivity extends AppCompatActivity
        implements BienvenidaMundoFragment.OnBienvenidaListener {

    private static final String EXTRA_NOMBRE = "nombre_mundo";
    private static final String EXTRA_IMAGEN = "imagen_mundo_res";

    private String nombreMundo;
    private int resImagenMundo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mundo_detalle);

        MaterialToolbar toolbar = findViewById(R.id.toolbarMundoDetalle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onSupportNavigateUp());

        nombreMundo    = getIntent().getStringExtra(EXTRA_NOMBRE);
        resImagenMundo = getIntent().getIntExtra(EXTRA_IMAGEN, R.drawable.islandd);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            R.id.containerDetalle,
                            BienvenidaMundoFragment.newInstance(nombreMundo, resImagenMundo)
                    )
                    .commit();
            getSupportActionBar().setTitle(nombreMundo);
        }
    }

    @Override
    public void onEmpezarClicked(String nombreCompetencia) {
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.setCustomAnimations(
                R.anim.enter_from_right,
                R.anim.exit_to_left,
                R.anim.enter_from_left,
                R.anim.exit_to_right
        );
        getSupportActionBar().setTitle(nombreCompetencia);
        tx.replace(
                R.id.containerDetalle,
                CompetenciaDetailFragment.newInstance(nombreCompetencia)
        );
        tx.addToBackStack(null);
        tx.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return true;
        }
        return super.onSupportNavigateUp();
    }
}

