package com.hurtarte.universales;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieLocalRepository {

    private MovieLocalDao movieDao;
    private LiveData<List<MovieLocal>> allMovies;

    public MovieLocalRepository(Application application) {

        MovieLocalDatabase database = MovieLocalDatabase.getInstance(application);
        movieDao = database.moviedao();

        allMovies = movieDao.getAllMovies();




    }

    public void insert (MovieLocal movie){

        new InsertMovieLocalAsyncTask(movieDao).execute(movie);



    }

    public void delete(MovieLocal movie){
        new DeleteMovieLocalAsyncTask(movieDao).execute(movie);


    }

    public LiveData<List<MovieLocal>> getAllMovies() {
        return allMovies;
    }


    private static class InsertMovieLocalAsyncTask extends AsyncTask<MovieLocal, Void, Void>{

        private MovieLocalDao movieDao;

        private InsertMovieLocalAsyncTask (MovieLocalDao movieDao){
            this.movieDao=movieDao;
        }


        @Override
        protected Void doInBackground(MovieLocal... movieLocals) {
            movieDao.insert(movieLocals[0]);
            return null;
        }
    }

    private static class DeleteMovieLocalAsyncTask extends AsyncTask<MovieLocal, Void, Void>{

        private MovieLocalDao movieDao;

        private DeleteMovieLocalAsyncTask (MovieLocalDao movieDao){
            this.movieDao=movieDao;
        }


        @Override
        protected Void doInBackground(MovieLocal... movieLocals) {
            movieDao.delete(movieLocals[0]);
            return null;
        }
    }
}
