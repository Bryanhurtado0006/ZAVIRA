package com.example.icfes_up.lenny_testi;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.icfes_up.R;

public class TestLottie extends AppCompatActivity {

    // --- VISTAS PRINCIPALES ---
    private CardView cardIsla;
    private View btnLectura, btnMatematicas, btnCiencias, btnSociales, btnIngles, btnPerfil;
    private TextView tituloArea;
    private LinearLayout contenedorSubtemas;
    private View panelSubtemas;

    // --- SUBTEMAS POR ÁREA ---
    private final String[] subtemasLectura = {
            "Comprensión lectora", "Inferencias", "Interpretación", "Argumentación", "Propósito del autor"
    };
    private final String[] subtemasMatematicas = {
            "Aritmética", "Álgebra", "Geometría", "Probabilidad y Estadística", "Razonamiento lógico"
    };
    private final String[] subtemasCiencias = {
            "Biología", "Física", "Química", "Método científico", "Ambiente y sostenibilidad"
    };
    private final String[] subtemasSociales = {
            "Historia", "Geografía", "Constitución y ciudadanía", "Economía básica", "Cultura y sociedad"
    };
    private final String[] subtemasIngles = {
            "Reading", "Vocabulary", "Grammar", "Uso del contexto", "Paráfrasis"
    };

    // --- ANIMACIONES ---
    private ScaleAnimation pulse() {
        ScaleAnimation s = new ScaleAnimation(
                1f, 1.05f, 1f, 1.05f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        );
        s.setDuration(140);
        s.setFillAfter(false);
        return s;
    }

    private AlphaAnimation fadeIn(long dur) {
        AlphaAnimation a = new AlphaAnimation(0f, 1f);
        a.setDuration(dur);
        a.setFillAfter(true);
        return a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Usa el XML que te paso abajo: res/layout/activity_test_lottie.xml
        setContentView(R.layout.activity_test_lottie);

        // --- REFERENCIAS ---
        cardIsla         = findViewById(R.id.cardIsla);
        btnLectura       = findViewById(R.id.btnLectura);
        btnMatematicas   = findViewById(R.id.btnMatematicas);
        btnCiencias      = findViewById(R.id.btnCiencias);
        btnSociales      = findViewById(R.id.btnSociales);
        btnIngles        = findViewById(R.id.btnIngles);
        btnPerfil        = findViewById(R.id.btnPerfil);

        tituloArea       = findViewById(R.id.tituloArea);
        contenedorSubtemas = findViewById(R.id.contenedorSubtemas);
        panelSubtemas    = findViewById(R.id.panelSubtemas);

        // Animación suave al entrar
        cardIsla.startAnimation(fadeIn(450));

        // --- LISTENERS DE ÁREAS (sobre la isla) ---
        btnLectura.setOnClickListener(v -> {
            v.startAnimation(pulse());
            mostrarSubtemas("Lectura Crítica", subtemasLectura, "#E3F2FD");
        });

        btnMatematicas.setOnClickListener(v -> {
            v.startAnimation(pulse());
            mostrarSubtemas("Matemáticas", subtemasMatematicas, "#FFF9C4");
        });

        btnCiencias.setOnClickListener(v -> {
            v.startAnimation(pulse());
            mostrarSubtemas("Ciencias Naturales", subtemasCiencias, "#E8F5E9");
        });

        btnSociales.setOnClickListener(v -> {
            v.startAnimation(pulse());
            mostrarSubtemas("Sociales y Ciudadanas", subtemasSociales, "#F3E5F5");
        });

        btnIngles.setOnClickListener(v -> {
            v.startAnimation(pulse());
            mostrarSubtemas("Inglés", subtemasIngles, "#E1F5FE");
        });

        // Botón Perfil y Estilo (extra)
        btnPerfil.setOnClickListener(v -> {
            v.startAnimation(pulse());
            new AlertDialog.Builder(this)
                    .setTitle("Perfil y Estilo de Aprendizaje")
                    .setMessage("Aquí verás tu estilo detectado y recomendaciones personalizadas.\n\n" +
                            "• Descripción del estilo.\n" +
                            "• Estrategias recomendadas.\n" +
                            "• Actividades sugeridas.")
                    .setPositiveButton("Cerrar", null)
                    .show();
        });
    }

    // --- PINTA SUBTEMAS LINDOS CON TARJETAS ---
    private void mostrarSubtemas(String area, String[] subtemas, String colorFondoHex) {
        tituloArea.setText(area);
        contenedorSubtemas.removeAllViews();

        // Cambia el fondo del panel
        try {
            panelSubtemas.setBackgroundColor(Color.parseColor(colorFondoHex));
        } catch (Exception e) {
            panelSubtemas.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        for (String s : subtemas) {
            // Tarjeta de subtema (CardView programático)
            CardView card = new CardView(this);
            LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );
            cp.setMargins(0, 12, 0, 12);
            card.setLayoutParams(cp);
            card.setRadius(18f);
            card.setCardElevation(6f);
            card.setUseCompatPadding(true);
            card.setCardBackgroundColor(Color.WHITE);

            // Contenido
            TextView tv = new TextView(this);
            tv.setText("• " + s);
            tv.setTextSize(16f);
            tv.setTextColor(Color.parseColor("#212121"));
            tv.setPadding(28, 24, 28, 24);
            card.addView(tv);

            // Click del subtema (muestra modal bonito)
            card.setOnClickListener(v -> {
                v.startAnimation(pulse());
                new AlertDialog.Builder(this)
                        .setTitle(s)
                        .setMessage("Aquí podrás ver teoría, ejemplos, tips y ejercicios sobre:\n\n" + s)
                        .setPositiveButton("Cerrar", null)
                        .show();
            });

            contenedorSubtemas.addView(card);
        }

        // Mostrar el panel si estaba oculto
        if (panelSubtemas.getVisibility() != View.VISIBLE) {
            panelSubtemas.setVisibility(View.VISIBLE);
            panelSubtemas.startAnimation(fadeIn(200));
        }
    }
}
