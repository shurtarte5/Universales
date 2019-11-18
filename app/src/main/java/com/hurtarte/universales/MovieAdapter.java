package com.hurtarte.universales;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>  implements Filterable {
    private List<MovieResult.ResultsBean> mMovieList;
    private Context mContext;
    private int contador;
    private static Context otherContext;
    private boolean swithfavorito = false;
    public int fav;


    private OnItemClickListener mListener;
    private Application mApplication;
    private MovieLocalViewModel viewModel;
    private int idmovie;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String SWITCH1 = "swith1";

    private List<MovieLocal> movieLocalList = new ArrayList<>();

    private List<MovieLocal> movieLocalListFull;


    public MovieAdapter(Context context) {

        mContext = context;



    }


    public interface OnItemClickListener {

        void onInsertSwich(MovieLocal movie, int fav);

        void onItemClick(MovieLocal movie);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {

        mListener = listener;



    }


    class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView poster;
        public TextView title;
        public TextView rating;
        public TextView noVisible;
        public Switch favorite;
        public boolean fav;


        public MovieViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            poster = itemView.findViewById(R.id.imageViewCardView);
            title = itemView.findViewById(R.id.textViewTituloCardView);
            rating = itemView.findViewById(R.id.textViewCalificacionCardView);
            //noVisible=itemView.findViewById(R.id.novisible);
            favorite = itemView.findViewById(R.id.switchFavorite);


            //click en un elemento del recyclerview

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(movieLocalList.get(position));

                    }


                }
            });









          favorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(listener!=null){

                        int position = getAdapterPosition();

                        if(position!=RecyclerView.NO_POSITION){

                            if(favorite.isChecked()){


                                listener.onInsertSwich(movieLocalList.get(position),1);

                            }else{

                                listener.onInsertSwich(movieLocalList.get(position),0);

                            }

                        }










                    }

                }
            });


        }


    }

  /*  public MovieAdapter(List<MovieResult.ResultsBean> movieList, Context context, Application application){

        mMovieList=movieList;
        mContext=context;
        mApplication=application;
        otherContext=context;



    }*/


    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_main, parent, false);

        MovieViewHolder mvh = new MovieViewHolder(v, mListener);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        int favorito;

        boolean favo = false;

        MovieLocal currentMovie = movieLocalList.get(position);
        //MovieLocalViewModel myViewModel = new MovieLocalViewModel();

        String url = "https://image.tmdb.org/t/p/w500" + currentMovie.getPosterpath();


        Glide.with(mContext)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.poster);


        holder.title.setText(currentMovie.getTitulo());
        String rating1 = currentMovie.getRating() + "";
        holder.rating.setText(rating1);
        //holder.noVisible.setText(currentMovie.getPoster_path());
        //idmovie = currentMovie.getId();
        fav = currentMovie.getFavorito();


        if (fav == 1) {
            holder.favorite.setChecked(true);

        } else {
            holder.favorite.setChecked(false);
        }


    }

    @Override
    public int getItemCount() {
        return movieLocalList.size();
    }

    public void setMoviesLocal(List<MovieLocal> movies) {
        this.movieLocalList = movies;
        //Igualando el arraylist que mandamos desde home al arraylist del filtro de la barra de busqueda
        movieLocalListFull= new ArrayList<>(movieLocalList);
        notifyDataSetChanged();

    }

    public MovieLocal getMovieLocal(int position) {

        return movieLocalList.get(position);

    }


    // Metodos que implemente filterable

    @Override
    public Filter getFilter() {
        return movieFilter;
    }

    private Filter movieFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<MovieLocal> filterList = new ArrayList<>();

            if(constraint==null || constraint.length()==0 ){
                filterList.addAll(movieLocalListFull);

            }else{
                String filterPttern = constraint.toString().toLowerCase().trim();

                for(MovieLocal item : movieLocalListFull){

                    if(item.getTitulo().toLowerCase().contains(filterPttern)){
                        filterList.add(item);

                    }

                }
            }

            FilterResults results = new FilterResults();
            results.values=filterList;

            return  results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            movieLocalList.clear();
            movieLocalList.addAll((List)results.values);
            notifyDataSetChanged();


        }
    };

    /////////////////////////////////////////
}
