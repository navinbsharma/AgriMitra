package com.example.agrimitra.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.agrimitra.R;

public class Login extends AppCompatActivity {
    Button b;
    Intent i;
    TextView txtSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         b = findViewById(R.id.btn_login);
        txtSignUp = findViewById(R.id.txtSignUp);
        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(Login.this,Signup.class);
                startActivity(i);
            }
        });
         b.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                i = new Intent(Login.this,Dashboard.class);
                startActivity(i);
             }
         });
    }
}
