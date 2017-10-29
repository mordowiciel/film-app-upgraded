package com.example.mordowiciel.filmappupgraded.ui;


import com.example.mordowiciel.filmappupgraded.model.Movie;

public interface MainFragmentView {

    void showMovieData(Movie movie);

    void showLoader();

    void hideLoader();
}
