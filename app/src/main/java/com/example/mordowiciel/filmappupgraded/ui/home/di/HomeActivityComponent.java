package com.example.mordowiciel.filmappupgraded.ui.home.di;

import com.example.mordowiciel.filmappupgraded.di.app.AppComponent;
import com.example.mordowiciel.filmappupgraded.di.scopes.ActivityScope;
import com.example.mordowiciel.filmappupgraded.ui.home.HomeActivity;

import dagger.Component;

@ActivityScope
@Component(modules = HomeActivityModule.class, dependencies = AppComponent.class)
public interface HomeActivityComponent {

    void inject(HomeActivity homeActivity);
}
