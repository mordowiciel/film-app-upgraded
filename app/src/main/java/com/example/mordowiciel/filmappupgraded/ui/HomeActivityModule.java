package com.example.mordowiciel.filmappupgraded.ui;


import android.content.Context;

import com.example.mordowiciel.filmappupgraded.ActivityContext;

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
