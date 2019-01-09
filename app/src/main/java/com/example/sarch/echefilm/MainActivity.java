package com.example.sarch.echefilm;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarch.echefilm.utilities.Movie;
import com.example.sarch.echefilm.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    String popularMoviesURL;
    ArrayList<Movie> mPopularList;
    ArrayList<Movie> actionMovies;
    ArrayList<Movie> comedyMovies;
    ArrayList<Movie> animatedMovies;
    ArrayList<Movie> dramaMovies;


    public final String myApiKey = "94a9226df34dd3b1351e1b8345b96af2";
    private static final String TAG = MainActivity.class.getSimpleName();


    public final String action = "28";
    public final String animated = "16";
    public final String comedy = "35";
    public final String drama = "18";
    ListView listView;

    ArrayList<Movie> testMovieList;
    Movie m1;
    Movie m2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m1 = new Movie();
        m2 = new Movie();
        m1.setTitle("Dreams");
        m2.setTitle("Friends");
        m1.setReleaseDate("2020");
        m1.setReleaseDate("2025");
        testMovieList = new ArrayList<>();
        testMovieList.add(m1);
        testMovieList.add(m2);

        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        MovieAdapter adapter = new MovieAdapter(MainActivity.this, testMovieList);
        listView.setAdapter(adapter);






        new FetchMovies().execute(); //New code
        Log.e(TAG, "Sarchen: End of OnCreate");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.pop_movies) {
            refreshList(mPopularList);
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshList(ArrayList<Movie> list) {


    }

    //AsyncTask
    public class FetchMovies extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            popularMoviesURL = "http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key="+myApiKey;

            mPopularList = new ArrayList<>();
            actionMovies = new ArrayList<>();
            comedyMovies = new ArrayList<>();
            animatedMovies = new ArrayList<>();
            dramaMovies = new ArrayList<>();

            try {
                if(NetworkUtils.networkStatus(MainActivity.this)){
                    mPopularList = NetworkUtils.fetchData(popularMoviesURL); //Get popular movies
                    Log.e(TAG, "Sarchen: Popular" + mPopularList.size());
                }else{
                    Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
                }
            } catch (IOException e){
                e.printStackTrace();
            }


            // filter movies by genre
            for (int m=0; m < mPopularList.size(); m++) {
                Log.e(TAG, "Sarchen: genre " + mPopularList.get(m).getGenre());
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
        protected void onPostExecute(Void  s) {
            super.onPostExecute(s);

        }
    }
}


