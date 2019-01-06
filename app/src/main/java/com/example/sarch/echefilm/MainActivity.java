package com.example.sarch.echefilm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sarch.echefilm.utilities.Movie;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button badButton;
    Button goodButton;
    Button randomButton;
    String popularMoviesURL;
    String topRatedMoviesURL;
    ArrayList<Movie> mPopularList;
    ArrayList<Movie> mTopTopRatedList;
    public final String myApiKey = "94a9226df34dd3b1351e1b8345b96af2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        badButton = (Button) findViewById(R.id.bad_button);
        badButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenBadMovies();
            }
        });

        goodButton = (Button) findViewById(R.id.good_button);
        goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGoodMovies();
            }
        });

        randomButton = (Button) findViewById(R.id.random_button);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenRandomGenerator();

            }
        });
    }

    // starts the other activity
    public void OpenBadMovies() {
        Intent intent= new Intent(this, BadMovies.class);
        startActivity(intent);
    }

    // starts the other activity
    public void OpenGoodMovies() {
        Intent intent= new Intent(this, GoodMovies.class);
        startActivity(intent);
    }

    // starts the other activity
    public void OpenRandomGenerator() {
        Intent intent= new Intent(this, RandomGenerator.class);
        startActivity(intent);
    }

    //AsyncTask
    public class FetchMovies extends AsyncTask<Void,Void,Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            popularMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+myApiKey;
            topRatedMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key="+myApiKey;

            mPopularList = new ArrayList<>();
            mTopTopRatedList = new ArrayList<>();



            return null;
        }
    }
}


