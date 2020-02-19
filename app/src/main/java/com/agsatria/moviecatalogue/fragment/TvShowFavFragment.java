package com.agsatria.moviecatalogue.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agsatria.moviecatalogue.model.MainViewModel;
import com.agsatria.moviecatalogue.R;
import com.agsatria.moviecatalogue.adapter.MovieFavAdapter;
import com.agsatria.moviecatalogue.database.MovieHelper;
import com.agsatria.moviecatalogue.database.MovieDatabase;
import com.agsatria.moviecatalogue.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class TvShowFavFragment extends Fragment {

	private MovieFavAdapter adapter;

	public TvShowFavFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_tv_show_fav, container, false);
		RecyclerView listFavMovie = rootView.findViewById(R.id.list_fav_movie);
		listFavMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
		adapter = new MovieFavAdapter(getActivity());
		listFavMovie.setAdapter(adapter);

		ArrayList<Movie> data = (ArrayList<Movie>) loadFavMovies();

		MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
		mainViewModel.setFavMovie(data);
		mainViewModel.getMovies().observe(this, getMovies);

		return rootView;
	}

	private List<Movie> loadFavMovies() {
		MovieDatabase database = Room.databaseBuilder(getActivity(), MovieDatabase.class, "db_movie")
				.allowMainThreadQueries()
				.build();
		MovieHelper movieHelper = database.getMovieHelper();
		return movieHelper.getMoviesByMovieType(2);
	}

	Observer<ArrayList<Movie>> getMovies = new Observer<ArrayList<Movie>>() {
		@Override
		public void onChanged(@Nullable ArrayList<Movie> movies) {
			if (movies != null)
				adapter.setMovies(movies);
		}
	};
}
