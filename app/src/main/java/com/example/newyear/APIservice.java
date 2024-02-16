package com.example.newyear;

import android.graphics.Movie;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIservice {
    @GET("v1.4/movie?rating.kp=3-9&token=T95K40E-4BQM1DV-HJ9TBSN-WA4JNBY&sortField=votes.kp&sortType=-1&limit=35")
    Single<MovieResponse> loadmovie(@Query("page") int page);
    @GET("v1.4/movie/{ID}?token=T95K40E-4BQM1DV-HJ9TBSN-WA4JNBY")
    Single<TrailerResponse> loadtrailer(@Path("ID") int id);
    @GET("v1.4/review?token=T95K40E-4BQM1DV-HJ9TBSN-WA4JNBY")
    Single<ReviewList> loadReview(@Query("movieId") int id);
}
