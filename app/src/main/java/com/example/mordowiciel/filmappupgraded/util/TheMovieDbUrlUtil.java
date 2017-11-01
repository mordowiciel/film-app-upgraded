package com.example.mordowiciel.filmappupgraded.util;


import android.util.Log;

public class TheMovieDbUrlUtil {

    private final static String MAIN_URL = "https://api.themoviedb.org/3/";

    private final static String IMAGE_MAIN_URL = "https://image.tmdb.org/t/p/";
    private final static String FILE_SIZE_URL_500 = "w500";

    public static String provideFullPosterUrl(String posterUrlSubpath) {

        StringBuilder linkBuilder = new StringBuilder();
        linkBuilder.append(IMAGE_MAIN_URL);
        linkBuilder.append(FILE_SIZE_URL_500);
        linkBuilder.append(posterUrlSubpath);

        Log.d("TheMovieDbUrlUtil", "url : " + linkBuilder.toString());

        return linkBuilder.toString();
    }


}
