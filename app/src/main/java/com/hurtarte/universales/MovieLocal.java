package com.hurtarte.universales;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movielocal_table")
public class MovieLocal {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String titulo;
    private String rating;
    private String posterpath;
    private int favorito;
    private int idMovie;
    private String overview;


   /* public MovieLocal(String titulo, String rating, String posterpath, int favorito) {
        this.titulo = titulo;
        this.rating = rating;
        this.posterpath = posterpath;
        this.favorito=favorito;
    }*/

    public MovieLocal(int idMovie,String titulo, String rating, String posterpath, int favorito, String overview) {
        this.titulo = titulo;
        this.rating = rating;
        this.posterpath = posterpath;
        this.favorito=favorito;
        this.idMovie=idMovie;
        this.overview=overview;
    }

    /*

    public MovieLocal(){

    }*/

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getOverview() {
        return overview;
    }

  /*  public void setOverview(String overview) {
        this.overview = overview;
    }*/

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getRating() {
        return rating;
    }

    public String getPosterpath() {
        return posterpath;
    }


    public int getFavorito() {
        return favorito;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPosterpath(String posterpath) {
        this.posterpath = posterpath;
    }

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }
}
