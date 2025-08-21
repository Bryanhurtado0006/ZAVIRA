package com.example.icfes_up.lenny_testi;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.icfes_up.R;

public class SplashAnimation extends AppCompatActivity {

    private LottieAnimationView lottieView;
    private TextView tvMessage1, tvMessage2, tvMessage3, tvMessage4;
    private Button btnComenzarTest;

    private final int DURACION_FADE = 800;   // Duración fade-in/fade-out en ms
    private final int DURACION_MENSAJE = 800; // Tiempo visible de cada mensaje

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_animation);

        // Animación Lottie
        lottieView = findViewById(R.id.lottieView);

        // TextViews de mensajes
        tvMessage1 = findViewById(R.id.tvMessage1);
        tvMessage2 = findViewById(R.id.tvMessage2);
        tvMessage3 = findViewById(R.id.tvMessage3);
        tvMessage4 = findViewById(R.id.tvMessage4);

        // Botón (oculto inicialmente)
        btnComenzarTest = findViewById(R.id.btnComenzarTest);
        btnComenzarTest.setVisibility(View.GONE);

        // Inicialmente invisibles
        tvMessage1.setAlpha(0f);
        tvMessage2.setAlpha(0f);
        tvMessage3.setAlpha(0f);
        tvMessage4.setAlpha(0f);

        // Secuencia de mensajes
        showMessageSequence();
    }

    // Secuencia de fade-in/fade-out de mensajes
    private void showMessageSequence() {
        fadeMessage(tvMessage1, 0, () ->
                fadeMessage(tvMessage2, 0, () ->
                        fadeMessage(tvMessage3, 0, () ->
                                fadeMessage(tvMessage4, 0, this::showAlert))));
    }

    private void fadeMessage(TextView message, int delay, Runnable next) {
        new Handler().postDelayed(() -> {
            // Fade-in
            message.animate().alpha(1f).setDuration(DURACION_FADE).withEndAction(() -> {
                // Mantener visible un tiempo y luego fade-out
                new Handler().postDelayed(() ->
                                message.animate().alpha(0f).setDuration(DURACION_FADE).withEndAction(next).start(),
                        DURACION_MENSAJE);
            }).start();
        }, delay);
    }

    // ALERTA EXPLICATIVA DEL TEST (tipo card animada)
    private void showAlert() {
        showAnimatedDialog(
                "Test de Estilo de Aprendizaje",
                "Este test evaluará tu estilo de aprendizaje en 4 técnicas diferentes.\n\n" +
                        "Con 4 opciones de respuesta:\n\n" +
                        "4. El estilo que más se acerque al suyo.\n" +
                        "3. El estilo que lo sigue en orden decreciente.\n" +
                        "2. El estilo que lo sigue en orden decreciente.\n" +
                        "1. El estilo que menos se acerca al suyo.\n\n" +
                        "Cada técnica contiene 9 preguntas, para un total de 36.\n\n" +
                        "Responde con sinceridad para conocer tu estilo dominante.",
                () -> {
                    Intent intent = new Intent(SplashAnimation.this, MainActivitytest.class);
                    startActivity(intent);
                    finish();
                }
        );
    }

    // ALERTA DEL MINI TEST 25 PREGUNTAS (tipo card animada)
    private void showAlertMiniTest() {
        showAnimatedDialog(
                "Preparate para el MiniTest",
                "Ahora realizarás un mini examen de 25 preguntas para evaluar tu estilo de aprendizaje.\n\n" +
                        " Cada pregunta tiene 4 opciones.\n" +
                        " Selecciona la opción que mejor refleje tu respuesta.",
                () -> {
                    Intent intent = new Intent(SplashAnimation.this, MiniTest.class);
                    startActivity(intent);
                }
        );
    }

    // MÉTODO GENERAL PARA ALERTA MODERNA ANIMADA
    private void showAnimatedDialog(String titulo, String mensaje, Runnable accionBtn) {
        Dialog dialog = new Dialog(this, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen);
        dialog.setCancelable(false);

        // Layout principal tipo "card"
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(40, 40, 40, 40);
        mainLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Fondo blanco con borde azul y sombra
        GradientDrawable background = new GradientDrawable();
        background.setColor(Color.WHITE);
        background.setStroke(4, Color.parseColor("#ADD8E6"));
        background.setCornerRadius(30f);
        mainLayout.setBackground(background);
        mainLayout.setElevation(20f);

        // Animación de entrada (fade + scale)
        mainLayout.setScaleX(0f);
        mainLayout.setScaleY(0f);
        mainLayout.setAlpha(0f);
        mainLayout.animate()
                .scaleX(1f)
                .scaleY(1f)
                .alpha(1f)
                .setDuration(400)
                .setInterpolator(new android.view.animation.AccelerateDecelerateInterpolator())
                .start();

        // Título centrado
        TextView tvTitulo = new TextView(this);
        tvTitulo.setText(titulo);
        tvTitulo.setTextSize(20f);
        tvTitulo.setTextColor(Color.BLACK);
        tvTitulo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvTitulo.setPadding(0, 0, 0, 24);
        mainLayout.addView(tvTitulo);

        // ScrollView para mensaje largo
        ScrollView scroll = new ScrollView(this);
        scroll.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                0,
                1f
        ));

        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);

        TextView tvMensaje = new TextView(this);
        tvMensaje.setText(mensaje);
        tvMensaje.setTextSize(16f);
        tvMensaje.setTextColor(Color.BLACK);
        tvMensaje.setLineSpacing(1.2f, 1.2f);
        contentLayout.addView(tvMensaje);

        scroll.addView(contentLayout);
        mainLayout.addView(scroll);

        // Botón centrado
        Button btnAceptar = new Button(this);
        btnAceptar.setText("Comenzar");
        btnAceptar.setBackgroundColor(Color.parseColor("#ADD8E6"));
        btnAceptar.setTextColor(Color.BLACK);

        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        btnParams.topMargin = 24;
        btnParams.gravity = android.view.Gravity.CENTER_HORIZONTAL;
        btnAceptar.setLayoutParams(btnParams);

        btnAceptar.setOnClickListener(v -> {
            // Animación de salida
            mainLayout.animate()
                    .scaleX(0f)
                    .scaleY(0f)
                    .alpha(0f)
                    .setDuration(300)
                    .withEndAction(() -> {
                        dialog.dismiss();
                        if (accionBtn != null) accionBtn.run();
                    })
                    .start();
        });

        mainLayout.addView(btnAceptar);

        dialog.setContentView(mainLayout);
        dialog.getWindow().setLayout(
                (int) (getResources().getDisplayMetrics().widthPixels * 0.9),
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        // Cambiado a blanco para que no se vea fondo oscuro
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);

        dialog.show();
    }

}
