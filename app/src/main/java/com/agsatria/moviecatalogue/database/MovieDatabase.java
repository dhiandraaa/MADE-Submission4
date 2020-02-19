package com.agsatria.moviecatalogue.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.agsatria.moviecatalogue.model.Movie;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
	public abstract MovieHelper getMovieHelper();
}
