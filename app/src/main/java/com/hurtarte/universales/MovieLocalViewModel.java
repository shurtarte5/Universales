package com.hurtarte.universales;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieLocalViewModel extends AndroidViewModel {

    private MovieLocalRepository repository;
    private LiveData<List<MovieLocal>> allMovies;



    public MovieLocalViewModel(@NonNull Application application) {
        super(application);

        repository= new MovieLocalRepository(application);
        allMovies= repository.getAllMovies();
    }

    public void insert(MovieLocal movie){
        repository.insert(movie);

    }

    public void delete(MovieLocal movie){
        repository.delete(movie);

    }

    public LiveData<List<MovieLocal>> getAllMovies(){
        return allMovies;
    }
}
