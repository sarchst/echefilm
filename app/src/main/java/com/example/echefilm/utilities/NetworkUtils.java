package com.example.echefilm.utilities;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();


    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static ArrayList<Movie> fetchData(String url) throws IOException {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        try {

            URL new_url = new URL(url); //create a url from a String
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection(); //Opening a http connection  to the remote object
            connection.connect();

            InputStream inputStream = connection.getInputStream(); //reading from the object
            String results = IOUtils.toString(inputStream);  //IOUtils to convert inputstream objects into Strings type
            parseJson(results,movies);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public static void parseJson(String data, ArrayList<Movie> list){


        try {
            JSONObject mainObject = new JSONObject(data);
            Log.v(TAG,mainObject.toString());
            JSONArray resArray = mainObject.getJSONArray("results"); //Getting the results object
            for (int i = 0; i < resArray.length(); i++) {
                JSONObject jsonObject = resArray.getJSONObject(i);
                Movie movie = new Movie(); //New Movie object
                movie.setId(jsonObject.getInt("id"));
                movie.setGenre(jsonObject.getJSONArray("genre_ids"));
                movie.setVoteAverage(jsonObject.getInt("vote_average"));
                movie.setVoteCount(jsonObject.getInt("vote_count"));
                movie.setOriginalTitle(jsonObject.getString("original_title"));
                movie.setTitle(jsonObject.getString("title"));
                movie.setPopularity(jsonObject.getDouble("popularity"));
                movie.setBackdropPath(jsonObject.getString("backdrop_path"));
                movie.setOverview(jsonObject.getString("overview"));
                movie.setReleaseDate(jsonObject.getString("release_date"));
                movie.setPosterPath(jsonObject.getString("poster_path"));
                list.add(movie);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Error occurred during JSON Parsing");
        }

    }
    // Genre

    public static ArrayList<Genre> fetchGenreData(String url) throws IOException {
        ArrayList<Genre> genres = new ArrayList<Genre>();
        try {

            URL new_url = new URL(url); //create a url from a String
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection(); //Opening a http connection  to the remote object
            connection.connect();

            InputStream inputStream = connection.getInputStream(); //reading from the object
            String results = IOUtils.toString(inputStream);  //IOUtils to convert inputstream objects into Strings type
            parseGenreJson(results,genres);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.v(TAG,"Sarchen: checking genre list " + genres);

        return genres;
    }

    public static void parseGenreJson(String data, ArrayList<Genre> genreList){
        try {
            JSONObject mainObject = new JSONObject(data);
            Log.v(TAG,mainObject.toString());
            JSONArray resArray = mainObject.getJSONArray("genres"); //Getting the results object
            Log.v(TAG,"Sarchen: resArray " + resArray);
            for (int i = 0; i < resArray.length(); i++) {
                JSONObject jsonObject = resArray.getJSONObject(i);
                Genre genre = new Genre(); //New Movie object
                genre.setId(jsonObject.getInt("id"));
                genre.setGenreName(jsonObject.getString("name"));
                genreList.add(genre);
                Log.v(TAG,"Sarchen: adding Genre " + genre.getId());
                Log.v(TAG,"Sarchen: adding Genre " + genre.getGenreName());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Error occurred during JSON Parsing");
        }

    }

    public static ArrayList<SimpleMovie> fetchSimpleData(String url) throws IOException {
        ArrayList<SimpleMovie> movies = new ArrayList<SimpleMovie>();
        try {

            URL new_url = new URL(url); //create a url from a String
            HttpURLConnection connection = (HttpURLConnection) new_url.openConnection(); //Opening a http connection  to the remote object
            connection.connect();

            InputStream inputStream = connection.getInputStream(); //reading from the object
            String results = IOUtils.toString(inputStream);  //IOUtils to convert inputstream objects into Strings type
            parseJsonSimple(results,movies);
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return movies;
    }

    public static void parseJsonSimple(String data, ArrayList<SimpleMovie> list){


        try {
            JSONObject mainObject = new JSONObject(data);
            Log.v(TAG,mainObject.toString());
            JSONArray resArray = mainObject.getJSONArray("results"); //Getting the results object
            for (int i = 0; i < resArray.length(); i++) {
                JSONObject jsonObject = resArray.getJSONObject(i);
                SimpleMovie movie = new SimpleMovie(); //New Movie object
                movie.setVoteAverage(jsonObject.getInt("vote_average"));
                movie.setTitle(jsonObject.getString("title"));
                movie.setOverview(jsonObject.getString("overview"));
                movie.setReleaseDate(jsonObject.getString("release_date"));
                movie.setPosterPath(jsonObject.getString("poster_path"));
                list.add(movie);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "Error occurred during JSON Parsing");
        }

    }

    public static Boolean networkStatus(Context context){
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()){
            Log.e(TAG, "Sarchen: Connected!!");
            return true;
        }
        Log.e(TAG, "NOT connected!!");
        return false;
    }

}
