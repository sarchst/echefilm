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
    public Api API;;
    String genresURL;
    ArrayList<Genre> genresList;
    private static final String TAG = SplashActivity.class.getSimpleName();

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
            genresURL = "https://api.themoviedb.org/3/genre/movie/list?api_key=" + myApiKey;
            genresList = new ArrayList<>();
            try {
                if (NetworkUtils.networkStatus(SplashActivity.this)) {
                    genresList = NetworkUtils.fetchGenreData(genresURL);
                    Log.i(TAG,"STARKE: in SplashActivity genre " + genresList.size());
                } else {
                    Toast.makeText(SplashActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            i.putExtra("genre", (Serializable) genresList);
            startActivity(i);
            finish();

        }
    }

}
