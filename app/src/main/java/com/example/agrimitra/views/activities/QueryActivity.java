package com.example.agrimitra.views.activities;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agrimitra.R;
import com.example.agrimitra.views.models.LocationGetterSetter;

public class QueryActivity extends AppCompatActivity implements LocationGetterSetter.LocationChangeListener {

    LocationGetterSetter loc;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        loc = new LocationGetterSetter(getApplicationContext(), QueryActivity.this, this);

        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String l = "Lat: " + location.getLatitude() + ", Long: " + location.getLongitude();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                Uri uri = Uri.parse("mailto:navinsharmasuprb@gmail.com");
                intent.setData(uri);
                intent.putExtra("subject", "my subject");
                intent.putExtra("body", "My location: " + l);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onLocationChange(Location location) {
        this.location = location;
    }
}
