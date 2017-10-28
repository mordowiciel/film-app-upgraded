package com.example.mordowiciel.filmappupgraded.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mordowiciel.filmappupgraded.R;
import com.example.mordowiciel.filmappupgraded.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    @BindView(R.id.title)
    TextView titleText;

    @BindView(R.id.vote_average)
    TextView voteAverage;

    @BindView(R.id.vote_count)
    TextView voteCount;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private MainActivityPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mPresenter = new MainActivityPresenter(this);
    }

    @Override
    protected void onStart() {
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
