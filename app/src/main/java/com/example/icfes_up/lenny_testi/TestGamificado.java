package com.example.icfes_up.lenny_testi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icfes_up.R;

public class TestGamificado extends AppCompatActivity {

    private LinearLayout containerTarjetas;
    private String[] materias = {
            "Lectura Crítica",
            "Matemáticas",
            "Ciencias Naturales",
            "Sociales y Ciudadanas",
            "Inglés",
            "Perfil y Estilo de Aprendizaje"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gamificado);

        containerTarjetas = findViewById(R.id.containerTarjetas);

        for (String materia : materias) {
            crearTarjeta(materia);
        }
    }

    private void crearTarjeta(String nombre) {
        LinearLayout tarjeta = new LinearLayout(this);
        tarjeta.setOrientation(LinearLayout.VERTICAL);
        tarjeta.setPadding(24, 24, 24, 24);
        tarjeta.setGravity(Gravity.CENTER);
        tarjeta.setBackgroundColor(Color.parseColor("#ADD8E6"));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                200
        );
        params.setMargins(0, 16, 0, 16);
        tarjeta.setLayoutParams(params);
        tarjeta.setClickable(true);

        TextView tvNombre = new TextView(this);
        tvNombre.setText(nombre);
        tvNombre.setTextSize(20f);
        tvNombre.setTypeface(Typeface.DEFAULT_BOLD);
        tvNombre.setTextColor(Color.BLACK);
        tarjeta.addView(tvNombre);

        tarjeta.setOnClickListener(v -> {
            ScaleAnimation scale = new ScaleAnimation(
                    1f, 1.05f, 1f, 1.05f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                    ScaleAnimation.RELATIVE_TO_SELF, 0.5f
            );
            scale.setDuration(200);
            scale.setFillAfter(true);
            tarjeta.startAnimation(scale);

            if (nombre.equals("Perfil y Estilo de Aprendizaje")) {
                mostrarPerfil();
            } else {
                mostrarSubtemas(nombre);
            }
        });

        containerTarjetas.addView(tarjeta);
    }

    private void mostrarSubtemas(String materia) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(materia + " - Subtemas");

        String mensaje = "";
        switch (materia) {
            case "Lectura Crítica":
                mensaje = "- Comprensión lectora\n- Inferencias\n- Interpretación\n- Argumentación";
                break;
            case "Matemáticas":
                mensaje = "- Álgebra\n- Geometría\n- Probabilidad y Estadística\n- Razonamiento Lógico";
                break;
            case "Ciencias Naturales":
                mensaje = "- Biología\n- Física\n- Química\n- Medio ambiente";
                break;
            case "Sociales y Ciudadanas":
                mensaje = "- Historia\n- Geografía\n- Constitución\n- Economía básica";
                break;
            case "Inglés":
                mensaje = "- Gramática\n- Comprensión de textos\n- Listening\n- Vocabulary";
                break;
        }

        builder.setMessage(mensaje);
        builder.setPositiveButton("Cerrar", null);
        builder.show();
    }

    private void mostrarPerfil() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Perfil y Estilo de Aprendizaje");
        builder.setMessage(
                "Aquí se mostrará tu estilo de aprendizaje detectado en el test inicial.\n\n" +
                        "- Descripción de tu estilo\n" +
                        "- Estrategias recomendadas\n" +
                        "- Actividades sugeridas"
        );
        builder.setPositiveButton("Cerrar", null);
        builder.show();
    }
}
