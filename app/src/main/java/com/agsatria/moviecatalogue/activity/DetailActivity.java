package com.agsatria.moviecatalogue.activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.agsatria.moviecatalogue.BuildConfig;
import com.agsatria.moviecatalogue.R;
import com.agsatria.moviecatalogue.database.MovieHelper;
import com.agsatria.moviecatalogue.database.MovieDatabase;
import com.agsatria.moviecatalogue.model.Movie;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

	private Movie movie;
	private MovieHelper movieHelper;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Intent intent = getIntent();
	  movie = intent.getParcelableExtra("movie");

		showDetails(movie);

	  movieHelper = Room.databaseBuilder(this, MovieDatabase.class, "db_movie")
			  .allowMainThreadQueries()
			  .build()
			  .getMovieHelper();
  }

  private void showDetails(Movie movie) {
    TextView title = findViewById(R.id.title);
    TextView originalLanguage = findViewById(R.id.original_language);
    TextView description = findViewById(R.id.overview);
    TextView releaseDate = findViewById(R.id.release_date);
    TextView voteAverage = findViewById(R.id.vote_average);
    ImageView poster = findViewById(R.id.poster);
    ImageView bgImage = findViewById(R.id.bg_image);
    ProgressBar pgBar = findViewById(R.id.pg_bar);

    pgBar.setVisibility(View.VISIBLE);
    title.setText(movie.getTitle());
    originalLanguage.setText(movie.getOriginalLanguage());
    description.setText(movie.getDescription());
    releaseDate.setText(movie.getReleaseDate());
    voteAverage.setText(movie.getVoteAverage());
		Glide.with(this)
			.load(BuildConfig.IMG_URL + movie.getPoster())
			.into(poster);
		Glide.with(this)
			.load(BuildConfig.IMG_URL + movie.getPoster())
			.into(bgImage);
	pgBar.setVisibility(View.GONE);

    Toolbar toolbar = findViewById(R.id.toolbar_detail);
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null){
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
			setTitle(R.string.details);
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.favorit_menu, menu);
		return true;
	}

	@Override
  public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				this.finish();
				break;
			case R.id.btn_fav:
				try {
					if (movieHelper.getMovieByTitle(movie.getTitle()) > 0) {
						item.setEnabled(false);
					} else {
						movieHelper.insert(movie);
						setResult(RESULT_OK);
						Toast.makeText(this, R.string.success_insert_fav, Toast.LENGTH_SHORT).show();
					}
				} catch (SQLiteConstraintException e) {
					Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
				}
		}
		return true;
  }
}
