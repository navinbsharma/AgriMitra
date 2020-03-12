package com.example.agrimitra.views.activities.market;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.agrimitra.R;

public class Market_list extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_list);
        RecyclerView market_recyclerview = (RecyclerView) findViewById(R.id.market_recyclerview);
    }
}
