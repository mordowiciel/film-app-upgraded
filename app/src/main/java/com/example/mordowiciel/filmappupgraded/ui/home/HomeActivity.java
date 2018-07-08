package com.example.mordowiciel.filmappupgraded.ui.home;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mordowiciel.filmappupgraded.App;
import com.example.mordowiciel.filmappupgraded.R;
import com.example.mordowiciel.filmappupgraded.rxbus.event.ActionSearchEvent;
import com.example.mordowiciel.filmappupgraded.ui.home.di.DaggerHomeActivityComponent;
import com.example.mordowiciel.filmappupgraded.ui.home.di.HomeActivityComponent;
import com.example.mordowiciel.filmappupgraded.ui.home.di.HomeActivityModule;
import com.example.mordowiciel.filmappupgraded.ui.home.fragment.MovieFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class HomeActivity extends AppCompatActivity implements HomeActivityView {

    @Inject
    HomeActivityPresenter mPresenter;

    @BindView(R.id.home_toolbar)
    Toolbar mToolbar;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        int itemID = menuItem.getItemId();
        switch (itemID) {
            case (R.id.action_filter):
                Toast.makeText(this, "Filter not implemented yet :(", Toast.LENGTH_LONG).show();
                return true;
            case (R.id.action_search):
                Toast.makeText(this, "Search not implemented yet :(", Toast.LENGTH_LONG).show();
                Timber.d("Creating ActionSearchEvent");
                App.get(this)
                        .getAppComponent()
                        .getRxBus()
                        .send(new ActionSearchEvent());
                return true;
            case (R.id.action_settings):
                Toast.makeText(this, "Settings not implemented yet :(", Toast.LENGTH_LONG).show();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void addMainFragment() {
        MovieFragment mainFragment = new MovieFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction
                .add(R.id.home_activity_root, mainFragment, MovieFragment.class.getSimpleName())
                .commit();
    }
}
