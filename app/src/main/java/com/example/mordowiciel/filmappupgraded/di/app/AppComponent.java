package com.example.mordowiciel.filmappupgraded.di.app;

import android.app.Application;
import android.content.Context;

import com.example.mordowiciel.filmappupgraded.rest.MovieService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    Context getContext();

    Application getApplication();

    MovieService getMovieService();
}
