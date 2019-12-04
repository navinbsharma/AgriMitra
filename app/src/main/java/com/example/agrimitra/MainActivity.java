package com.example.agrimitra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton query = (ImageButton) findViewById(R.id.query);
        ImageButton weather = (ImageButton) findViewById(R.id.weather);
        ImageButton feedback = (ImageButton) findViewById(R.id.feedback);
        ImageButton soil = (ImageButton) findViewById(R.id.soil);
        ImageButton market = (ImageButton) findViewById(R.id.market);

        query.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this , QueryActivity.class);
                startActivity(intent);
            }
        });

        feedback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this , FeedbackActivity.class);
                startActivity(intent);
            }
        });

        soil.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this , SoilHealthActivity.class);
                startActivity(intent);
            }
        });

        weather.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this , WeatherActivity.class);
                startActivity(intent);
            }
        });

        market.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this , MarketActivity.class);
                startActivity(intent);
            }
        });

    }

}
