package com.hurtarte.universales;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/3/movie/{category}")
    Call<MovieResult> listOfMovies(
            @Path("category") String category,
            @Query("api_key") String apiKey,
            @Query("languaje")String languaje,
            @Query("page") int page

    );
}
