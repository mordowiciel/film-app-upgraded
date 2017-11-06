package com.example.mordowiciel.filmappupgraded.ui.home.fragment;


import android.content.Context;
import android.graphics.Rect;
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

    HomeActivityAdapter mAdapter;

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

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this.getContext(), 2);
        mAdapter = new HomeActivityAdapter();

        /// Anonymous class containing custom ItemDecoration to separate the cards in the view.
        RecyclerView.ItemDecoration separator = new RecyclerView.ItemDecoration() {

            int leftSpace = 15;
            int rightSpace = 15;
            int topSpace = 35;
            int bottomSpace = 35;

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

                outRect.left = leftSpace;
                outRect.right = rightSpace;
                outRect.bottom = bottomSpace;

                if (parent.getChildLayoutPosition(view) < 2) {
                    outRect.top = topSpace;
                } else {
                    outRect.top = 0;
                }
            }
        };
        mRecyclerView.addItemDecoration(separator);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mPresenter.fetchMovieData();
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
