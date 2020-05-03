package com.example.agrimitra.views.activities.chatting;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;


import com.example.agrimitra.R;
import com.example.agrimitra.databinding.ActivityChatOptionBinding;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatOptionActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 1;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    ActivityChatOptionBinding mainBinding;
    Float transactionY = 100f;
    Boolean isMenuOpen = false;
    OvershootInterpolator overshootInterpolator = new OvershootInterpolator();
    String currentImagePath = null;
    File imageFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_chat_option);

        mainBinding.queryFabButton.setAlpha(0f);
        mainBinding.cameraFabButton.setAlpha(0f);

        mainBinding.cameraFabButton.setOnClickListener(this);
        mainBinding.addFabButton.setOnClickListener(this);
        mainBinding.queryFabButton.setOnClickListener(this);

        mainBinding.queryFabButton.setTranslationY(transactionY);
        mainBinding.cameraFabButton.setTranslationY(transactionY);


    }

    private void openMenu() {
        isMenuOpen = !isMenuOpen;
        mainBinding.addFabButton.animate().setInterpolator(overshootInterpolator).rotation(45f).setDuration(500).start();
        mainBinding.queryFabButton.animate().translationY(0f).alpha(1f).setInterpolator(overshootInterpolator).setDuration(300).start();
        mainBinding.cameraFabButton.animate().translationY(0f).alpha(1f).setInterpolator(overshootInterpolator).setDuration(300).start();

    }

    private void closeMenu() {
        isMenuOpen = !isMenuOpen;
        mainBinding.addFabButton.animate().setInterpolator(overshootInterpolator).rotation(-45f).setDuration(500).start();
        mainBinding.queryFabButton.animate().translationY(transactionY).alpha(0f).setInterpolator(overshootInterpolator).setDuration(300).start();
        mainBinding.cameraFabButton.animate().translationY(transactionY).alpha(0f).setInterpolator(overshootInterpolator).setDuration(300).start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_fab_button:
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
            case R.id.camera_fab_button:
                Log.d("Camera", "onClick: ");
                openCamera();


                break;
            case R.id.query_fab_button:
                openQuery();
                if (isMenuOpen) {
                    closeMenu();
                } else {
                    openMenu();
                }
                break;
        }

    }

    private void openQuery() {
        Intent i = new Intent(ChatOptionActivity.this, WrittenQueryActivity.class);
        startActivity(i);
    }

    private void openCamera() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
        } else {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                File imageFile = null;
                try {
                    imageFile = getFileName();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (imageFile != null) {
                    Uri imageUri = FileProvider.getUriForFile(this, "com.example.agrimitra.fileprovider", imageFile);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
            //startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }
    }

    private File getFileName() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyymmdd_hhmmss").format(new Date());
        String fileName = "jpg_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        imageFile = File.createTempFile(fileName, ".jpg", storageDir);
        currentImagePath = imageFile.getAbsolutePath();
        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Intent i = new Intent(ChatOptionActivity.this, ImageSelectActivity.class);

        Log.d("ChatOptionActivity", "onActivityResult: " + imageFile);

        i.putExtra("IMAGE_NAME", imageFile);

        startActivity(i);
    }


}
