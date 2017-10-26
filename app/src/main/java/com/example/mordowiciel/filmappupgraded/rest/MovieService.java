package com.example.mordowiciel.filmappupgraded.rest;

import com.example.mordowiciel.filmappupgraded.model.Movie;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    String MAIN_URL = "https://api.themoviedb.org/3/";

    @GET("movie/{movie_id}")
    Observable<Movie> getMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey);
}
