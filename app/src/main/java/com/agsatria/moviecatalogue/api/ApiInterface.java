package com.agsatria.moviecatalogue.api;

import com.agsatria.moviecatalogue.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
  @GET("discover/{type}")
  Call<MovieResponse> getMovies(@Path("type") String movieType);
}
