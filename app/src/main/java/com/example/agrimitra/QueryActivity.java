package com.example.agrimitra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                Uri uri = Uri.parse("mailto:navinsharmasuprb@gmail.com");
                intent.setData(uri);
                intent.putExtra("subject", "my subject");
                intent.putExtra("body", "my location");
                startActivity(intent);
            }
        });


    }
}
