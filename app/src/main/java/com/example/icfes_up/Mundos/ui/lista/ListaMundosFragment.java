package com.example.icfes_up.Mundos;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icfes_up.R;
import com.example.icfes_up.Mundos.Mundo;
import com.example.icfes_up.Mundos.MundosAdapter;
import com.example.icfes_up.Mundos.SpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ListaMundosFragment extends Fragment {

    private RecyclerView recyclerView;
    private MundosAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mundos_list, container, false);

        recyclerView = view.findViewById(R.id.recyclerMundos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.addItemDecoration(new SpacingItemDecoration(dpToPx(16)));

        List<Mundo> listaMundos = new ArrayList<>();
        listaMundos.add(new Mundo("Campo de batalla Matematico", R.drawable.mundouno));
        listaMundos.add(new Mundo("English Arena", R.drawable.mundodos));
        listaMundos.add(new Mundo("Biblioteca Encantada", R.drawable.mundotres));
        listaMundos.add(new Mundo("Laboratorio Galactico", R.drawable.mundocuatro));
        listaMundos.add(new Mundo("Republica de las Decisiones", R.drawable.mundocinco));

        adapter = new MundosAdapter(listaMundos, mundoSeleccionado -> {
            Intent intent = new Intent(requireContext(), MundoDetalleActivity.class);
            // Extra: nombre del mundo
            intent.putExtra("nombre_mundo", mundoSeleccionado.getNombre());
            // Nuevo extra: recurso de imagen
            intent.putExtra("imagen_mundo_res", mundoSeleccionado.getImagen());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);


        return view;
    }
    private int dpToPx(int dp) {
        float density = getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }
}

