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


   /* public MovieLocal(String titulo, String rating, String posterpath, int favorito) {
        this.titulo = titulo;
        this.rating = rating;
        this.posterpath = posterpath;
        this.favorito=favorito;
    }*/

    public MovieLocal(String titulo, String rating, String posterpath) {
        this.titulo = titulo;
        this.rating = rating;
        this.posterpath = posterpath;
    }

    /*

    public MovieLocal(){

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

    public void setFavorito(int favorito) {
        this.favorito = favorito;
    }
}
