package com.example.icfes_up.configuracion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.icfes_up.R;
import com.example.icfes_up.configuracion.Lenny_TesInicial;

public class Lenny_TesInicial extends AppCompatActivity {

    private int currentQuestionIndex = 0;
    private int score = 0;

    private final String[] questions = {
            "¿Cuál es la capital de Colombia?",
            "¿Cuánto es 5 + 3?",
            "Choose the correct translation: 'Perro'",
            "¿Qué planeta es conocido como el planeta rojo?",
            "¿Quién escribió 'Cien años de soledad'?"
    };

    private final String[][] options = {
            {"Bogotá", "Medellín", "Cali"},
            {"6", "8", "10"},
            {"Dog", "Cat", "Bird"},
            {"Marte", "Júpiter", "Venus"},
            {"Gabriel García Márquez", "Pablo Neruda", "Mario Vargas Llosa"}
    };

    private final int[] correctAnswers = {0, 1, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lenny_tes_inicial);

        showWelcomeMessage();
    }

    private void showWelcomeMessage() {
        new AlertDialog.Builder(this)
                .setTitle("¡Bienvenido/a!")
                .setMessage("Vas a realizar un test inicial rápido para conocerte mejor.")
                .setCancelable(false)
                .setPositiveButton("Iniciar", (dialog, which) -> showTestDialog())
                .show();
    }

    private void showTestDialog() {
        if (currentQuestionIndex < questions.length) {
            new AlertDialog.Builder(this)
                    .setTitle("Pregunta " + (currentQuestionIndex + 1))
                    .setSingleChoiceItems(options[currentQuestionIndex], -1, null)
                    .setCancelable(false)
                    .setPositiveButton("Responder", (dialog, which) -> {
                        AlertDialog alertDialog = (AlertDialog) dialog;
                        int selectedPosition = alertDialog.getListView().getCheckedItemPosition();
                        if (selectedPosition == correctAnswers[currentQuestionIndex]) {
                            score++;
                        }
                        currentQuestionIndex++;
                        showTestDialog();
                    })
                    .show();
        } else {
            showResultDialog();
        }
    }

    private void showResultDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Resultado")
                .setMessage("Tu puntaje fue: " + score + " de " + questions.length)
                .setCancelable(false)
                .setPositiveButton("Continuar", (dialog, which) -> {
                    // Ir a pantalla de simulacros
                    Intent intent = new Intent(this, Lenny_TesInicial.class);
                    startActivity(intent);

                    finish();
                })
                .show();
    }
}
