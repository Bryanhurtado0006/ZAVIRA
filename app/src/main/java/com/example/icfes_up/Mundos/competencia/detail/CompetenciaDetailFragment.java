package com.example.icfes_up.Mundos.competencia.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icfes_up.Mundos.shared.adapters.SubtemaAdapter;
import com.example.icfes_up.R;

import java.util.Arrays;
import java.util.List;

public class CompetenciaDetailFragment extends Fragment
        implements SubtemaAdapter.OnSubtemaClickListener {

    private static final String ARG_COMPETENCIA = "arg_competencia";

    public static CompetenciaDetailFragment newInstance(String nombre) {
        CompetenciaDetailFragment fragment = new CompetenciaDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_COMPETENCIA, nombre);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_competencia_detail, container, false);

        TextView txtTitulo = view.findViewById(R.id.txtTituloCompetencia);
        TextView txtDesc   = view.findViewById(R.id.txtDescripcionCompetencia);
        RecyclerView rv    = view.findViewById(R.id.recyclerSubtemas);

        String nombre = requireArguments().getString(ARG_COMPETENCIA);
        txtTitulo.setText("Bienvenido a \"" + nombre + "\"");
        txtDesc.setText("Aquí encontrarás los subtemas de la competencia.");

        List<String> subtemas = getSubtemasPara(nombre);

        rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        // Pasa 'this' que ahora recibe (subtema, itemView)
        rv.setAdapter(new SubtemaAdapter(subtemas, this));

        return view;
    }

    /**
     * Ahora recibe también la View clickeada, que usamos
     * para buscar el NavController correcto.
     */
    @Override
    public void onSubtemaClick(String subtema, View itemView) {
        Bundle args = new Bundle();
        args.putString("arg_subtema", subtema);

        NavController navController = Navigation.findNavController(itemView);
        navController.navigate(
                R.id.action_competenciaDetailFragment_to_subtemaDetailFragment,
                args
        );
    }

    private List<String> getSubtemasPara(String competencia) {
        switch (competencia) {
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
