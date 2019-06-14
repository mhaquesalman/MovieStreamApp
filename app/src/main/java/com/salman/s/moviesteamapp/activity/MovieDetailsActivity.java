package com.salman.s.moviesteamapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetailsActivity extends AppCompatActivity {
    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title,tv_description;
    private FloatingActionButton play_fab;
    RecyclerView rv_cast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.salman.s.moviesteamapp.R.layout.movie_details_activity);
        iniViews();


    }

    void iniViews() {
        play_fab = findViewById(com.salman.s.moviesteamapp.R.id.play_fab);
        String movieTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imagecover = getIntent().getExtras().getInt("imgCover");

        MovieThumbnailImg = findViewById(com.salman.s.moviesteamapp.R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        MovieThumbnailImg.setImageResource(imageResourceId);

        MovieCoverImg = findViewById(com.salman.s.moviesteamapp.R.id.detail_movie_cover);
        Glide.with(this).load(imagecover).into(MovieCoverImg);

        tv_title = findViewById(com.salman.s.moviesteamapp.R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        getSupportActionBar().setTitle(movieTitle);
        tv_description = findViewById(com.salman.s.moviesteamapp.R.id.detail_movie_desc);

        // setup animation
        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, com.salman.s.moviesteamapp.R.anim.scale_animation));
        play_fab.setAnimation(AnimationUtils.loadAnimation(this, com.salman.s.moviesteamapp.R.anim.scale_animation));


    }
}
