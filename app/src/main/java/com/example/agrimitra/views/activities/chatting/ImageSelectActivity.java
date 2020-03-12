package com.example.agrimitra.views.activities.chatting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agrimitra.R;
import com.example.agrimitra.databinding.ActivityImageSelectBinding;

import java.io.File;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImageSelectActivity extends AppCompatActivity {
    ActivityImageSelectBinding imageSelectBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_image_select);
        imageSelectBinding = DataBindingUtil.setContentView(this,R.layout.activity_image_select);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cryptic-shelf-53020.herokuapp.com")
                //.baseUrl("http://192.168.43.62:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        Bundle bundle = getIntent().getExtras();
        Log.d("ImageSelectActivity", "onCreate: "+bundle.getString("IMAGE_NAME"));
        if(bundle.get("IMAGE_NAME") != null){
            File fileName = (File) bundle.get("IMAGE_NAME");
            imageSelectBinding.imageQueryView.setImageURI(Uri.fromFile(fileName));
        }
        else{
            Intent i = new Intent(ImageSelectActivity.this,ChatOptionActivity.class);
            Toast.makeText(this, "problem", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }

        imageSelectBinding.sendImageQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = imageSelectBinding.messageImageQuery.getText().toString();
                if(msg.trim() != null){



                }
            }
        });



    }
}
