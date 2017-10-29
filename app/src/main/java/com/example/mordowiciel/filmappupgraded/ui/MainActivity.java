package com.example.mordowiciel.filmappupgraded.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.mordowiciel.filmappupgraded.R;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private MainActivityPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainActivityPresenter(this);
        addMainFragment();
    }

    public void addMainFragment() {
        MainFragment mainFragment = new MainFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .add(R.id.home_activity_root, mainFragment, MainFragment.class.getSimpleName())
                .commit();
    }
}
