package com.example.mordowiciel.filmappupgraded.ui;


import com.example.mordowiciel.filmappupgraded.model.Movie;

public interface MainActivityView {

    void showMovieData(Movie movie);

    void showLoader();

    void hideLoader();
}
