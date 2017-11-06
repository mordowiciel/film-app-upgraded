package com.example.mordowiciel.filmappupgraded.ui.home.fragment;


import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mordowiciel.filmappupgraded.App;
import com.example.mordowiciel.filmappupgraded.R;
import com.example.mordowiciel.filmappupgraded.model.Movie;
import com.example.mordowiciel.filmappupgraded.ui.HomeActivityAdapter;
import com.example.mordowiciel.filmappupgraded.ui.home.adapter.GridLayoutItemDecorator;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.di.DaggerMovieFragmentComponent;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.di.MovieFragmentComponent;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.di.MovieFragmentModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment implements MovieFragmentView {

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.movie_recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    MovieFragmentPresenter mPresenter;

    private HomeActivityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private GridLayoutItemDecorator mItemDecorator;

    private final int COLUMN_SPAN = 2;

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

        Resources resources = getContext().getResources();
        mItemDecorator = new GridLayoutItemDecorator(
                resources.getDimensionPixelSize(R.dimen.recycler_view_left_space),
                resources.getDimensionPixelSize(R.dimen.recycler_view_right_space),
                resources.getDimensionPixelSize(R.dimen.recycler_view_top_space),
                resources.getDimensionPixelSize(R.dimen.recycler_view_bottom_space),
                COLUMN_SPAN);

        mAdapter = new HomeActivityAdapter();
        mLayoutManager = new GridLayoutManager(this.getContext(), COLUMN_SPAN);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(mItemDecorator);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);

        mPresenter.fetchMovieData();
    }

    @Override
    public void onStop() {
        super.onStop();
        mRecyclerView.setLayoutManager(null);
        mRecyclerView.removeItemDecoration(mItemDecorator);
        mRecyclerView.setAdapter(null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter = null;
        mRecyclerView = null;
    }

    @Override
    public void showMovieData(List<Movie> movieList) {
        mAdapter.setDataset(movieList);
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
