package com.hurtarte.universales;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieLocalDao {

    @Insert
    void insert(MovieLocal movie);

    @Delete
    void delete(MovieLocal movie);

    @Query("DELETE FROM movielocal_table")
    void deleteAllMovies();

    @Query("SELECT * FROM  movielocal_table ORDER BY id DESC")
    LiveData<List<MovieLocal>> getAllMovies();


}
