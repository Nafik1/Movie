package com.example.newyear;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterTrailer extends RecyclerView.Adapter<AdapterTrailer.MovieViewHolder>{
    private List<Trailer> trailers= new ArrayList<>();
    private clickOnTrailer clickOnTrailer;

    public void setClickOnTrailer(AdapterTrailer.clickOnTrailer clickOnTrailer) {
        this.clickOnTrailer = clickOnTrailer;
    }

    public void setTrailers(List<Trailer> trailers) {
        this.trailers = trailers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.trailer_item,
                parent,
                false
        );
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Trailer trailer = trailers.get(position);
        holder.nameTrailer.setText(trailer.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(clickOnTrailer != null) {
                    clickOnTrailer.ClickOnTrail(trailer);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }
    interface clickOnTrailer{
        void ClickOnTrail(Trailer trailer);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTrailer;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTrailer = itemView.findViewById(R.id.nametrailer);
        }
    }
}
