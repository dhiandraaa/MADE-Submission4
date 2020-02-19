package com.agsatria.moviecatalogue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.agsatria.moviecatalogue.R;
import com.agsatria.moviecatalogue.fragment.FavoriteFragment;
import com.agsatria.moviecatalogue.fragment.MovieFragment;
import com.agsatria.moviecatalogue.fragment.TvShowFragment;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	Toolbar toolbar = findViewById(R.id.toolbar);
	setSupportActionBar(toolbar);

		BottomNavigationView navigation = findViewById(R.id.navigation);
		navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

		if (getSupportActionBar() != null)
			getSupportActionBar().setTitle(R.string.title_movie);
		if (savedInstanceState == null) {
			loadFragment(new MovieFragment());
		}
	}

	private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
			Fragment fragment;
			switch (menuItem.getItemId()) {
				case R.id.nav_movie:
					if (getSupportActionBar() != null)
						getSupportActionBar().setTitle(R.string.title_movie);
					fragment = new MovieFragment();
					loadFragment(fragment);
					return true;
				case R.id.nav_tv:
					if (getSupportActionBar() != null)
						getSupportActionBar().setTitle(R.string.title_tv);
					fragment = new TvShowFragment();
					loadFragment(fragment);
					return true;
				case R.id.nav_fav:
					if (getSupportActionBar() != null)
						getSupportActionBar().setTitle(R.string.title_fav);
					fragment = new FavoriteFragment();
					loadFragment(fragment);
					return true;
			}
			return false;
		}
	};

	private void loadFragment(Fragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.frame_container, fragment);
		transaction.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	getMenuInflater().inflate(R.menu.main_menu, menu);
	return true;
 }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
   if (item.getItemId() == R.id.btn_setting){
	 Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
	 startActivity(mIntent);
   }
   return super.onOptionsItemSelected(item);
 }

}
