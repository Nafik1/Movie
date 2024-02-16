package com.example.newyear;

import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MovieViewHolder> {
    private List<movie> movies = new ArrayList<>();

    private onReachEndListener onReachEndListener;
    private onClickPoster onClickPoster;

    public void setOnClickPoster(Adapter.onClickPoster onClickPoster) {
        this.onClickPoster = onClickPoster;
    }

    public void setOnReachEndListener(Adapter.onReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setMovies(List<movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.movie_item, parent, false
        );
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        movie movie = movies.get(position);
        Glide.with(holder.itemView)
                .load(movie.getPoster().getUrl())
                .into(holder.posterView);

        double rating = movie.getRating().getKp();
        int backgroundId;
        if (rating > 8) {
            backgroundId = R.drawable.circle_green;
        } else if(rating > 5) {
            backgroundId = R.drawable.circle_orange;
        } else {
            backgroundId = R.drawable.clrcle_red;
        }
        Drawable background = ContextCompat.getDrawable(holder.itemView.getContext(),backgroundId);
        holder.ratingView.setBackground(background);
        holder.ratingView.setText(String.valueOf(movie.getRating().getKp()));
        if (position >= movies.size()-10 && onReachEndListener != null) {
            onReachEndListener.onReachEnd();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickPoster != null) {
                    onClickPoster.OnClickPost(movie);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    interface onReachEndListener {
        void onReachEnd();
    }
    interface onClickPoster {
        void OnClickPost(movie movie);
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final ImageView posterView;
        private final TextView ratingView;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            posterView = itemView.findViewById(R.id.ImageViewPoster);
            ratingView = itemView.findViewById(R.id.TextViewRating);
        }
    }
}
