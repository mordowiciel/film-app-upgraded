package com.example.mordowiciel.filmappupgraded;

import android.app.Application;
import android.content.Context;

import com.example.mordowiciel.filmappupgraded.rest.MovieService;

import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context getContext();

    Application getApplication();

    MovieService getMovieService();
}
