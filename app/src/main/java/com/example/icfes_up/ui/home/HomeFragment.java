package com.example.icfes_up.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.navigation.Navigation;

import com.example.icfes_up.Gamificacion_LUIS.Ruleta_LUIS;
import com.example.icfes_up.R;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.icfes_up.Mundos.ui.lista.Mundos_activity;

import com.example.icfes_up.databinding.FragmentHomeBinding;
import com.example.icfes_up.mod_supervivencia.BienvenidaSupervivenciaActivity;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        binding.OrientacionV.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_homeFragment_to_bienvenidaFragment);
        });

        //mundos direccion
        binding.ImgMundosgami.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Mundos_activity.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.slide_in_right_testv, R.anim.slide_out_left_testv);
        });

        //DINAMICAS Y RETOS

        binding.ImgGamificacionsemanal.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), Ruleta_LUIS.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.slide_in_right_testv, R.anim.slide_out_left_testv);
        });

        binding.txtModoSupervivencia.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BienvenidaSupervivenciaActivity.class);
            startActivity(intent);
        });




        return root;


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}