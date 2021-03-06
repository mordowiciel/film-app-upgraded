package com.example.mordowiciel.filmappupgraded;

import android.app.Application;
import android.content.Context;

import com.example.mordowiciel.filmappupgraded.di.app.AppComponent;
import com.example.mordowiciel.filmappupgraded.di.app.AppModule;
import com.example.mordowiciel.filmappupgraded.di.app.DaggerAppComponent;

import timber.log.Timber;

public class App extends Application {

    protected AppComponent appComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
