package com.example.newyear;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterReview extends RecyclerView.Adapter<AdapterReview.MovieViewHolder>{
    private List<Review> reviews = new ArrayList<>();
    private static final String TYPE_POSITIVE = "Позитивный";
    private static final String TYPE_NEGATIVE = "Негативный";
    private static final String TYPE_NEUTRAL = "Нейтральный";

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.review_item,
                parent,
                false
        );
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.author.setText(review.getAuthor());
        holder.text.setText(review.getReview());
        String type = review.getType();
        int colorResId = android.R.color.holo_red_light;
        switch(type) {
            case TYPE_POSITIVE:
                colorResId = android.R.color.holo_green_light;
                break;
            case TYPE_NEUTRAL:
                colorResId = android.R.color.holo_orange_light;
                break;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), colorResId);
        holder.linearLayout.setBackgroundColor(color);


    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        private final TextView author;
        private final TextView text;
        private final LinearLayout linearLayout;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.nameReview);
            text = itemView.findViewById(R.id.otziv);
            linearLayout = itemView.findViewById(R.id.LinearLayoutConteiner);


        }
    }
}
