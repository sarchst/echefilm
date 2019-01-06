package com.example.sarch.echefilm;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.HashSet;

public class GoodMovies extends AppCompatActivity {
    private ImageView moonlightButton;
    private ImageView francesButton;
    private ImageView sunsetButton;
    private ImageView pulpButton;
    Dialog myDialog;
    TextView alertTitle;
    TextView alertDescription;
    ImageView alertImage;
    HashSet<Movie2> goodMovies;
    Movie2 moonlight;
    Movie2 francesHa;
    Movie2 beforeSunset;
    Movie2 pulpFiction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_movies);

        // Initialize the movies
        moonlight = new Movie2("Moonlight",
                "'...it’s about teaching a child to swim, about cooking a meal for an " +
                        "old friend, about the feeling of sand on skin and the sound of waves on a " +
                        "darkened beach, about first kisses and lingering regrets.' - A. O. Scott (NYT)",
                R.drawable.moonlight);

        francesHa = new Movie2("Frances Ha",
                "A story that follows a New York woman, who doesn\\'t really have an apartment. " +
                        "She apprentices for a dance company although she\\'s not really a dancer, and throws" +
                        " herself headlong into her dreams.",
                R.drawable.frances_ha);

        beforeSunset = new Movie2("Before Sunset",
                "\\'Erasing nearly a decade of longing and distraction, Jesse and Celine, " +
                        "a bit awkwardly, pick up where they left off...they have no interest in us, which is " +
                        "why we, eavesdropping and spying on their clumsy, passionate intimacy, are so interested in them.\\'  " +
                        "- A. O. Scott (NYT)",
                R.drawable.before_sunset);

        pulpFiction = new Movie2("Pulp Fiction",
                "Hitmen with a penchant for philosophical discussions.",
                R.drawable.pulp_fiction);



        myDialog = new Dialog(this);

        // movie picture buttons
        moonlightButton = (ImageView) findViewById(R.id.moonlight_imageview);
        francesButton = (ImageView) findViewById(R.id.frances_imageview);
        sunsetButton = (ImageView) findViewById(R.id.sunset_imageview);
        pulpButton = (ImageView) findViewById(R.id.pulp_imageview);

        moonlightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog(moonlight);
            }
        });

        francesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog(francesHa);
            }
        });

        sunsetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog(beforeSunset);
            }
        });

        pulpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog(pulpFiction);
            }
        });

    }

    public void ShowCustomDialog(Movie2 movie) {
        Log.d("GoodMovie","In SHOW CUSTOM DIALOG");

        myDialog.setContentView(R.layout.custom_alert);
        alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
        alertTitle.setText(movie.title);

        alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
        alertDescription.setText(movie.description);

        alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
        alertImage.setImageResource(movie.imageResource);

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}

