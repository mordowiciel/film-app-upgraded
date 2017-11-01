package com.example.mordowiciel.filmappupgraded.ui.home.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mordowiciel.filmappupgraded.App;
import com.example.mordowiciel.filmappupgraded.R;
import com.example.mordowiciel.filmappupgraded.model.Movie;
import com.example.mordowiciel.filmappupgraded.ui.components.MovieThumbnailView;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.di.DaggerMovieFragmentComponent;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.di.MovieFragmentComponent;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.di.MovieFragmentModule;
import com.example.mordowiciel.filmappupgraded.util.TheMovieDbUrlUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment implements MovieFragmentView {

    @BindView(R.id.movie_thumbnail)
    MovieThumbnailView mMovieThumbnailView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Inject
    MovieFragmentPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        MovieFragmentComponent component = DaggerMovieFragmentComponent
                .builder()
                .appComponent(App.get(getActivity()).getAppComponent())
                .movieFragmentModule(new MovieFragmentModule(this))
                .build();

        component.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.fetchMovieData();
    }

    @Override
    public void showMovieData(Movie movie) {
        mMovieThumbnailView.setTitle(movie.getTitle());
        mMovieThumbnailView.setImageFromUrl(TheMovieDbUrlUtil.provideFullPosterUrl(movie.getPosterSubpath()));
    }

    @Override
    public void showLoader() {
        mMovieThumbnailView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mMovieThumbnailView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }
}
