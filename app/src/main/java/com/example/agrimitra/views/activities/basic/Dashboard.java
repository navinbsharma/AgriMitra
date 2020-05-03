package com.example.agrimitra.views.activities.basic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agrimitra.R;
import com.example.agrimitra.views.activities.chatting.ChatOptionActivity;
import com.example.agrimitra.views.activities.soil.SoilHealthActivity;
import com.example.agrimitra.views.activities.tutorial.Tutorials;
import com.example.agrimitra.views.activities.chatting.ChatActivity;
import com.example.agrimitra.views.activities.feedback.FeedbackActivity;
import com.example.agrimitra.views.activities.market.MarketActivity;
import com.example.agrimitra.views.activities.weather.WeatherForecastActivity;
import com.example.agrimitra.views.activities.weather.WeatherShowActivity;

import static com.example.agrimitra.views.activities.basic.Login.MyPREFERENCES;

public class Dashboard extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    public static String APP_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Remove title bar
        sharedPreferences = getSharedPreferences(MyPREFERENCES,Context.MODE_PRIVATE);


        ImageView query = findViewById(R.id.query);
        ImageView weather = findViewById(R.id.weather);
        ImageView feedback = findViewById(R.id.feedback);
        ImageView soil = findViewById(R.id.soilcard);
        ImageView market = findViewById(R.id.market);
        ImageView tutorial = findViewById(R.id.tutorial);
        TextView logoutView = findViewById(R.id.logoutGrid);
        TextView username = findViewById(R.id.userNameGrid);
        username.setText(sharedPreferences.getString("name",null));

        APP_USERNAME = username.getText().toString();

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, ChatActivity.class);
                startActivity(intent);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });

        soil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, SoilHealthActivity.class);
                startActivity(intent);
            }
        });

        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, WeatherForecastActivity.class);
                startActivity(intent);
            }
        });

        market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, MarketActivity.class);
                startActivity(intent);
            }
        });
        tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Dashboard.this, Tutorials.class);
                startActivity(intent);
            }
        });
        logoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogBox();
            }
        });

    }

    private void openDialogBox() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        SharedPreferences sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                        SharedPreferences.Editor shEdit = sharedPreferences.edit();

                        shEdit.clear();
                        shEdit.apply();
                        Intent i = new Intent(Dashboard.this,Login.class);
                        startActivity(i);
                        finish();

                    }
                });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
