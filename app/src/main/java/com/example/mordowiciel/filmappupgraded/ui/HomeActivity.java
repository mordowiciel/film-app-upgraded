package com.example.mordowiciel.filmappupgraded.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.mordowiciel.filmappupgraded.R;

public class HomeActivity extends AppCompatActivity implements HomeActivityView {

    private HomeActivityPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mPresenter = new HomeActivityPresenter(this);
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
