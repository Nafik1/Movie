package com.example.newyear;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Trailer implements Serializable {
    @SerializedName("url")
    private String urlTrailer;
    @SerializedName("name")
    private String name;

    public Trailer(String urlTrailer, String name) {
        this.urlTrailer = urlTrailer;
        this.name = name;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public String getName() {
        return name;
    }
}
