package com.example.agrimitra.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.agrimitra.R;

public class SplashScreen extends AppCompatActivity {
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);
                id = sharedpreferences.getString("id", null);
                Log.d("Shareprefrences", "run: "+id);
                if (id == null) {
                    Intent intent = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent);
                    finish();
                }else{
                    Intent intent = new Intent(SplashScreen.this, Dashboard.class);
                    Toast.makeText(SplashScreen.this, "Welcome back "+sharedpreferences.getString("name",null), Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();                    
                }

            }
        },2500);
    }
}
