package com.stimednp.aplikasimoviecataloguesub3.API;

import com.stimednp.aplikasimoviecataloguesub3.Model.MoviesResponse;
import com.stimednp.aplikasimoviecataloguesub3.Model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by rivaldy on 7/21/2019.
 */

public interface APIMovieTv {
    @GET("discover/movie")
    Call<MoviesResponse> getMovieList(
            @Query("api_key") String apiKey,
            @Query("language") String language);

    @GET("discover/tv")
    Call<TvShowResponse> getTvShowList(
            @Query("api_key") String apiKey,
            @Query("language") String language);
}
