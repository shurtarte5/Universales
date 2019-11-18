package com.hurtarte.universales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FavoriteFragment extends Fragment {

    private MovieLocalViewModel movieLocalModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favorite,container,false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerviewFavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        final MovieLocalAdapter adapter = new MovieLocalAdapter(getContext());
        recyclerView.setAdapter(adapter);





        movieLocalModel = ViewModelProviders.of(getActivity()).get(MovieLocalViewModel.class);

        movieLocalModel.getFavMovies().observe(this, new Observer<List<MovieLocal>>() {
            @Override
            public void onChanged(List<MovieLocal> movieLocals) {
                //Toast.makeText(getContext(),"cambio",Toast.LENGTH_SHORT).show();
                adapter.setMoviesLocal(movieLocals);
            }
        });


        return rootView;
    }
}
