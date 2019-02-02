package com.example.echefilm;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.echefilm.utilities.Api;
import com.example.echefilm.utilities.Genre;
import com.example.echefilm.utilities.Movie;
import com.example.echefilm.utilities.NetworkUtils;
import com.example.echefilm.utilities.SimpleMovie;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.GONE;

// TODO add a surprise me functionality
// TODO favourites list functionality

public class MainActivity extends AppCompatActivity {
    String popularMoviesURL;
    ArrayList<Movie> mPopularList;
    ArrayList<SimpleMovie> simpleMovieList;
    public String myApiKey;
    public Api API;
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";
    ListView listView;
    Dialog myDialog;
    TextView alertTitle;
    TextView alertDescription;
    TextView alertReleaseDate;
    TextView alertVoteAvg;
    ImageView alertImage;
    Button randomButton;
    TextView noGenres;

    ArrayList<Genre> splashGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        API = new Api();
        myApiKey = API.myApiKey;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        myDialog = new Dialog(this);
        new FetchMovies().execute();
        randomButton = findViewById(R.id.random_button);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRandomGen();
            }
        });
        Intent i = getIntent();
        splashGenres = (ArrayList<Genre>) i.getSerializableExtra("genre");
        Log.i(TAG, "STARKE: splash genre size " + splashGenres.size());
        noGenres = findViewById(R.id.no_genres);
    }

    // starts the other activity
    public void openRandomGen() {







        Intent intent= new Intent(MainActivity.this, RandomGenerator.class);
        intent.putExtra("movies", (Serializable) simpleMovieList);
       // Bundle bundle = new Bundle();
//        intent.putExtra("key", mPopularList);
//        bundle.putSerializable("movieList", mPopularList);
//        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // TODO case where no movies fit the genre
        // Inflate the menu; this adds items to the action bar if it is present.
        for (Genre genre: splashGenres) {
            menu.add(menu.NONE, genre.getId(),menu.NONE,genre.getGenreName());
        }
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i(TAG, "STARKE: on options selected");
        String idS = Integer.toString(item.getItemId());
        ArrayList<Movie> temp = new ArrayList<>();
        for (Movie movie : mPopularList) {
            if (movie.getGenre().toString().contains(idS)) {
                temp.add(movie);
            }
        }
        if (temp.isEmpty()) {
            noGenres.setVisibility(View.VISIBLE);
            refreshList(temp);
            Log.i(TAG,"STARKE: no movies in this list");
        } else {
            noGenres.setVisibility(GONE);
            refreshList(temp);
        }
        return super.onOptionsItemSelected(item);
        }


    private void refreshList(ArrayList<Movie> list) {
        MovieAdapter adapter = new MovieAdapter(MainActivity.this, list);
        listView.setAdapter(adapter);
    }

    public void ShowCustomDialog(Movie movie) {
        myDialog.setContentView(R.layout.custom_alert);
        alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
        alertTitle.setText(movie.getTitle());

        alertReleaseDate= (TextView)  myDialog.findViewById(R.id.alert_release_date);
        alertReleaseDate.setText("Release date: " + movie.getReleaseDate());

        alertVoteAvg = (TextView)  myDialog.findViewById(R.id.alert_vote_avg);
        alertVoteAvg.setText("Rating: " + Float.toString(movie.getVoteAverage()));

        alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
        alertDescription.setText(movie.getOverview());

        alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
        Picasso.get().load(MOVIE_BASE_URL + movie.getPosterPath())
                .placeholder(R.drawable.image_placeholder)
                .into(alertImage);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }



    //AsyncTask
    public class FetchMovies extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            popularMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + myApiKey;
            mPopularList = new ArrayList<>();
            simpleMovieList = new ArrayList<>();
            try {
                if(NetworkUtils.networkStatus(MainActivity.this)){
                    mPopularList = NetworkUtils.fetchData(popularMoviesURL);
                    simpleMovieList = NetworkUtils.fetchSimpleData(popularMoviesURL);
                    Log.e(TAG, "Sarchen: Popular MOVIES" + mPopularList.size());
                    Log.e(TAG, "Sarchen: Popular MOVIES simplified" + simpleMovieList.size());
                }else{
                    Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            } catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);
            MovieAdapter adapter = new MovieAdapter(MainActivity.this, mPopularList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Movie item = (Movie) listView.getItemAtPosition(position);
                    ShowCustomDialog(item);
                }
            });

        }
    }
}


