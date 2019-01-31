package com.example.echefilm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.echefilm.utilities.Api;
import com.example.echefilm.utilities.Genre;
import com.example.echefilm.utilities.Movie;
import com.example.echefilm.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    public String myApiKey;
    public Api API;
    String popularMoviesURL;
    String genresURL;

    ArrayList<Movie> mPopularList;
    ArrayList<Movie> actionMovies;
    ArrayList<Movie> comedyMovies;
    ArrayList<Movie> animatedMovies;
    ArrayList<Movie> dramaMovies;
    ArrayList<Genre> genresList;

    public final String action = "28";
    public final String animated = "16";
    public final String comedy = "35";
    public final String drama = "18";

    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG,"STARKE: in SplashActivity");
        setContentView(R.layout.activity_splash);
        API = new Api();
        myApiKey = API.myApiKey;
        new FetchMovies().execute();
    }

    //AsyncTask
    public class FetchMovies extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            popularMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=" + myApiKey;
            genresURL = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + myApiKey;

            mPopularList = new ArrayList<>();
            actionMovies = new ArrayList<>();
            comedyMovies = new ArrayList<>();
            animatedMovies = new ArrayList<>();
            dramaMovies = new ArrayList<>();
            genresList = new ArrayList<>();

            try {
                if (NetworkUtils.networkStatus(SplashActivity.this)) {
                    mPopularList = NetworkUtils.fetchData(popularMoviesURL); //Get popular movies
                    genresList = NetworkUtils.fetchGenreData(genresURL);
                    Log.i(TAG,"STARKE: in SplashActivity movies " + mPopularList.size());
                    Log.i(TAG,"STARKE: in SplashActivity genre " + genresList.size());
                } else {
                    Toast.makeText(SplashActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            // filter movies by genre
            for (int m = 0; m < mPopularList.size(); m++) {
                //Log.e(TAG, "Sarchen: genre " + mPopularList.get(m).getGenre());
                for (int i = 0; i < mPopularList.get(m).getGenre().length(); i++) {
                    try {
                        if (mPopularList.get(m).getGenre().getString(i).equals(action)) {
                            actionMovies.add(mPopularList.get(m));
                        }
                        if (mPopularList.get(m).getGenre().getString(i).equals(drama)) {
                            dramaMovies.add(mPopularList.get(m));
                        }
                        if (mPopularList.get(m).getGenre().getString(i).equals(animated)) {
                            animatedMovies.add(mPopularList.get(m));
                        }
                        if (mPopularList.get(m).getGenre().getString(i).equals(comedy)) {
                            comedyMovies.add(mPopularList.get(m));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
//            MovieAdapter adapter = new MovieAdapter(MainActivity.this, mPopularList);
//             listView.setAdapter(adapter);
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//                    Movie item = (Movie) listView.getItemAtPosition(position);
//                    ShowCustomDialog(item);
//                }
//            });

            i.putExtra("genre", (Serializable) genresList);
            startActivity(i);
            finish();

        }
    }

}
