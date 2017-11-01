package com.example.mordowiciel.filmappupgraded.ui.home.fragment.di;

import com.example.mordowiciel.filmappupgraded.di.app.AppComponent;
import com.example.mordowiciel.filmappupgraded.di.scopes.FragmentScope;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.MovieFragment;

import dagger.Component;

@FragmentScope
@Component(modules = MovieFragmentModule.class, dependencies = AppComponent.class)
public interface MovieFragmentComponent {

    void inject(MovieFragment movieFragment);
}
