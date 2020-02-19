package com.agsatria.moviecatalogue.fragment;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.agsatria.moviecatalogue.model.MainViewModel;
import com.agsatria.moviecatalogue.R;
import com.agsatria.moviecatalogue.adapter.MovieAdapter;
import com.agsatria.moviecatalogue.model.Movie;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class TvShowFragment extends Fragment {

  private MovieAdapter adapter;
  private ShimmerFrameLayout mShimmerFrameLayout;
  private ProgressBar pgBar;

	 public TvShowFragment() {
		// Required empty public constructor
	 }

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
													Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.fragment_tv_show, container, false);
	RecyclerView list_tv_show = rootView.findViewById(R.id.list_movie);
	list_tv_show.setLayoutManager(new GridLayoutManager(getActivity(), 3));
	adapter = new MovieAdapter(getActivity());
	list_tv_show.setAdapter(adapter);

	 MainViewModel mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
	 mainViewModel.setMovies("tv");
	 mainViewModel.getMovies().observe(this, getMovies);
	 pgBar = rootView.findViewById(R.id.pg_bar);
	 mShimmerFrameLayout = rootView.findViewById(R.id.shimmer_view_container);
	 mShimmerFrameLayout.startShimmer();
	 pgBar.setVisibility(View.VISIBLE);

   return rootView;
 }
  private Observer<ArrayList<Movie>> getMovies = new Observer<ArrayList<Movie>>() {
	@Override
	public void onChanged(@Nullable ArrayList<Movie> movies) {
	  if (movies != null){
		mShimmerFrameLayout.stopShimmer();
		mShimmerFrameLayout.setVisibility(View.GONE);
		pgBar.setVisibility(View.GONE);
		adapter.setMovies(movies);
	  }
	}
  };
}
