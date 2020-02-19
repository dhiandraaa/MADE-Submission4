package com.agsatria.moviecatalogue.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.agsatria.moviecatalogue.R;
import com.agsatria.moviecatalogue.adapter.ViewPagerAdapter;

public class FavoriteFragment extends Fragment {

	public FavoriteFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_favorite, container, false);
		ViewPager viewPager = rootView.findViewById(R.id.viewpager);
		setupViewPager(viewPager);

		TabLayout tabLayout = rootView.findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);
		return rootView;
	}

	private void setupViewPager(ViewPager viewPager) {
		ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
		adapter.addFragment(new MovieFavFragment(), getResources().getString(R.string.title_movie));
		adapter.addFragment(new TvShowFavFragment(), getResources().getString(R.string.title_tv));
		viewPager.setAdapter(adapter);
	}

}
