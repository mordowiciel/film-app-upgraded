package com.example.mordowiciel.filmappupgraded.ui.home.fragment;


import android.util.Log;

import com.example.mordowiciel.filmappupgraded.BuildConfig;
import com.example.mordowiciel.filmappupgraded.model.Movie;
import com.example.mordowiciel.filmappupgraded.rest.MovieService;

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

//        // 1) Using classic observer methods implementation
//        mMovieService.getMovie(335984, BuildConfig.MOVIE_DB_API_KEY)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Movie>() {
//
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        mView.showLoader();
//                    }
//
//                    @Override
//                    public void onNext(Movie movie) {
//                        onResponse(movie);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.hideLoader();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mView.hideLoader();
//                    }
//                });

        // 2) Using method references
        mMovieService.getMovie(335984, BuildConfig.MOVIE_DB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onResponse, e -> onError(), this::onComplete, d -> onSubscribe());
    }

    private void onResponse(Movie movie) {

        Log.d(this.getClass().getSimpleName(), "Logging movie data : ");
        Log.d(this.getClass().getSimpleName(), "Movie title: " + movie.getTitle());
        mView.showMovieData(movie);
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
