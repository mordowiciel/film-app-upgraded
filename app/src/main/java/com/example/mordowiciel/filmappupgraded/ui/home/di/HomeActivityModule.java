package com.example.mordowiciel.filmappupgraded.ui.home.di;


import android.content.Context;

import com.example.mordowiciel.filmappupgraded.di.qualifiers.ActivityContext;
import com.example.mordowiciel.filmappupgraded.ui.home.HomeActivity;
import com.example.mordowiciel.filmappupgraded.ui.home.HomeActivityPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    private HomeActivity homeActivity;

    public HomeActivityModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return homeActivity;
    }

    @Provides
    HomeActivityPresenter provideHomeActivityPresenter() {
        return new HomeActivityPresenter(homeActivity);
    }
}
