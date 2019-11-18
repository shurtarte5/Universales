package com.hurtarte.universales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieLocalAdapter extends RecyclerView.Adapter<MovieLocalAdapter.MovieLocalHolder> {

    private List<MovieLocal> movieLocalList=new ArrayList<>();
    private Context mContext;

    public MovieLocalAdapter(Context context) {
        mContext=context;

    }

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

        String url = "https://image.tmdb.org/t/p/w500" + curretnMocie.getPosterpath();


        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.textViewPoster);

        holder.textViewTitle.setText(curretnMocie.getTitulo());
        holder.textViewRating.setText(curretnMocie.getRating());



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
        private ImageView textViewPoster;

        public MovieLocalHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle= itemView.findViewById(R.id.text_view_title);
            textViewRating=itemView.findViewById(R.id.text_view_rating);
            textViewPoster=itemView.findViewById(R.id.image_view_image);


        }
    }
}
