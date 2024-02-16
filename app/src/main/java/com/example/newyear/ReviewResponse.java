package com.example.newyear;

import java.io.Serializable;

public class ReviewResponse implements Serializable {
    private ReviewList reviewList;

    public ReviewResponse(ReviewList reviewList) {
        this.reviewList = reviewList;
    }

    public ReviewList getReviewList() {
        return reviewList;
    }

    @Override
    public String toString() {
        return "ReviewResponse{" +
                "reviewList=" + reviewList +
                '}';
    }
}
