package com.example.agrimitra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ImageView query = findViewById(R.id.query);
        ImageView weather =  findViewById(R.id.weather);
        ImageView feedback =  findViewById(R.id.feedback);
        ImageView soil =  findViewById(R.id.soilcard);
        ImageView market =  findViewById(R.id.market);

        query.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Dashboard.this , QueryActivity.class);
                startActivity(intent);
            }
        });

        feedback.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Dashboard.this , FeedbackActivity.class);
                startActivity(intent);
            }
        });

        soil.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Dashboard.this , SoilHealthActivity.class);
                startActivity(intent);
            }
        });

        weather.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Dashboard.this , WeatherActivity.class);
                startActivity(intent);
            }
        });

        market.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Dashboard.this , MarketActivity.class);
                startActivity(intent);
            }
        });
    }
}
