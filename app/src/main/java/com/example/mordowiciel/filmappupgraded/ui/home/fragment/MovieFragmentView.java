package com.example.mordowiciel.filmappupgraded.ui.home.fragment;


import com.example.mordowiciel.filmappupgraded.model.Movie;

import java.util.List;

public interface MovieFragmentView {

    void showMovieData(List<Movie> movieList);

    void showLoader();

    void hideLoader();
}
