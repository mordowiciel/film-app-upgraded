package com.example.mordowiciel.filmappupgraded;

import android.app.Application;
import android.content.Context;

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

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
