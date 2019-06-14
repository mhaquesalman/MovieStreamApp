package com.salman.s.moviesteamapp.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.salman.s.moviesteamapp.Movie;
import com.salman.s.moviesteamapp.MovieAdapter;
import com.salman.s.moviesteamapp.MovieItemClickListener;
import com.salman.s.moviesteamapp.Slide;
import com.salman.s.moviesteamapp.SliderPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements MovieItemClickListener {
    private List<Slide> listSlides ;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.salman.s.moviesteamapp.R.layout.activity_main);

        sliderpager = findViewById(com.salman.s.moviesteamapp.R.id.slider_pager) ;
        indicator = findViewById(com.salman.s.moviesteamapp.R.id.indicator);
        recyclerView = findViewById(com.salman.s.moviesteamapp.R.id.Rv_movies);

        // prepare a list of slides ..
        listSlides = new ArrayList<>() ;

        listSlides.add(new Slide(com.salman.s.moviesteamapp.R.drawable.slide1, "Title \nmore text here"));
        listSlides.add(new Slide(com.salman.s.moviesteamapp.R.drawable.slide2,"Slide Title \nmore text here"));
        listSlides.add(new Slide(com.salman.s.moviesteamapp.R.drawable.slide1,"Slide Title \nmore text here"));
        listSlides.add(new Slide(com.salman.s.moviesteamapp.R.drawable.slide2,"Slide Title \nmore text here"));

        SliderPagerAdapter sliderPagerAdapter = new SliderPagerAdapter(this,listSlides);
        sliderpager.setAdapter(sliderPagerAdapter);

        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);

        List<Movie> listMovies = new ArrayList<>();
        listMovies.add(new Movie("Moana", com.salman.s.moviesteamapp.R.drawable.moana, com.salman.s.moviesteamapp.R.drawable.spidercover));
        listMovies.add(new Movie("Black P", com.salman.s.moviesteamapp.R.drawable.blackp, com.salman.s.moviesteamapp.R.drawable.spidercover));
        listMovies.add(new Movie("The Martian", com.salman.s.moviesteamapp.R.drawable.themartian));
        listMovies.add(new Movie("The Martian", com.salman.s.moviesteamapp.R.drawable.themartian));
        listMovies.add(new Movie("The Martian", com.salman.s.moviesteamapp.R.drawable.themartian));
        listMovies.add(new Movie("The Martian", com.salman.s.moviesteamapp.R.drawable.themartian));

        MovieAdapter movieAdapter = new MovieAdapter(this, listMovies, this);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

     }

    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL",movie.getThumbnail());
        intent.putExtra("imgCover",movie.getCoverPhoto());
        startActivity(intent);

        // lets crezte the animation
       // ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,movieImageView,"sharedName");
        Toast.makeText(this,"item clicked : " + movie.getTitle(),Toast.LENGTH_LONG).show();
        Log.d("MainActivity","item clicked");


    }

    class SliderTimer extends TimerTask {
        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem()<listSlides.size()-1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                    }
                    else
                        sliderpager.setCurrentItem(0);
                }
            });

        }
    }
}
