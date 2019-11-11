package com.hurtarte.universales;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieLocalAdapter extends RecyclerView.Adapter<MovieLocalAdapter.MovieLocalHolder> {

    private List<MovieLocal> movieLocalList=new ArrayList<>();


    @NonNull
    @Override
    public MovieLocalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_movielocal,parent,false);

        return new MovieLocalHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieLocalHolder holder, int position) {

        MovieLocal curretnMocie = movieLocalList.get(position);

        holder.textViewTitle.setText(curretnMocie.getTitulo());
        holder.textViewRating.setText(curretnMocie.getRating());
        holder.textViewPoster.setText(curretnMocie.getPosterpath());


    }

    @Override
    public int getItemCount() {
        return movieLocalList.size();
    }

    public void setMoviesLocal(List<MovieLocal> movies){
        this.movieLocalList=movies;
        notifyDataSetChanged();

    }

    class MovieLocalHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle;
        private TextView textViewRating;
        private TextView textViewPoster;

        public MovieLocalHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle= itemView.findViewById(R.id.text_view_title);
            textViewRating=itemView.findViewById(R.id.text_view_rating);
            textViewPoster=itemView.findViewById(R.id.text_view_image);


        }
    }
}
