package com.example.sarch.echefilm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button badButton;
    Button goodButton;
    Button randomButton;

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
}


