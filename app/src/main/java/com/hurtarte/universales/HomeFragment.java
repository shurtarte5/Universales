package com.hurtarte.universales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

    public static String BASE_URL="https://api.themoviedb.org";

    public static int PAGE = 1;

    public static String API_KEY= "3abc5f93e76fd7c3dbdfd790df363d4a";

    public static String LANGUAJE = "en-US";

    public static String CATEGORY="popular";

    private TextView textview;
    //List <MovieResult.ResultsBean> listOfMovies;

    List<MovieResult.ResultsBean> listOfMovies;

    private RecyclerView mRecyclerViewMain;
    private RecyclerView.Adapter mAdapterMain;
    private RecyclerView.LayoutManager mLayoutManagerMain;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home,container,false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface myInterface = retrofit.create(ApiInterface.class);

        Call<MovieResult> call = myInterface.listOfMovies(CATEGORY,API_KEY,LANGUAJE,PAGE);

        call.enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                MovieResult results = response.body();

                listOfMovies= results.getResults();
                mRecyclerViewMain = rootView.findViewById(R.id.recyclerView);
                mRecyclerViewMain.setHasFixedSize(true);
                mLayoutManagerMain = new LinearLayoutManager(getContext());
                mAdapterMain = new MovieAdapter(listOfMovies, getContext());

                mRecyclerViewMain.setLayoutManager(mLayoutManagerMain);
                mRecyclerViewMain.setAdapter(mAdapterMain);

            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                t.printStackTrace();
            }
        });



        return rootView;
    }
}
