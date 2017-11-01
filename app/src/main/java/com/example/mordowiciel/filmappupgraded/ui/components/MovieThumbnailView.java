package com.example.mordowiciel.filmappupgraded.ui.components;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mordowiciel.filmappupgraded.App;
import com.example.mordowiciel.filmappupgraded.R;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieThumbnailView extends FrameLayout {

    @BindView(R.id.movie_poster)
    ImageView moviePoster;

    @BindView(R.id.movie_title)
    TextView movieTitle;

    @Inject
    Picasso mPicasso;

    public MovieThumbnailView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public MovieThumbnailView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {

        App.get(context)
                .getAppComponent()
                .inject(this);

        inflate(context, R.layout.movie_thumbnail, this);
        ButterKnife.bind(this);

        String emptyString = "";
        movieTitle.setText(emptyString);
    }

    public void setImageFromUrl(String imagePosterPath) {
        mPicasso
                .load(imagePosterPath)
                .fit()
                .into(moviePoster);
    }

    public void setTitle(String title) {
        movieTitle.setText(title);
    }
}
