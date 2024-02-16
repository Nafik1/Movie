package com.example.newyear;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse {
    @SerializedName("docs")
    private List<movie> movies;

    public MovieResponse(List<movie> movies) {
        this.movies = movies;
    }

    public List<movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movies=" + movies +
                '}';
    }
}
