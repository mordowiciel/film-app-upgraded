package com.example.mordowiciel.filmappupgraded.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    private int id;
    private String title;

    @SerializedName("original_title")
    private String originalTitle;

    @SerializedName("vote_average")
    private double voteAverage;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("poster_path")
    private String posterSubpath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public String getPosterSubpath() {
        return posterSubpath;
    }

    public void setPosterSubpath(String posterSubpath) {
        this.posterSubpath = posterSubpath;
    }
}
