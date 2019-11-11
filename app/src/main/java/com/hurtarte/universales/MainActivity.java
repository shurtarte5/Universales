package com.hurtarte.universales;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

   /* public static String BASE_URL="https://api.themoviedb.org";

    public static int PAGE = 1;

    public static String API_KEY= "3abc5f93e76fd7c3dbdfd790df363d4a";

    public static String LANGUAJE = "en-US";

    public static String CATEGORY="popular";

    private TextView textview;
    //List <MovieResult.ResultsBean> listOfMovies;

    List <MovieResult.ResultsBean> listOfMovies;

    private RecyclerView mRecyclerViewMain;
    private RecyclerView.Adapter mAdapterMain;
    private RecyclerView.LayoutManager mLayoutManagerMain;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottonNav = findViewById(R.id.bottomNvigation);
        bottonNav.setOnNavigationItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                    new HomeFragment()).commit();
        }


      // textview= (TextView) findViewById(R.id.mytextview);

/*

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
                mRecyclerViewMain = findViewById(R.id.recyclerView);
                mRecyclerViewMain.setHasFixedSize(true);
                mLayoutManagerMain = new LinearLayoutManager(getApplicationContext());
                mAdapterMain = new MovieAdapter(listOfMovies, getApplicationContext());

                mRecyclerViewMain.setLayoutManager(mLayoutManagerMain);
                mRecyclerViewMain.setAdapter(mAdapterMain);

                //MovieResult.ResultsBean first = listOfMovies.get(0);



               // textview.setText(first.getTitle());

               // for (MovieResult.ResultsBean movies : listOfMovies) {
                 //  String content = "";
                   // content += movies.getTitle() + "\n";


                  //  textview.append(content);
                //}



            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                t.printStackTrace();
            }
        });




*/



    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_fav:
                            selectedFragment = new FavoriteFragment();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                            selectedFragment).commit();


                    return true;
                }
            };
}
