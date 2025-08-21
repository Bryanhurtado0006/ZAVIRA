package com.example.icfes_up.Mundos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icfes_up.R;
import com.example.icfes_up.Mundos.Mundo;

import java.util.List;

public class MundosAdapter extends RecyclerView.Adapter<MundosAdapter.MundoViewHolder> {

    private List<Mundo> mundos;
    private OnMundoClickListener listener;

    public interface OnMundoClickListener {
        void onMundoClick(Mundo mundo);
    }

    public MundosAdapter(List<Mundo> mundos, OnMundoClickListener listener) {
        this.mundos = mundos;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MundoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_mundo, parent, false);
        return new MundoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MundoViewHolder holder, int position) {
        Mundo mundo = mundos.get(position);
        holder.txtNombreMundo1.setText(mundo.getNombre());
        holder.imgMundo1.setImageResource(mundo.getImagen());

        // Acción al hacer clic
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onMundoClick(mundo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mundos.size();
    }

    static class MundoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombreMundo1;
        ImageView imgMundo1;

        public MundoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombreMundo1 = itemView.findViewById(R.id.txtMundo1);
            imgMundo1 = itemView.findViewById(R.id.imgMundo1);
        }
    }
}


