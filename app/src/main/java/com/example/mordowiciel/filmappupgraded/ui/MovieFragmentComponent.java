package com.example.mordowiciel.filmappupgraded.ui;

import com.example.mordowiciel.filmappupgraded.AppComponent;
import com.example.mordowiciel.filmappupgraded.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(modules = MovieFragmentModule.class, dependencies = AppComponent.class)
public interface MovieFragmentComponent {

    void inject(MovieFragment movieFragment);
}
