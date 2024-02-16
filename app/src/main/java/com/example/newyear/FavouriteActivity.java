package com.example.newyear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMovies);
        Adapter adapter = new Adapter();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);
        adapter.setOnClickPoster(new Adapter.onClickPoster() {
            @Override
            public void OnClickPost(movie movie) {
                Intent intent = MainDetailActivity.newintent(FavouriteActivity.this,movie);
                startActivity(intent);
            }
        });
        FavouriteMoviesViewModel favouriteMoviesViewModel = new ViewModelProvider(this).get(FavouriteMoviesViewModel.class);
        favouriteMoviesViewModel.getMovies().observe(this, new Observer<List<movie>>() {
            @Override
            public void onChanged(List<movie> movies) {
                adapter.setMovies(movies);
            }
        });

    }
    public static Intent newintent(Context context) {
        Intent intent = new Intent(context, FavouriteActivity.class);
        return intent;
    }
}