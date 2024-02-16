package com.example.newyear;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TrailerList implements Serializable {
    @SerializedName("trailers")
    private List<Trailer> trailerlist;

    public TrailerList(List<Trailer> trailerlist) {
        this.trailerlist = trailerlist;
    }

    public List<Trailer> getTrailerlist() {
        return trailerlist;
    }

    @Override
    public String toString() {
        return "TrailerList{" +
                "trailerlist=" + trailerlist +
                '}';
    }
}
