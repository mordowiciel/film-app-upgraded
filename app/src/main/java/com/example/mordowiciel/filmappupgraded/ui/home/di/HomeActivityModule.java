package com.example.mordowiciel.filmappupgraded.ui.home.di;

import com.example.mordowiciel.filmappupgraded.ui.home.HomeActivityPresenter;
import com.example.mordowiciel.filmappupgraded.ui.home.HomeActivityView;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeActivityModule {

    private HomeActivityView homeActivity;

    public HomeActivityModule(HomeActivityView homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides
    HomeActivityPresenter provideHomeActivityPresenter() {
        return new HomeActivityPresenter(homeActivity);
    }
}
