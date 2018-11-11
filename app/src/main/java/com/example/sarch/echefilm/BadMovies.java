package com.example.sarch.echefilm;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BadMovies extends AppCompatActivity {
    private ImageView room;
    private ImageView troll;
    private ImageView bird;
    private ImageView santa;
    private ImageView plan;
    Dialog myDialog;
    TextView alertTitle;
    TextView alertDescription;
    ImageView alertImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_movies);
        myDialog = new Dialog(this);

        // ImageView buttons
        room = (ImageView) findViewById(R.id.room_picture);
        troll = (ImageView) findViewById(R.id.troll_picture);
        bird = (ImageView) findViewById(R.id.birdemic);
        santa = (ImageView) findViewById(R.id.santa_picture);
        plan = (ImageView) findViewById(R.id.plan_picture);

        room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog("room");
            }
        });

        troll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog("troll");
            }
        });

        bird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog("bird");
            }
        });

        santa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog("santa");
            }
        });

        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowCustomDialog("plan");
            }
        });
    }

    public void ShowCustomDialog(String movie) {
        myDialog.setContentView(R.layout.custom_alert);
        switch (movie) {
            case "room":
                alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
                alertTitle.setText("The Room");

                alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
                alertDescription.setText(R.string.room_synopsis);

                alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
                alertImage.setImageResource(R.drawable.room);
                break;

            case "troll":
                alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
                alertTitle.setText("Troll 2");

                alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
                alertDescription.setText(R.string.troll_synopsis);

                alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
                alertImage.setImageResource(R.drawable.troll);
                break;

            case "bird":
                alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
                alertTitle.setText("Birdemic: Shock and Terror");

                alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
                alertDescription.setText(R.string.birdemic);

                alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
                alertImage.setImageResource(R.drawable.birdemic);
                break;

            case "santa":
                alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
                alertTitle.setText("Santa Claus Conquers the Martians");

                alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
                alertDescription.setText(R.string.pulp_synopsis);

                alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
                alertImage.setImageResource(R.drawable.santa);
                break;

            case "plan":
                alertTitle = (TextView)  myDialog.findViewById(R.id.alert_title);
                alertTitle.setText("Plan 9 from Outer Space");

                alertDescription = (TextView) myDialog.findViewById(R.id.alert_description);
                alertDescription.setText(R.string.pulp_synopsis);

                alertImage = (ImageView) myDialog.findViewById(R.id.alert_image);
                alertImage.setImageResource(R.drawable.plan);
                break;

            default: break;

        }





        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

}