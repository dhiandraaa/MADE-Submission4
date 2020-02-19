package com.agsatria.moviecatalogue.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.agsatria.moviecatalogue.activity.MainActivity;
import com.agsatria.moviecatalogue.api.ApiClient;
import com.agsatria.moviecatalogue.api.ApiInterface;
import com.agsatria.moviecatalogue.model.Movie;
import com.agsatria.moviecatalogue.model.MovieResponse;

public class MainViewModel extends ViewModel {
  private MutableLiveData<ArrayList<Movie>> movieList = new MutableLiveData<>();

	public void setMovies(final String movieType) {
	ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
	Call<MovieResponse> call = apiService.getMovies(movieType);
	call.enqueue(new Callback<MovieResponse>() {
	  @Override
	  public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
	    try {
		  ArrayList<Movie> movies = response.body().getResults();
		    for (Movie data : movies) {
			    data.setMovieType(movieType.equals("movie") ? 1 : 2);
		    }
		  movieList.postValue(movies);
	    } catch (Exception e){
		    Log.d(MainActivity.class.getSimpleName(), e.getLocalizedMessage());
			}
	  }

	  @Override
	  public void onFailure(Call<MovieResponse> call, Throwable t) {
			Log.d(MainActivity.class.getSimpleName(), t.getLocalizedMessage());
	  }
	});
  }

	public void setFavMovie(ArrayList<Movie> movies) {
		movieList.postValue(movies);
	}

  public LiveData<ArrayList<Movie>> getMovies() {
    return movieList;
  }
}
