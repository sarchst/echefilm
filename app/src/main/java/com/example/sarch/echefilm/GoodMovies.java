package com.example.sarch.echefilm;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodMovies extends AppCompatActivity {
    private ImageView moonlight;
    private ImageView frances;
    private ImageView sunset;
    private ImageView pulp;
    Dialog myDialog;
    TextView alertTitle;
    TextView alertDescription;
    ImageView alertImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_movies);

        myDialog = new Dialog(this);

        // ImageView buttons
        moonlight = (ImageView) findViewById(R.id.moonlight_picture);
        frances = (ImageView) findViewById(R.id.frances_picture);
        sunset = (ImageView) findViewById(R.id.before_sunset);
        pulp = (ImageView) findViewById(R.id.pulp_fiction);

        moonlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog("moonlight");
            }
        });

        frances.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog("frances");
            }
        });

        sunset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog("sunset");
            }
        });

        pulp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog("pulp");
            }
        });

    }

    public void ShowCustomDialog(String movie) {
        myDialog.setContentView(R.layout.custom_alert);
        switch (movie) {
            case "moonlight":
                alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
                alertTitle.setText("Moonlight");

                alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
                alertDescription.setText(R.string.moonlight_synopsis);

                alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
                alertImage.setImageResource(R.drawable.moonlight);
                break;

            case "frances":
                alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
                alertTitle.setText("Frances Ha");

                alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
                alertDescription.setText(R.string.frances_synopsis);

                alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
                alertImage.setImageResource(R.drawable.frances_ha);
                break;

            case "sunset":
                alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
                alertTitle.setText("Before Sunset");

                alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
                alertDescription.setText(R.string.sunset_synopsis);

                alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
                alertImage.setImageResource(R.drawable.before_sunset);
                break;

            case "pulp":
                alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
                alertTitle.setText("Pulp Fiction");

                alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
                alertDescription.setText(R.string.pulp_synopsis);

                alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
                alertImage.setImageResource(R.drawable.pulp_fiction);
                break;

            default: break;

        }





        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}

