package com.example.newyear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainDetailActivity extends AppCompatActivity {
    private ImageView poster;
    private TextView name;
    private TextView year;
    private TextView description;
    private MainDetailViewModel mainDetailViewModel;
    private AdapterTrailer adapterTrailer;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewReview;
    private AdapterReview adapterReview;
    private ImageView starchik;
    private static final String EXTRA_MOVIE = "movie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_detail);
        exec();
        movie movie = (movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        Glide.with(this)
                .load(movie.getPoster().getUrl())
                .into(poster);
        name.setText(movie.getName());
        year.setText(String.valueOf(movie.getYear()));
        description.setText(movie.getDescription());
        mainDetailViewModel = new ViewModelProvider(this).get(MainDetailViewModel.class);
        mainDetailViewModel.loadtrailers(movie.getId());
        mainDetailViewModel.getTrailers().observe(this, new Observer<List<Trailer>>() {
            @Override
            public void onChanged(List<Trailer> trailers) {
                adapterTrailer.setTrailers(trailers);
            }
        });
        adapterTrailer = new AdapterTrailer();
        recyclerView = findViewById(R.id.recyclerTrailer);
        recyclerView.setAdapter(adapterTrailer);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapterTrailer.setClickOnTrailer(new AdapterTrailer.clickOnTrailer() {
            @Override
            public void ClickOnTrail(Trailer trailer) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(trailer.getUrlTrailer()));
                startActivity(intent);
            }
        });

        adapterReview = new AdapterReview();
        recyclerViewReview.setAdapter(adapterReview);
        recyclerViewReview.setLayoutManager(new GridLayoutManager(this, 1));
        mainDetailViewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                adapterReview.setReviews(reviews);
                Log.d("jsahj28","kaif");
            }
        });
        mainDetailViewModel.loadreview(movie.getId());

        Drawable starOff = ContextCompat.getDrawable(MainDetailActivity.this, android.R.drawable.star_big_off);
        Drawable starOn = ContextCompat.getDrawable(MainDetailActivity.this, android.R.drawable.star_big_on);

        mainDetailViewModel.getFavouriteMovie(movie.getId()).observe(this, new Observer<movie>() {
            @Override
            public void onChanged(movie movieFromDb) {
                if (movieFromDb == null) {
                    starchik.setImageDrawable(starOff);
                    starchik.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mainDetailViewModel.insertMovie(movie);
                        }
                    });
                } else {
                    starchik.setImageDrawable(starOn);
                    starchik.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mainDetailViewModel.removeMovie(movie.getId());
                        }
                    });
                }

            }
        });

    }
    private void exec() {
        poster = findViewById(R.id.ImageViewPoster);
        name = findViewById(R.id.FilmName);
        year = findViewById(R.id.FilmYear);
        description = findViewById(R.id.FilmDescription);
        recyclerViewReview = findViewById(R.id.recyclerReview);
        starchik = findViewById(R.id.ImageViewStar);
    }
    public static Intent newintent(Context context, movie movie) {
        Intent intent = new Intent(context, MainDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE,movie);
        return intent;
    }
}