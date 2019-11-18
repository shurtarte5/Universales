package com.hurtarte.universales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import static com.hurtarte.universales.HomeFragment.EXTRA_ID;
import static com.hurtarte.universales.HomeFragment.EXTRA_OVERVIEW;
import static com.hurtarte.universales.HomeFragment.EXTRA_POSTER;
import static com.hurtarte.universales.HomeFragment.EXTRA_TITLE;

public class MovieDetail extends AppCompatActivity {

    private TextView title;
    private ImageView poster;
    private TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        title = findViewById(R.id.textView_title);
        overview=findViewById(R.id.textView_synop);
        poster = findViewById(R.id.imageview_detail);

        Intent intent = getIntent();





        String url = "https://image.tmdb.org/t/p/w500" + intent.getStringExtra(EXTRA_POSTER);


        Glide.with(getApplicationContext())
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .into(poster);


            title.setText(intent.getStringExtra(EXTRA_TITLE));

            overview.setText(intent.getStringExtra(EXTRA_OVERVIEW));







    }
}
