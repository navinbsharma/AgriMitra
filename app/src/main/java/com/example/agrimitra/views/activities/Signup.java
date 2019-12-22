package com.example.agrimitra.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrimitra.R;
import com.example.agrimitra.views.Retrofit.IMyService;

public class Signup extends AppCompatActivity {
    Button b;
    Intent i;
    EditText username , mpin , confirmPin , adhar , mobile ;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;

    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setView();
        b = findViewById(R.id.btn_signup);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser(username.toString(),mobile.toString(),mpin.toString(),confirmPin.toString(),adhar.toString());



                i = new Intent(Signup.this, Login.class);
                startActivity(i);
            }
        });

    }

    private void registerUser( String username,String mobile,String mpin , String conformPin, String adhar){
        if (TextUtils.isEmpty(mobile)){
            Toast.makeText(Signup.this, "Please insert a mobile number", Toast.LENGTH_SHORT);
            return;
        } else if(TextUtils.isEmpty(mpin)) {
            Toast.makeText(Signup.this, "Please insert a mpin", Toast.LENGTH_SHORT);
            return;
        } else if(TextUtils.isEmpty(conformPin)) {
            Toast.makeText(Signup.this, "Please insert a confirm pin", Toast.LENGTH_SHORT);
            return;
        } else if(TextUtils.isEmpty(username)) {
            Toast.makeText(Signup.this, "Please insert a username", Toast.LENGTH_SHORT);
            return;
        } else if(TextUtils.isEmpty(adhar)) {
            Toast.makeText(Signup.this, "Please insert a adhar", Toast.LENGTH_SHORT);
            return;
        }

        compositeDisposable.add(iMyService.loginUser(mobile,mpin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(Signup.this,""+response,Toast.LENGTH_SHORT);

                    }
                }));




    }

    private void setView(){
        username = findViewById(R.id.signup_username);
        mobile = findViewById(R.id.signup_mobile);
        mpin = findViewById(R.id.signup_mpin);
        confirmPin = findViewById(R.id.signup_confirm_pin);
        adhar = findViewById(R.id.signup_adhar);
    }
}