package com.example.mordowiciel.filmappupgraded.ui.home;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.mordowiciel.filmappupgraded.App;
import com.example.mordowiciel.filmappupgraded.R;
import com.example.mordowiciel.filmappupgraded.ui.home.di.DaggerHomeActivityComponent;
import com.example.mordowiciel.filmappupgraded.ui.home.di.HomeActivityComponent;
import com.example.mordowiciel.filmappupgraded.ui.home.di.HomeActivityModule;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.MovieFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeActivityView {

    @BindView(R.id.home_toolbar)
    Toolbar mToolbar;

    @Inject
    HomeActivityPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        HomeActivityComponent component = DaggerHomeActivityComponent
                .builder()
                .homeActivityModule(new HomeActivityModule(this))
                .appComponent(App.get(this).getAppComponent())
                .build();

        component.inject(this);
        addMainFragment();
    }

    public void addMainFragment() {
        MovieFragment mainFragment = new MovieFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .add(R.id.home_activity_root, mainFragment, MovieFragment.class.getSimpleName())
                .commit();
    }
}
