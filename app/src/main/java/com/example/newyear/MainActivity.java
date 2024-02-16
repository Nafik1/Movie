package com.example.newyear;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private RecyclerView recyclerview;
    private Adapter adapter;
    private ProgressBar progressBar;
    private MainDetailActivity mainDetailActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerview = findViewById(R.id.RecyclerViewMovies);
        progressBar = findViewById(R.id.ProgressBarLoading);
        adapter = new Adapter();
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new GridLayoutManager(this,2));
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getMovies().observe(this, new Observer<List<movie>>() {
            @Override
            public void onChanged(List<movie> movies) {
                adapter.setMovies(movies);
            }
        });
        adapter.setOnReachEndListener(new Adapter.onReachEndListener() {
            @Override
            public void onReachEnd() {
                viewModel.loadmovie();
            }
        });
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {

                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
        adapter.setOnClickPoster(new Adapter.onClickPoster() {
            @Override
            public void OnClickPost(movie movie) {
                Intent intent = MainDetailActivity.newintent(MainActivity.this,movie);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemFavourite) {
            Intent intent = FavouriteActivity.newintent(this);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}