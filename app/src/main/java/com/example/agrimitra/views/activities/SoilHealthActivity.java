package com.example.agrimitra.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.agrimitra.R;
import com.example.agrimitra.databinding.ActivitySoilHealthBinding;

public class SoilHealthActivity extends AppCompatActivity {
    ActivitySoilHealthBinding soilHealthBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soilHealthBinding = DataBindingUtil.setContentView(this,R.layout.activity_soil_health);
        //setContentView(R.layout.activity_soil_health);
        soilHealthBinding.request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }
        });



    }
}
