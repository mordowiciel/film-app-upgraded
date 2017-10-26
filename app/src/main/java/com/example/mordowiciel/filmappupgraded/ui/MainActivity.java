package com.example.mordowiciel.filmappupgraded.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.mordowiciel.filmappupgraded.BuildConfig;
import com.example.mordowiciel.filmappupgraded.R;
import com.example.mordowiciel.filmappupgraded.model.Movie;
import com.example.mordowiciel.filmappupgraded.rest.MovieService;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.title)
    TextView titleText;

    @BindView(R.id.vote_average)
    TextView voteAverage;

    @BindView(R.id.vote_count)
    TextView voteCount;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MovieService.MAIN_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        MovieService movieService = retrofit.create(MovieService.class);
        movieService.getMovie(335984, BuildConfig.MOVIE_DB_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onResponse);
    }

    private void onResponse(Movie movie) {
        titleText.setText(movie.getTitle());
        voteAverage.setText(String.valueOf(movie.getVoteAverage()));
        voteCount.setText(String.valueOf(movie.getVoteCount()));
    }
}
