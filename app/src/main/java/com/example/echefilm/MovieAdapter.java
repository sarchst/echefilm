package com.example.echefilm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.echefilm.utilities.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<Movie> list;
    private static final String MOVIE_BASE_URL="https://image.tmdb.org/t/p/w185";
    private static final String TAG = MovieAdapter.class.getSimpleName();

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.mContext = context;
        this.list = movieList;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Movie getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.single_row, parent, false);
        TextView title = row.findViewById(R.id.title);
        TextView synopsis = row.findViewById(R.id.synopsis);
        ImageView imageView = row.findViewById(R.id.image_view);
        Movie temp = getItem(position);
        title.setText(temp.getTitle());
        synopsis.setText(temp.getOverview());
        Picasso.get().load(MOVIE_BASE_URL + temp.getPosterPath())
                .placeholder(R.drawable.image_placeholder)
                .into(imageView);

        return row;

    }
}
