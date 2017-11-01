package com.example.mordowiciel.filmappupgraded.ui;

import com.example.mordowiciel.filmappupgraded.ActivityScope;
import com.example.mordowiciel.filmappupgraded.AppComponent;

import dagger.Component;

@ActivityScope
@Component(modules = HomeActivityModule.class, dependencies = AppComponent.class)
public interface HomeActivityComponent {

    void inject(HomeActivity homeActivity);
}
