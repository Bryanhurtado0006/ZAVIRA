package com.example.icfes_up.Mundos.competencia.detail;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.icfes_up.R;

public class SubtemaDetailFragment extends Fragment {

    private static final String ARG_SUBTEMA = "arg_subtema";

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(
                R.layout.fragment_subtema_detail, container, false);

        TextView txtSubtema = view.findViewById(R.id.txtSubtemaDetalle);

        // Obtener el argumento enviado desde CompetenciaDetailFragment
        String subtema = requireArguments().getString(ARG_SUBTEMA);
        txtSubtema.setText(subtema);

        return view;
    }
}
