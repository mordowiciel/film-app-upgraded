package com.example.mordowiciel.filmappupgraded.di.app;

import android.app.Application;
import android.content.Context;

import com.example.mordowiciel.filmappupgraded.rest.MovieService;
import com.example.mordowiciel.filmappupgraded.rxbus.RxBus;
import com.example.mordowiciel.filmappupgraded.ui.components.MovieThumbnailView;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(MovieThumbnailView movieThumbnailView);

    Context getContext();
    Application getApplication();

    Picasso getPicasso();
    MovieService getMovieService();

    RxBus getRxBus();
}
