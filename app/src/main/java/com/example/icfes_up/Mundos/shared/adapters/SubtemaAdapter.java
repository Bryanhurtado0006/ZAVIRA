package com.example.icfes_up.Mundos;

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

    private List<String> subtemas;

    public SubtemaAdapter(List<String> subtemas) {
        this.subtemas = subtemas;
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
        holder.txtSubtema.setText(subtemas.get(position));
    }

    @Override
    public int getItemCount() {
        return subtemas.size();
    }

    static class SubtemaViewHolder extends RecyclerView.ViewHolder {
        TextView txtSubtema;
        public SubtemaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubtema = itemView.findViewById(R.id.txtSubtema);
        }
    }
}
