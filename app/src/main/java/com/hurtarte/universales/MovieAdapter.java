package com.hurtarte.universales;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<MovieResult.ResultsBean> mMovieList;
    private Context mContext;


    private OnItemClickListener mListener;
    private MovieLocalViewModel viewModel;


    public interface OnItemClickListener {

        void onInsertSwich(String title, String rating);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;


    }




    public static  class MovieViewHolder extends  RecyclerView.ViewHolder{

        public ImageView poster;
        public TextView title;
        public TextView rating;
        public Switch favorite;


        public MovieViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            poster =  itemView.findViewById(R.id.imageViewCardView);
            title = itemView.findViewById(R.id.textViewTituloCardView);
            rating = itemView.findViewById(R.id.textViewCalificacionCardView);
            favorite= itemView.findViewById(R.id.switchFavorite);


         /*   favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!= null){

                       String titulo = title.getText().toString();
                       String r=rating.getText().toString();
                       int p= getAdapterPosition();

                       if(favorite.isChecked()){

                       }








                    }
                }
            });*/


        }


    }

    public MovieAdapter(List<MovieResult.ResultsBean> movieList, Context context){

        mMovieList=movieList;
        mContext=context;



    }


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_main, parent,false);

        MovieViewHolder mvh=new MovieViewHolder(v, mListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        MovieResult.ResultsBean currentMovie = mMovieList.get(position);
        //MovieLocalViewModel myViewModel = new MovieLocalViewModel();

        String url = "https://image.tmdb.org/t/p/w500" + currentMovie.getPoster_path();

        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.poster);


        holder.title.setText(currentMovie.getTitle());
        String rating1=currentMovie.getVote_average()+"";
        holder.rating.setText(rating1);









    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
}
