package com.example.mordowiciel.filmappupgraded.ui;


import com.example.mordowiciel.filmappupgraded.BuildConfig;
import com.example.mordowiciel.filmappupgraded.model.Movie;
import com.example.mordowiciel.filmappupgraded.rest.MovieService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainFragmentPresenter {

    private MainFragmentView mView;

    private Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(MovieService.MAIN_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private MovieService mMovieService;

    public MainFragmentPresenter(MainFragmentView view) {
        mView = view;
        mMovieService = mRetrofit.create(MovieService.class);
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
