package com.example.mordowiciel.filmappupgraded.ui.home.fragment;


import com.example.mordowiciel.filmappupgraded.BuildConfig;
import com.example.mordowiciel.filmappupgraded.model.Movie;
import com.example.mordowiciel.filmappupgraded.model.MovieDiscover;
import com.example.mordowiciel.filmappupgraded.rest.MovieService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieFragmentPresenter {

    private MovieFragmentView mView;
    private MovieService mMovieService;

    public MovieFragmentPresenter(MovieFragmentView view, MovieService movieService) {
        mView = view;
        mMovieService = movieService;
    }

    public void fetchMovieData() {

        mMovieService.discoverMovies("popularity.desc", BuildConfig.MOVIE_DB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((MovieDiscover::getResults))
                .subscribe(this::onResponse, e -> onError(), this::onComplete, d -> onSubscribe());
    }

    private void onResponse(List<Movie> movieList) {
        mView.showMovieData(movieList);
    }

    private void onComplete() {
        mView.hideLoader();
    }

    private void onError() {
        mView.hideLoader();
    }

    private void onSubscribe() {
        mView.showLoader();
    }
}
