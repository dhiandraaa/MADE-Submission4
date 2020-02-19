package com.agsatria.moviecatalogue.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.agsatria.moviecatalogue.model.Movie;

import java.util.List;

@Dao
public interface MovieHelper {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void insert(Movie... movies);

	@Query("DELETE FROM movie WHERE uid = :uid")
	void deleteByUid(int uid);

	@Query("SELECT COUNT(uid) FROM movie WHERE title = :title")
	int getMovieByTitle(String title);

	@Query("SELECT * FROM movie WHERE movieType = :movieType")
	List<Movie> getMoviesByMovieType(int movieType);

}
