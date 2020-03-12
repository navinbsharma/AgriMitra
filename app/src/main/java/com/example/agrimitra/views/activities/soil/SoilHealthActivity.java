package com.example.agrimitra.views.activities.soil;

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
                Intent i = new Intent(getApplicationContext(),RequestNewCard.class);
                startActivity(i);
            }


        });
        soilHealthBinding.upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getApplicationContext(),Upload_soil_card.class);
                startActivity(a);
            }
        });

        soilHealthBinding.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(getApplicationContext(),ViewSoilCard.class);
                startActivity(k);
            }
        });


    }
}
