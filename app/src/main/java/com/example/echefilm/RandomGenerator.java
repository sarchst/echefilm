package com.example.echefilm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.echefilm.utilities.Genre;
import com.example.echefilm.utilities.Movie;
import com.example.echefilm.utilities.SimpleMovie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

import static android.view.View.GONE;

public class RandomGenerator extends AppCompatActivity {
    Button chooseButton;
    ImageView clapperboardImageView;
    GifImageView waitingGif;
    TextView waitingText;
    private static final String TAG = RandomGenerator.class.getSimpleName();
    ArrayList<Movie> movieList;
    LinearLayout randomMovieLayout;
    Movie randomMovie;
    TextView randomMovieTitle;
    TextView randomReleaseDate;
    TextView randomVoteAvg;
    TextView randomMovieDesc;
    ImageView randomMovieImage;
    ArrayList<SimpleMovie> simpleMovies;
    private static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_generator);
//        Bundle bundleObject = getIntent().getExtras();
//        if (bundleObject != null) {
//        movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("movieList");
//          // todo fix unchecked cast

        // movie layout initialize
        randomMovieLayout = findViewById(R.id.random_movie_linear_layout);
        randomMovieTitle = findViewById(R.id.random_movie_title);
        randomReleaseDate = findViewById(R.id.random_movie_release_date);
        randomVoteAvg = findViewById(R.id.random_movie_vote_avg);
        randomMovieDesc = findViewById(R.id.random_movie_description);
        randomMovieImage = findViewById(R.id.random_movie_image);

        chooseButton = findViewById(R.id.choose_movie_button);
        clapperboardImageView = findViewById(R.id.clapperboard);
        waitingGif = findViewById(R.id.frances_gif);
        waitingText = findViewById(R.id.waiting_textview);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseButton.setVisibility(GONE);
                waitingGif.setVisibility(View.VISIBLE);
                waitingText.setVisibility(View.VISIBLE);
                clapperboardImageView.setVisibility(GONE);
                randomMovieLayout.setVisibility(View.GONE);
                chooseMovie();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.v(TAG,"Sarchen: theChosenMovie will be displayed NOW");
                        displayMovie();
                    }
                }, 3000);
            }
        });
        Intent i = getIntent();
        simpleMovies = (ArrayList<SimpleMovie>) i.getSerializableExtra("movies");
        Log.v(TAG,"Sarchen: size of simplelist " + simpleMovies.size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    public void displayMovie() {
        waitingGif.setVisibility(GONE);
        waitingText.setVisibility(GONE);
        chooseButton.setText("Redo");
        chooseButton.setVisibility(View.VISIBLE);
        randomMovieLayout.setVisibility(View.VISIBLE);

        randomMovieTitle.setText(randomMovie.getTitle());
        randomMovieDesc.setText(randomMovie.getOverview());
        randomMovie.setReleaseDate(randomMovie.getReleaseDate());




        Picasso.get().load(MOVIE_BASE_URL + randomMovie.getPosterPath())
                .placeholder(R.drawable.image_placeholder)
                .into(randomMovieImage);

    }





    public void chooseMovie() {
        SimpleMovie random = simpleMovies.get(new Random().nextInt(simpleMovies.size()));
        randomMovie = new Movie();
        randomMovie.setTitle(random.getTitle());
        randomMovie.setOverview(random.getOverview()); // todo display this theChosenMovie dummy
        randomMovie.setPosterPath(random.getOverview());
        randomMovie.setReleaseDate(random.getReleaseDate());
        randomMovie.setVoteAverage(random.getVoteAverage());
    }
}
