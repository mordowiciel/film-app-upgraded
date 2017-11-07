package com.example.mordowiciel.filmappupgraded.ui.home.fragment;


import android.util.Log;

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

    private int mCurrentPage = 1;
    private DownloadState mDownloadState = DownloadState.NONE;
    private final int VISIBLE_ITEMS_THRESHOLD = 4;

    private final String TAG = MovieFragmentPresenter.class.getSimpleName();

    public MovieFragmentPresenter(MovieFragmentView view, MovieService movieService) {
        mView = view;
        mMovieService = movieService;
    }

    public boolean shouldLoadMore(int totalItemCount, int lastVisibleItemPos) {
        return mDownloadState != DownloadState.DOWNLOADING
                && totalItemCount <= (lastVisibleItemPos + VISIBLE_ITEMS_THRESHOLD);
    }

    public void refreshLoaderState() {
        mCurrentPage = 1;
    }

    public void fetchMovieData() {

        if (mDownloadState == DownloadState.DOWNLOADING) {
            return;
        }
        mDownloadState = DownloadState.DOWNLOADING;

        Log.d(TAG, "Downloading page " + mCurrentPage);
        mMovieService.discoverMovies("popularity.desc", mCurrentPage, BuildConfig.MOVIE_DB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((MovieDiscover::getResults))
                .subscribe(this::onResponse, e -> onError(), this::onComplete, d -> onSubscribe());
    }

    private void onResponse(List<Movie> movieList) {
        mView.showMovieData(movieList);
    }

    private void onComplete() {
        Log.d(TAG, "onComplete()");
        mView.hideLoader();
        mDownloadState = DownloadState.FINISHED;
        mCurrentPage++;

    }

    private void onError() {
        Log.d(TAG, "onError()");
        mView.hideLoader();
        mDownloadState = DownloadState.ERROR;
    }

    private void onSubscribe() {
        Log.d(TAG, "onSubscribe()");
        if (mCurrentPage == 1)
            mView.showLoader();
    }

    private enum DownloadState {
        NONE, DOWNLOADING, FINISHED, ERROR
    }
}
