package com.example.echefilm;

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

import com.example.echefilm.utilities.Movie;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifImageView;

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
                chooseButton.setVisibility(View.GONE);
                waitingGif.setVisibility(View.VISIBLE);
                waitingText.setVisibility(View.VISIBLE);
                clapperboardImageView.setVisibility(View.GONE);
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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    public void displayMovie() {
        waitingGif.setVisibility(View.GONE);
        waitingText.setVisibility(View.GONE);
        chooseButton.setText("Redo");
        chooseButton.setVisibility(View.VISIBLE);
        randomMovieLayout.setVisibility(View.VISIBLE);

        randomMovieTitle.setText(randomMovie.getTitle());
        randomMovieDesc.setText(randomMovie.getOverview());

    }





    public void chooseMovie() {
        randomMovie = new Movie();
        randomMovie.setTitle("An Amazing Movie");
        randomMovie.setOverview("This theChosenMovie is amazing."); // todo display this theChosenMovie dummy
        randomMovie.setPosterPath(null);



//        Random randomizer = new Random();
//        Movie random = movieList.get(new Random().nextInt(movieList.size()));
    }
}
