package com.example.icfes_up.lenny_testi;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.icfes_up.MainActivity;
import com.example.icfes_up.R;

public class MiniTest extends AppCompatActivity {

    private LinearLayout contenedorPreguntas;
    private ScrollView scrollView;
    private Button btnFinalizar;

    private String[] preguntas = {
            "1. Si hoy es martes, ¿qué día será en 3 días?",
            "2. ¿Cuál es la capital de Colombia?",
            "3. Si 5x = 20, ¿cuánto vale x?",
            "4. ¿Qué figura tiene 4 lados iguales?",
            "5. Juan tiene 3 manzanas y le dan 2 más, ¿cuántas tiene?",
            "6. ¿Cuál número sigue en la serie: 2, 4, 6, ...?",
            "7. Si A es mayor que B, y B es mayor que C, ¿A es mayor que C?",
            "8. ¿Cuál es el resultado de 12 ÷ 3?",
            "9. ¿Qué planeta es conocido como el planeta rojo?",
            "10. ¿Cuál es el sinónimo de 'contento'?",
            "11. Si una camiseta cuesta $20.000 y hay un descuento de $5.000, ¿cuánto pagas?",
            "12. ¿Qué gas respiramos principalmente?",
            "13. ¿Cuál es el antónimo de 'frío'?",
            "14. ¿Cuántos lados tiene un triángulo?",
            "15. Si un coche viaja a 60 km/h, ¿cuánto tarda en recorrer 120 km?",
            "16. ¿Cuál es la suma de 7 + 8?",
            "17. ¿Qué instrumento mide la temperatura?",
            "18. Si tienes 10 dulces y comes 4, ¿cuántos te quedan?",
            "19. ¿Cuál es el número primo más pequeño?",
            "20. ¿Qué país limita al norte con Colombia?",
            "21. Si hoy es viernes, ¿qué día fue anteayer?",
            "22. ¿Qué símbolo químico representa el agua?",
            "23. ¿Cuántos continentes hay en el mundo?",
            "24. ¿Qué operación da 15 ÷ 3?",
            "25. Si 3 + x = 7, ¿cuánto vale x?"
    };

    private String[][] opciones = {
            {"Viernes", "Lunes", "Jueves", "Miércoles"},
            {"Bogotá", "Medellín", "Cali", "Cartagena"},
            {"2", "4", "5", "10"},
            {"Triángulo", "Círculo", "Cuadrado", "Rectángulo"},
            {"3", "5", "6", "4"},
            {"7", "8", "10", "6"},
            {"Sí", "No", "Igual", "Indeterminado"},
            {"3", "4", "6", "12"},
            {"Marte", "Venus", "Júpiter", "Saturno"},
            {"Triste", "Contento", "Serio", "Molesto"},
            {"15.000", "20.000", "25.000", "10.000"},
            {"Oxígeno", "Nitrógeno", "Hidrógeno", "Dióxido de carbono"},
            {"Caliente", "Frío", "Claro", "Oscuro"},
            {"3", "4", "5", "6"},
            {"2 h", "1 h", "3 h", "4 h"},
            {"14", "15", "16", "17"},
            {"Termómetro", "Regla", "Barómetro", "Brújula"},
            {"6", "4", "5", "3"},
            {"0", "1", "2", "3"},
            {"Venezuela", "Brasil", "Perú", "Ecuador"},
            {"Miércoles", "Jueves", "Viernes", "Martes"},
            {"H2O", "O2", "CO2", "NaCl"},
            {"5", "6", "7", "8"},
            {"5", "6", "4", "3"},
            {"4", "2", "3", "5"}
    };

    private int[] respuestasCorrectas = {
            0,0,1,2,1,3,0,1,0,1,
            0,0,0,0,1,2,0,1,1,0,
            0,0,1,0,0
    };

    private int[] respuestasUsuario = new int[25];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_test);

        scrollView = findViewById(R.id.scrollViewPreguntas);
        contenedorPreguntas = findViewById(R.id.contenedorPreguntas);
        btnFinalizar = findViewById(R.id.btnFinalizarMiniTest);

        for (int i = 0; i < respuestasUsuario.length; i++) respuestasUsuario[i] = -1;

        mostrarPreguntas();

        btnFinalizar.setOnClickListener(v -> validarYCalcular());
    }

    private void mostrarPreguntas() {
        for (int i = 0; i < preguntas.length; i++) {
            CardView card = new CardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(16,16,16,16);
            card.setLayoutParams(cardParams);
            card.setRadius(12f);
            card.setCardElevation(8f);
            card.setUseCompatPadding(true);
            card.setCardBackgroundColor(Color.WHITE);

            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(16,16,16,16);

            TextView tvPregunta = new TextView(this);
            tvPregunta.setText(preguntas[i]);
            tvPregunta.setTextSize(16f);
            tvPregunta.setTextColor(Color.BLACK);
            layout.addView(tvPregunta);

            RadioGroup rg = new RadioGroup(this);
            rg.setOrientation(RadioGroup.VERTICAL);
            int finalI = i;

            for (int j = 0; j < 4; j++) {
                RadioButton rb = new RadioButton(this);
                rb.setText(opciones[i][j]);
                rb.setTextColor(Color.BLACK);
                rb.setId(View.generateViewId());
                int finalJ = j;
                rb.setOnClickListener(v -> respuestasUsuario[finalI] = finalJ);
                rg.addView(rb);
            }

            layout.addView(rg);
            card.addView(layout);
            contenedorPreguntas.addView(card);
        }
    }

    private void validarYCalcular() {
        StringBuilder preguntasFaltantes = new StringBuilder();
        int primerFaltante = -1;

        for (int i = 0; i < respuestasUsuario.length; i++) {
            if (respuestasUsuario[i] == -1) {
                if (primerFaltante == -1) primerFaltante = i;
                preguntasFaltantes.append((i + 1)).append(", ");
            }
        }

        if (preguntasFaltantes.length() > 0) {
            preguntasFaltantes.setLength(preguntasFaltantes.length() - 2);
            Toast.makeText(this,
                    "Debes responder todas las preguntas. Faltan: " + preguntasFaltantes.toString(),
                    Toast.LENGTH_LONG).show();

            View preguntaFaltanteView = contenedorPreguntas.getChildAt(primerFaltante);
            if (preguntaFaltanteView != null) {
                scrollView.smoothScrollTo(0, preguntaFaltanteView.getTop());
            }
            return;
        }

        calcularResultado();
    }

    private void calcularResultado() {
        int correctas = 0;
        for (int i = 0; i < respuestasUsuario.length; i++) {
            if (respuestasUsuario[i] == respuestasCorrectas[i]) correctas++;
        }

        int porcentaje = (correctas * 100) / preguntas.length;
        mostrarResultado(porcentaje, correctas);
    }

    private void mostrarResultado(int porcentaje, int correctas) {
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32,32,32,32);
        layout.setBackgroundColor(Color.WHITE);

        TextView tv = new TextView(this);
        tv.setText("Respondiste correctamente " + correctas + " de " + preguntas.length + " preguntas.\nPorcentaje: " + porcentaje + "%");
        tv.setTextColor(Color.BLACK);
        tv.setTextSize(18f);
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        layout.addView(tv);

        Button btnCerrar = new Button(this);
        btnCerrar.setText("Aceptar");
        btnCerrar.setBackgroundColor(Color.parseColor("#ADD8E6"));
        btnCerrar.setTextColor(Color.BLACK);
        btnCerrar.setOnClickListener(v -> {
            dialog.dismiss();

            SharedPreferences prefs = getSharedPreferences("mi_app", MODE_PRIVATE);
            prefs.edit().putBoolean("miniTestTerminado", true).apply();

            Intent intent = new Intent(MiniTest.this, MainActivity.class);
            intent.putExtra("abrirHome", true);
            startActivity(intent);
            finish();
        });

        layout.addView(btnCerrar);

        dialog.setContentView(layout);
        dialog.getWindow().setLayout(
                (int)(getResources().getDisplayMetrics().widthPixels*0.9),
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        dialog.show();
    }
}
