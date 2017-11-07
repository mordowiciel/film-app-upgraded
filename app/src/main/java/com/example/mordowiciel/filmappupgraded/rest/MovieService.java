package com.example.mordowiciel.filmappupgraded.rest;

import com.example.mordowiciel.filmappupgraded.model.Movie;
import com.example.mordowiciel.filmappupgraded.model.MovieDiscover;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    String MAIN_URL = "https://api.themoviedb.org/3/";

    @GET("movie/{movie_id}")
    Observable<Movie> getMovie(@Path("movie_id") int movieId, @Query("api_key") String apiKey);

    @GET("discover/movie")
    Observable<MovieDiscover> discoverMovies(@Query("sort_by") String sortBy,
                                             @Query("page") int pageNumber,
                                             @Query("api_key") String apiKey);
}
