package com.example.icfes_up.Mundos;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.icfes_up.R;
import java.util.Arrays;
import java.util.List;

public class CompetenciaDetailFragment extends Fragment {

    private static final String ARG_COMPETENCIA = "arg_competencia";

    public static CompetenciaDetailFragment newInstance(String nombre) {
        CompetenciaDetailFragment f = new CompetenciaDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COMPETENCIA, nombre);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(
                R.layout.fragment_competencia_bienvenida, container, false);

        TextView txtTitulo   = v.findViewById(R.id.txtTituloCompetencia);
        TextView txtDesc     = v.findViewById(R.id.txtDescripcionCompetencia);
        RecyclerView rv      = v.findViewById(R.id.recyclerSubtemas);

        String nombre = getArguments().getString(ARG_COMPETENCIA);
        txtTitulo.setText("Bienvenido a " + nombre);

        // Mapeo estático de subtemas según competencia
        List<String> subtemas = getSubtemasPara(nombre);

        // Configurar RecyclerView
        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        rv.setAdapter(new SubtemaAdapter(subtemas));

        return v;
    }

    // Aquí añades los subtemas de cada competencia
    private List<String> getSubtemasPara(String comp) {
        switch (comp) {
            case "Interpretación y representación":
                return Arrays.asList(
                        "Comprender y transformar información matemática",
                        "Extraer datos relevantes de situaciones diversas"
                );
            case "Formulación y ejecución":
                return Arrays.asList(
                        "Plantear estrategias matemáticas",
                        "Resolver problemas aplicando procedimientos adecuados"
                );
            case "Argumentación":
                return Arrays.asList(
                        "Validar o refutar conclusiones",
                        "Justificar interpretaciones y representaciones"
                );
            default:
                return Arrays.asList("Subtema 1", "Subtema 2");
        }
    }
}
