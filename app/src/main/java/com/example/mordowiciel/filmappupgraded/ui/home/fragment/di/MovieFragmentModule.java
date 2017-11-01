package com.example.mordowiciel.filmappupgraded.ui.home.fragment.di;

import com.example.mordowiciel.filmappupgraded.rest.MovieService;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.MovieFragmentPresenter;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.MovieFragmentView;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieFragmentModule {

    private final MovieFragmentView movieFragment;

    public MovieFragmentModule(MovieFragmentView movieFragment) {
        this.movieFragment = movieFragment;
    }

    @Provides
    MovieFragmentPresenter provideMovieFragmentPresenter(MovieService movieService) {
        return new MovieFragmentPresenter(movieFragment, movieService);
    }
}
