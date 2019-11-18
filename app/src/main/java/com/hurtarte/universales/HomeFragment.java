package com.hurtarte.universales;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hurtarte.universales.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private MovieLocalViewModel movieLocalModel;

    public static String BASE_URL="https://api.themoviedb.org";

    public static int PAGE = 1;

    public static String API_KEY= "3abc5f93e76fd7c3dbdfd790df363d4a";

    public static String LANGUAJE = "en-US";

    public static String CATEGORY="popular";

    public static final String SHARED_PREFS= "sharedPrefs";
    public static final String CONTADOR1= "contador";


    public static final String EXTRA_TITLE ="TITLE";
    public static final String EXTRA_POSTER ="POSTER";
    public static final String EXTRA_OVERVIEW ="OVERVIEW";
    public static final String EXTRA_ID ="ID";




    private TextView textview;
    //List <MovieResult.ResultsBean> listOfMovies;

    List<MovieResult.ResultsBean> listOfMovies;

    private RecyclerView mRecyclerViewMain;
   // private RecyclerView.Adapter mAdapterMain;
    private MovieAdapter mAdapterMain;
    private RecyclerView.LayoutManager mLayoutManagerMain;
    private MovieLocalViewModel a;
    private int contador=0;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home,container,false);

            loadData();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieResult> call = myInterface.listOfMovies(CATEGORY,API_KEY,LANGUAJE,PAGE);
        //a = new MovieLocalViewModel(getActivity().getApplication());
        movieLocalModel= new MovieLocalViewModel(getActivity().getApplication());


        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult results = response.body();



                listOfMovies= results.getResults();








                if(contador<1){
                    for(int i=0;i<listOfMovies.size();i++){






                        movieLocalModel.insert(new MovieLocal(listOfMovies.get(i).getId(),listOfMovies.get(i).getTitle(),Double.toString(listOfMovies.get(i).getVote_average()),listOfMovies.get(i).getPoster_path(),0,listOfMovies.get(i).getOverview()));

                        contador++;
                        saveContador();



                    }

                }



            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                t.printStackTrace();
            }
        });


        mRecyclerViewMain = rootView.findViewById(R.id.recyclerView);
        mRecyclerViewMain.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViewMain.setHasFixedSize(true);
        mAdapterMain  = new MovieAdapter(getContext());
        mRecyclerViewMain.setAdapter(mAdapterMain);



        movieLocalModel = ViewModelProviders.of(getActivity()).get(MovieLocalViewModel.class);

        movieLocalModel.getAllMovies().observe(getActivity(), new Observer<List<MovieLocal>>() {
            @Override
            public void onChanged(List<MovieLocal> movieLocals) {

                mAdapterMain.setMoviesLocal(movieLocals);

            }
        });


        mAdapterMain.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {


            @Override
            public void onInsertSwich(MovieLocal movie, int fav) {

                int id= movie.getId();

                MovieLocal movieL = new MovieLocal(movie.getIdMovie(), movie.getTitulo(),movie.getRating(),movie.getPosterpath(),fav,movie.getOverview());
                movieL.setId(id);

                movieLocalModel.update(movieL);







            }

            @Override
            public void onItemClick(MovieLocal movie) {

                Intent intent = new Intent(getActivity(), MovieDetail.class);
                intent.putExtra(EXTRA_ID,movie.getIdMovie());
                intent.putExtra(EXTRA_TITLE,movie.getTitulo());
                intent.putExtra(EXTRA_POSTER, movie.getPosterpath());
                intent.putExtra(EXTRA_OVERVIEW,movie.getOverview());



                startActivity(intent);



            }
        });








        return rootView;
    }

    public void saveContador(){

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();
        editor.putInt(CONTADOR1,contador);
        editor.apply();



    }

    public void loadData(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        contador = sharedPreferences.getInt(CONTADOR1,0);


    }






}
