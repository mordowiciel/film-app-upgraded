package com.example.mordowiciel.filmappupgraded.ui.home.fragment.di;

import com.example.mordowiciel.filmappupgraded.rest.MovieService;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.MovieFragment;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.MovieFragmentPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieFragmentModule {

    private final MovieFragment movieFragment;

    public MovieFragmentModule(MovieFragment movieFragment) {
        this.movieFragment = movieFragment;
    }

    @Provides
    MovieFragmentPresenter provideMovieFragmentPresenter(MovieService movieService) {
        return new MovieFragmentPresenter(movieFragment, movieService);
    }
}
