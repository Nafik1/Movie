package com.example.newyear;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
    private static final String BASE_URL = "https://api.kinopoisk.dev/";

    private static APIservice apIservice;

    public static APIservice getApIservice() {
        if(apIservice == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
            apIservice = retrofit.create(APIservice.class);
        }
        return apIservice;
    }
}
