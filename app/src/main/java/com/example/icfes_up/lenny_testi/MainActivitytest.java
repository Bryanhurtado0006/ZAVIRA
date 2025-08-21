package com.example.icfes_up.lenny_testi;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.icfes_up.R;

public class MainActivitytest extends AppCompatActivity {

    private String[][] preguntas = {
            {
                    "DICERNIMIENTO: Procedo por eliminación, trato de distinguir cuidadosamente los elementos más pertinentes.",
                    "RECEPTIVAMENTE: Estoy concentrado plenamente en lo que sucede y disponible para recoger la mayor cantidad de elementos posible.",
                    "SINTIENDO: Pongo atención a lo que siento espontáneamente y a lo que soy dentro de la situación.",
                    "ACEPTANDO: Acepto la situación tal cual se presenta, admito incondicionalmente la realidad, parto de los hechos tal como son.",
                    "INTUITIVAMENTE: Me fío a lo que surge espontáneamente de mi intuición, antes que recurrir al razonamiento lógico (inducción, deducción, comparación, etc.).",
                    "ABSTRACTO: Tengo tendencia a referirme más a los principios y conocimientos adquiridos antes que detenerme a analizar los hechos o las evidencias de la realidad.",
                    "ORIENTADO HACIA EL PRESENTE: Tengo en cuenta antes que nada lo que sucede en el momento presente.",
                    "APRENDIENDO MÁS DE LA EXPERIENCIA: Me refiero principalmente al conjunto de mis experiencias vividas, o a lo que otros han encontrado.",
                    "AFECTIVAMENTE: Pongo toda mi atención sobre el tema o problema y reflexiono hasta llegar a una conclusión satisfactoria."
            },
            {
                    "ENSAYANDO: Actúo por olfato, ensayo las cosas que se me ocurren, que se me vienen a la mente hasta que funciona.",
                    "RELACIONANDO: Trato de ubicarme correctamente con relación a la pregunta o al problema que se presenta.",
                    "OBSERVANDO: Observo atentamente lo que sucede y cómo se desarrolla.",
                    "TOMANDO RIESGOS: Me involucro en buen grado en experiencias nuevas, tengo tendencia a salirme de los caminos convencionales.",
                    "PRODUCTIVAMENTE: Me preocupo sobre todo por obtener resultados concretos, trato de alcanzar un producto útil o interesante.",
                    "OBSERVANDO: Examinando atentamente los detalles; prefiero observar atentamente lo que sucede en lugar de tratar de buscar e imaginar diferentes explicaciones.",
                    "REFLEXIVO: Pienso, reflexiono, el problema da vueltas en mi cabeza, lo 'mastico mentalmente'.",
                    "APRENDE MÁS DE LA OBSERVACIÓN: Mantengo mi atención sobre la situación, analizo y observo todo lo que concierne de cerca o de lejos.",
                    "RESERVADO: Con cautela y sin manifestación externa. Tengo tendencia a ser prudente y moderado, a documentarme bien antes de pronunciarme sobre una pregunta o un problema."
            },
            {
                    "INVOLUCRÁNDOME: Me involucro a fondo, trabajo con ardor, pongo mucho interés y energía.",
                    "ANALÍTICAMENTE: Analizo la situación, tomo en consideración, uno a uno, los diferentes elementos que pueda identificar.",
                    "PENSANDO: Pienso y trato de comprender qué es lo que me presenta una dificultad; trato de encontrar una explicación a lo que me intriga.",
                    "EVALUANDO: En primer lugar y ante todo trato de evaluar seriamente el estado actual de las cosas, y apreciar su justo valor antes de efectuar cualquier cambio.",
                    "LÓGICAMENTE: Trato de aplicar hasta el fin un razonamiento lógico (inductivo, deductivo, comparativo, etc.) riguroso.",
                    "CONCRETO: Me intereso sobre todo en los aspectos concretos, materiales del problema, antes que en sus dimensiones conceptuales, teóricas.",
                    "ORIENTADO HACIA EL FUTURO: Me preocupo sobre todo de las perspectivas del futuro; trato de prever y/o prevenir lo que podría eventualmente suceder con relación a ello.",
                    "APRENDE MÁS DE LA CONCEPCIÓN: Me las ingenio para elaborar una explicación teórica que muestre, de manera original, los diversos aspectos de la situación problemática.",
                    "RACIONAL: Utilizo mi razonamiento y mi juicio lógico para resolver un problema o responder una pregunta."
            },
            {
                    "PRACTICANDO: Trato de hacerlo de manera satisfactoria, yendo de lo más corto, con un mínimo de tiempo y esfuerzo.",
                    "IMPARCIALMENTE: Me esfuerzo por ser lo más objetivo posible, estudio la situación sin tomar partido ni prejuicios.",
                    "HACIENDO: Paso rápidamente a la acción; hago de todo para resolver inmediatamente y de manera práctica el problema presentado.",
                    "CON CAUTELA: Fijándome si las ideas son ciertas o correctas. Me mantengo alerta, con los ojos abiertos; trato de percibir lúcidamente todo lo que me concierne.",
                    "CUESTIONANDO: Me hago todo tipo de preguntas y trato activamente de buscar y aportar elementos de respuestas satisfactorias.",
                    "ACTIVO: Prefiero antes que nada hacer activamente algo, realizar operaciones prácticas.",
                    "PRAGMÁTICO: Buscando efectos o usos prácticos. Antes de gastar energías, me preocupo primero de qué va a servirme y su aplicación práctica.",
                    "DISEÑANDO FORMAS DE PROBAR LAS IDEAS: Creo o provoco acontecimientos con el objetivo de estudiarlos metódicamente o controlar aspectos indeseables.",
                    "ABIERTO: Apertura a otras opciones."
            }
    };

    private String[] titulos = {
            "Experiencia Concreta (EC)",
            "Observación Reflexiva (OR)",
            "Conceptualización Abstracta (CA)",
            "Experimentación Activa (EA)"
    };

    private int paginaActual = 0;
    private int[] puntajes = new int[4];
    private LinearLayout contenedorPreguntas;
    private Button btnSiguiente;
    private ScrollView scrollView; // <-- Declaramos ScrollView para la validación


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activitytest);


        contenedorPreguntas = findViewById(R.id.contenedorPreguntas);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        mostrarPagina();

        btnSiguiente.setOnClickListener(v -> {
            if (!guardarRespuestas()) return;
            paginaActual++;
            if (paginaActual < 4) {
                mostrarPagina();
            } else {
                mostrarResultado(); // 👉 Aquí aparece la alerta original
            }
        });
    }

    // --- ALERTA EXPLICATIVA ---
    private void mostrarExplicacionTest() {
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(24, 24, 24, 24);
        mainLayout.setBackgroundColor(Color.WHITE);

        TextView tvTitulo = new TextView(this);
        tvTitulo.setText("Test de Estilos de Aprendizaje");
        tvTitulo.setTextSize(20f);
        tvTitulo.setTextColor(Color.BLACK);
        tvTitulo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvTitulo.setPadding(0, 0, 0, 16);
        mainLayout.addView(tvTitulo);

        ScrollView scroll = new ScrollView(this);
        LinearLayout content = new LinearLayout(this);
        content.setOrientation(LinearLayout.VERTICAL);

        TextView mensaje = new TextView(this);
        mensaje.setText(
                "Este test evaluará tu estilo de aprendizaje en 4 técnicas diferentes.\n\n" +
                        "Con 4 opciones de respuesta:\n" +
                        "4 = El estilo que más se acerque al tuyo\n" +
                        "3 = El siguiente en orden\n" +
                        "2 = El que sigue en orden\n" +
                        "1 = El que menos se acerque a tu estilo\n\n" +
                        "Cada técnica contiene 9 preguntas, para un total de 36.\n\n" +
                        "Responde con sinceridad para conocer tu estilo dominante."
        );


        mensaje.setTextSize(16f);
        mensaje.setTextColor(Color.BLACK);
        content.addView(mensaje);
        scroll.addView(content);
        mainLayout.addView(scroll);

        Button btnComenzar = new Button(this);
        btnComenzar.setText("Comenzar");
        btnComenzar.setBackgroundColor(Color.parseColor("#ADD8E6"));
        btnComenzar.setTextColor(Color.BLACK);
        btnComenzar.setOnClickListener(v -> {
            dialog.dismiss();
            mostrarPagina(); // 👉 Al presionar comenzar se cargan preguntas
        });
        mainLayout.addView(btnComenzar);

        dialog.setContentView(mainLayout);
        dialog.getWindow().setLayout(
                (int) (getResources().getDisplayMetrics().widthPixels * 0.9),
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        dialog.show();
    }

    private void mostrarPagina() {
        contenedorPreguntas.removeAllViews();
        TextView tvTitulo = findViewById(R.id.tvTitulo);
        tvTitulo.setText(titulos[paginaActual]);

        for (int i = 0; i < 9; i++) {
            androidx.cardview.widget.CardView card = new androidx.cardview.widget.CardView(this);
            LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            cardParams.setMargins(16, 16, 16, 16);
            card.setLayoutParams(cardParams);
            card.setRadius(12f);
            card.setCardElevation(8f);
            card.setUseCompatPadding(true);

            LinearLayout fila = new LinearLayout(this);
            fila.setOrientation(LinearLayout.VERTICAL);
            fila.setPadding(16, 16, 16, 16);

            card.setCardBackgroundColor(Color.parseColor("#FAFAFA"));

            TextView pregunta = new TextView(this);
            pregunta.setText(preguntas[paginaActual][i]);
            pregunta.setTextSize(15f);
            pregunta.setTextColor(Color.BLACK);
            pregunta.setPadding(0, 0, 0, 8);
            fila.addView(pregunta);

            RadioGroup opciones = new RadioGroup(this);
            opciones.setOrientation(RadioGroup.HORIZONTAL);

            for (int j = 1; j <= 4; j++) {
                RadioButton rb = new RadioButton(this);
                rb.setText(String.valueOf(j));
                rb.setTextColor(Color.BLACK);
                rb.setId(View.generateViewId());
                opciones.addView(rb);
            }

            fila.addView(opciones);
            card.addView(fila);
            contenedorPreguntas.addView(card);
        }
    }

    private boolean guardarRespuestas() {
        int numPreguntasBloque = contenedorPreguntas.getChildCount();
        int puntos = 0;

        for (int i = 0; i < numPreguntasBloque; i++) {
            View v = contenedorPreguntas.getChildAt(i);
            androidx.cardview.widget.CardView card = (androidx.cardview.widget.CardView) v;
            LinearLayout fila = (LinearLayout) card.getChildAt(0);
            RadioGroup rg = null;

            for (int k = 0; k < fila.getChildCount(); k++) {
                if (fila.getChildAt(k) instanceof RadioGroup) {
                    rg = (RadioGroup) fila.getChildAt(k);
                    break;
                }
            }

            if (rg == null) continue;
            int checkedId = rg.getCheckedRadioButtonId();
            if (checkedId == -1) {
                Toast.makeText(this, "Responde todas las preguntas del bloque", Toast.LENGTH_SHORT).show();
                return false;
            }
            RadioButton rb = rg.findViewById(checkedId);
            puntos += Integer.parseInt(rb.getText().toString());
        }
        puntajes[paginaActual] = puntos;
        return true;
    }

    private void mostrarResultado() {
        int EC = puntajes[0];
        int OR = puntajes[1];
        int CA = puntajes[2];
        int EA = puntajes[3];

        int X = EA - OR;
        int Y = CA - EC;

        String estilo;
        if (X > 0 && Y > 0) estilo = "Convergente";
        else if (X < 0 && Y > 0) estilo = "Asimilador";
        else if (X < 0 && Y < 0) estilo = "Divergente";
        else estilo = "Acomodador";

        String descripcion;
        String estrategias;

        switch (estilo) {
            case "Convergente":
                descripcion = "Orientado a la aplicación práctica y solución de problemas; experimenta con teorías y busca soluciones técnicas.";
                estrategias = "Proyectos prácticos, ejercicios de resolución de problemas, demostraciones y uso de herramientas.";
                break;
            case "Asimilador":
                descripcion = "Prefiere ideas y modelos teóricos; analiza y organiza datos para construir explicaciones coherentes.";
                estrategias = "Lecturas estructuradas, conferencias, mapas conceptuales, tiempo para reflexión y análisis.";
                break;
            case "Divergente":
                descripcion = "Creativo, observa desde varias perspectivas; genera ideas y alternativas; enfocado en la gente y las sensaciones.";
                estrategias = "Lluvias de ideas, actividades creativas, simulaciones, debates y proyectos abiertos.";
                break;
            case "Acomodador":
                descripcion = "Aprende haciendo; se adapta rápido y disfruta la experiencia directa; tiende al ensayo/error y a la acción.";
                estrategias = "Trabajo por proyectos, role-playing, aprendizaje experimental, trabajo en equipo y actividades prácticas.";
                break;
            default:
                descripcion = "";
                estrategias = "";
                break;
        }

        // --- ALERTA ORIGINAL ---
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(8, 8, 8, 8);

        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.WHITE);
        border.setStroke(6, Color.parseColor("#ADD8E6"));
        border.setCornerRadius(30f);
        mainLayout.setBackground(border);

        TextView tvTitulo = new TextView(this);
        tvTitulo.setText("Resultado del Test");
        tvTitulo.setTextSize(20f);
        tvTitulo.setTextColor(Color.BLACK);
        tvTitulo.setPadding(24, 24, 24, 24);
        tvTitulo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        mainLayout.addView(tvTitulo);

        ScrollView scroll = new ScrollView(this);
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);
        contentLayout.setPadding(24, 0, 24, 24);

        TextView mensaje = new TextView(this);
        mensaje.setText(
                "Resultados:\n" +
                        "EC (Experiencia Concreta): " + EC + "\n" +
                        "OR (Observación Reflexiva): " + OR + "\n" +
                        "CA (Conceptualización Abstracta): " + CA + "\n" +
                        "EA (Experimentación Activa): " + EA + "\n\n" +
                        "X = EA - OR = " + X + "\n" +
                        "Y = CA - EC = " + Y + "\n\n" +
                        "Estilo detectado: " + estilo + "\n\n" +
                        "Descripción: " + descripcion + "\n\n" +
                        "Estrategias recomendadas: " + estrategias
        );
        mensaje.setTextSize(16f);
        mensaje.setTextColor(Color.BLACK);

        contentLayout.addView(mensaje);
        scroll.addView(contentLayout);
        mainLayout.addView(scroll);

        Button btnAceptar = new Button(this);
        btnAceptar.setText("Aceptar");
        btnAceptar.setBackgroundColor(Color.parseColor("#ADD8E6"));
        btnAceptar.setTextColor(Color.BLACK);
        btnAceptar.setPadding(16, 16, 16, 16);
        btnAceptar.setOnClickListener(v -> {
            dialog.dismiss();

            mostrarAlertaMiniTest(); // 👉 Cierra y vuelve atrás (antes te llevaba al inicio de sesión)
        });
        mainLayout.addView(btnAceptar);

        dialog.setContentView(mainLayout);
        dialog.getWindow().setLayout(
                (int) (getResources().getDisplayMetrics().widthPixels * 0.9),
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        dialog.show();
    }

    private void finalizarTestKolb() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Preparate para un MiniTest");
        builder.setMessage(
                "Ahora realizarás un mini examen de 25 preguntas para evaluar tu estilo de aprendizaje.\n\n" +
                        " Cada pregunta tiene 4 opciones.\n" +
                        " Selecciona la opción que mejor refleje tu respuesta."

        );
        builder.setPositiveButton("Iniciar Mini Test", (dialog, which) -> {
            dialog.dismiss();
            // Abrir el MiniTest
            Intent intent = new Intent(MainActivitytest.this, MiniTest.class); // o MiniTestActivity
            startActivity(intent);
            finish(); // cerrar TestKolbActivity
        });
        AlertDialog dialog = builder.create();
        dialog.show();

        // Fondo blanco
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);

        // Botón azul
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setTextColor(Color.parseColor("#ADD8E6"));
    }

    private void mostrarAlertaMiniTest() {
        Dialog dialog = new Dialog(this);
        dialog.setCancelable(false);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(24, 24, 24, 24);

        GradientDrawable border = new GradientDrawable();
        border.setColor(Color.WHITE);
        border.setStroke(6, Color.parseColor("#ADD8E6"));
        border.setCornerRadius(30f);
        mainLayout.setBackground(border);

        TextView tvTitulo = new TextView(this);
        tvTitulo.setText("Preparación Mini Test");
        tvTitulo.setTextSize(20f);
        tvTitulo.setTextColor(Color.BLACK);
        tvTitulo.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvTitulo.setPadding(0, 0, 0, 16);
        mainLayout.addView(tvTitulo);

        ScrollView scroll = new ScrollView(this);
        LinearLayout contentLayout = new LinearLayout(this);
        contentLayout.setOrientation(LinearLayout.VERTICAL);

        TextView mensaje = new TextView(this);
        mensaje.setText(
                "Ahora realizarás un mini examen de 25 preguntas para evaluar tu conocimiento.\n\n" +
                        " Cada pregunta tiene 4 opciones.\n" +
                        " Selecciona la opción que mejor refleje tu respuesta."
        );
        mensaje.setTextSize(16f);
        mensaje.setTextColor(Color.BLACK);
        contentLayout.addView(mensaje);
        scroll.addView(contentLayout);
        mainLayout.addView(scroll);

        Button btnComenzar = new Button(this);
        btnComenzar.setText("Comenzar Mini Test");
        btnComenzar.setBackgroundColor(Color.parseColor("#ADD8E6"));
        btnComenzar.setTextColor(Color.BLACK);
        btnComenzar.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(MainActivitytest.this, MiniTest.class);
            startActivity(intent);
            finish(); // Cierra MainActivitytest
        });
        LinearLayout.LayoutParams btnParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        btnParams.topMargin = 24;
        btnParams.gravity = android.view.Gravity.CENTER_HORIZONTAL;
        btnComenzar.setLayoutParams(btnParams);
        mainLayout.addView(btnComenzar);

        dialog.setContentView(mainLayout);
        dialog.getWindow().setLayout(
                (int) (getResources().getDisplayMetrics().widthPixels * 0.9),
                ViewGroup.LayoutParams.WRAP_CONTENT
        );


        dialog.show();
    }
}

