package com.example.mordowiciel.filmappupgraded.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mordowiciel.filmappupgraded.R;
import com.example.mordowiciel.filmappupgraded.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements MainFragmentView {

    @BindView(R.id.title)
    TextView titleText;

    @BindView(R.id.vote_average)
    TextView voteAverage;

    @BindView(R.id.vote_count)
    TextView voteCount;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private MainFragmentPresenter mPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = new MainFragmentPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);
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
        titleText.setText(movie.getTitle());
        voteAverage.setText(String.valueOf(movie.getVoteAverage()));
        voteCount.setText(String.valueOf(movie.getVoteCount()));
    }

    @Override
    public void showLoader() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        mProgressBar.setVisibility(View.GONE);
    }
}