package com.example.echefilm;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.echefilm.utilities.Movie;

import java.util.ArrayList;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class RandomGenerator extends AppCompatActivity {
    Button chooseButton;
    ImageView clapperboardImageView;
    GifImageView waitingGif;
    TextView waitingText;
    private static final String TAG = RandomGenerator.class.getSimpleName();
    ArrayList<Movie> movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_generator);
//        Bundle bundleObject = getIntent().getExtras();
//        if (bundleObject != null) {
//        movieList = (ArrayList<Movie>) getIntent().getSerializableExtra("movieList");
//          // todo fix unchecked cast

        chooseButton = findViewById(R.id.choose_movie_button);
        clapperboardImageView = findViewById(R.id.clapperboard);
        waitingGif = findViewById(R.id.frances_gif);
        waitingText = findViewById(R.id.waiting_textview);
        chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseButton.setVisibility(View.INVISIBLE);
                waitingGif.setVisibility(View.VISIBLE);
                waitingText.setVisibility(View.VISIBLE);
                clapperboardImageView.setVisibility(View.INVISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.v(TAG,"Sarchen: movie will be displayed NOW");
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
        waitingGif.setVisibility(View.INVISIBLE);
        waitingText.setVisibility(View.INVISIBLE);
        chooseButton.setText("Redo");
        chooseButton.setVisibility(View.VISIBLE);
    }





    public void chooseMovie() {
        Movie movie = new Movie();
        movie.setTitle("An Amazing Movie");
        movie.setOverview("This movie is amazing.");
//        Random randomizer = new Random();
//        Movie random = movieList.get(new Random().nextInt(movieList.size()));
    }
}
