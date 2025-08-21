package com.example.icfes_up.Mundos;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.icfes_up.R;

public class BienvenidaMundoFragment extends Fragment {

    private static final String ARG_NOMBRE = "arg_nombre";
    private static final String ARG_IMAGEN = "arg_imagen";

    private String nombreMundo;
    private int resImagen;
    private OnBienvenidaListener listener;

    // 1. Interfaz con parámetro
    public interface OnBienvenidaListener {
        void onEmpezarClicked(String nombreCompetencia);
    }

    public static BienvenidaMundoFragment newInstance(String nombre, int imagenRes) {
        BienvenidaMundoFragment f = new BienvenidaMundoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NOMBRE, nombre);
        args.putInt(ARG_IMAGEN, imagenRes);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnBienvenidaListener) {
            listener = (OnBienvenidaListener) context;
        } else {
            throw new RuntimeException(
                    "La actividad debe implementar OnBienvenidaListener"
            );
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b != null) {
            nombreMundo = b.getString(ARG_NOMBRE);
            resImagen   = b.getInt(ARG_IMAGEN);
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        View v = inflater.inflate(
                R.layout.fragment_bienvenida_mundo, container, false
        );

        ImageView imgFondo = v.findViewById(R.id.imgFondoMundo);
        TextView txtTitulo = v.findViewById(R.id.txtTituloBienvenida);
        Button btnStart   = v.findViewById(R.id.btnEmpezar);

        imgFondo.setImageResource(resImagen);
        txtTitulo.setText("Bienvenido a " + nombreMundo);

        // 2. Enviar nombreMundo al callback
        btnStart.setOnClickListener(view -> {
            if (listener != null) {
                listener.onEmpezarClicked(nombreMundo);
            }
        });

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}

