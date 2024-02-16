package com.example.newyear;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM favourite_movies")
    LiveData<List<movie>> getAllFavouriteMovies();
    @Query("SELECT * FROM favourite_movies WHERE id = :id")
    LiveData<movie> getFavouriteMovie(int id);
    @Insert
    Completable insertMovie(movie movie);
    @Query("DELETE FROM favourite_movies WHERE id = :id")
    Completable removeMovie(int id);
}
