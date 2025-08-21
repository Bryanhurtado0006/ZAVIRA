package com.example.icfes_up.Mundos.shared.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icfes_up.R;

import java.util.List;

public class SubtemaAdapter
        extends RecyclerView.Adapter<SubtemaAdapter.SubtemaViewHolder> {

    /**
     * Callback para manejar clicks en cada subtema.
     */
    public interface OnSubtemaClickListener {
        void onSubtemaClick(String subtema, View itemView);
    }


    private final List<String> subtemas;
    private final OnSubtemaClickListener listener;

    /**
     * @param subtemas Lista de nombres de subtema.
     * @param listener Listener que recibe el nombre del subtema clicado.
     */
    public SubtemaAdapter(List<String> subtemas, OnSubtemaClickListener listener) {
        this.subtemas = subtemas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SubtemaViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subtema, parent, false);
        return new SubtemaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull SubtemaViewHolder holder, int position) {
        String texto = subtemas.get(position);
        holder.txtSubtema.setText(texto);

        // Configura el click y dispara el callback
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSubtemaClick(texto,v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subtemas == null ? 0 : subtemas.size();
    }

    static class SubtemaViewHolder extends RecyclerView.ViewHolder {
        final TextView txtSubtema;

        SubtemaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubtema = itemView.findViewById(R.id.txtSubtema);
        }
    }
}
