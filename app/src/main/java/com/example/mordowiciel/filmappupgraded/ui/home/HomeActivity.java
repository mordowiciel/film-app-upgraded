package com.example.mordowiciel.filmappupgraded.ui.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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

    @Inject
    HomeActivityPresenter mPresenter;

    @BindView(R.id.home_toolbar)
    Toolbar mToolbar;

    @BindView(R.id.home_drawer_layout)
    DrawerLayout mDrawerLayout;

    ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        setupNavigationDrawer();

        HomeActivityComponent component = DaggerHomeActivityComponent
                .builder()
                .homeActivityModule(new HomeActivityModule(this))
                .appComponent(App.get(this).getAppComponent())
                .build();

        component.inject(this);
        addMainFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int itemID = menuItem.getItemId();
        switch (itemID) {
            case (android.R.id.home):
                mDrawerLayout.openDrawer(Gravity.START);
                return true;
            case (R.id.action_filter):
                Toast.makeText(this, "Filter feature is not implemented yet :(", Toast.LENGTH_LONG).show();
                return true;
            case (R.id.action_search):
                Toast.makeText(this, "Search feature is not implemented yet :(", Toast.LENGTH_LONG).show();
                return true;
            case (R.id.action_settings):
                Toast.makeText(this, "Settings feature is not implemented yet :(", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setupNavigationDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void addMainFragment() {
        MovieFragment mainFragment = new MovieFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .add(R.id.home_content, mainFragment, MovieFragment.class.getSimpleName())
                .commit();
    }
}
