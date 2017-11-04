package com.example.mordowiciel.filmappupgraded.ui;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.mordowiciel.filmappupgraded.R;
import com.example.mordowiciel.filmappupgraded.model.Movie;
import com.example.mordowiciel.filmappupgraded.ui.components.MovieThumbnailView;
import com.example.mordowiciel.filmappupgraded.util.TheMovieDbUrlUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeActivityAdapter extends RecyclerView.Adapter<HomeActivityAdapter.ViewHolder> {

    List<Movie> mMovieDataset = new ArrayList<>();


    public void setDataset(List<Movie> dataset) {
        this.mMovieDataset = dataset;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public MovieThumbnailView movieThumbnailView;

        public ViewHolder(MovieThumbnailView itemView) {
            super(itemView);
            movieThumbnailView = itemView;
        }
    }

    @Override
    public HomeActivityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MovieThumbnailView movieThumbnailView = (MovieThumbnailView) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.movie_thumbnail, parent, false);

        return new ViewHolder(movieThumbnailView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movie itemMovie = mMovieDataset.get(position);
        holder.movieThumbnailView.setTitle(itemMovie.getTitle());
        holder.movieThumbnailView.setImageFromUrl(TheMovieDbUrlUtil.
                provideFullPosterUrl(itemMovie.getPosterSubpath()));
    }

    @Override
    public int getItemCount() {
        return mMovieDataset.size();
    }
}
