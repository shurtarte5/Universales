package com.hurtarte.universales;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {MovieLocal.class}, version = 1)
public abstract class MovieLocalDatabase extends RoomDatabase {

    private static MovieLocalDatabase instance;

    public abstract  MovieLocalDao moviedao();

    public static synchronized  MovieLocalDatabase getInstance(Context context){
        if(instance==null){

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MovieLocalDatabase.class,"movielocal_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }

        return instance;

    }

    private static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynTask(instance).execute();

        }
    };

    private static class PopulateDbAsynTask extends AsyncTask<Void,Void,Void>{

        private MovieLocalDao movieDao;

        public PopulateDbAsynTask(MovieLocalDatabase db) {
            movieDao=db.moviedao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

           // movieDao.insert(new MovieLocal("titulo 1","rating 1","poster 1",0));
            //movieDao.insert(new MovieLocal("titulo 2","rating 2","poster 2",0));
          //  movieDao.insert(new MovieLocal(23,"titulo 3","rating 3","poster 3",1,"calidad la pelicula"));



            return null;
        }
    }






}
