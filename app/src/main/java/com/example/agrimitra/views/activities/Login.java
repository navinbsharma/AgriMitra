package com.example.agrimitra.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrimitra.R;
import com.example.agrimitra.views.Retrofit.FarmerLogin;
import com.example.agrimitra.views.Retrofit.FarmerLoginAPi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Login extends AppCompatActivity {
    Call<FarmerLogin> call;
    EditText mobile , mpin ;
    TextView signUp ;
    Button submit;
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        setView();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.137.44:3000")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                FarmerLoginAPi loginAPi = retrofit.create(FarmerLoginAPi.class);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mobileNo = mobile.getText().toString();
                        String mPin = mpin.getText().toString();


                        call = loginAPi.login(mobileNo , mPin);
                        call.enqueue(new Callback<FarmerLogin>() {
                            @Override
                            public void onResponse(Call<FarmerLogin> call, Response<FarmerLogin> response) {
                                if(response.code() == 200 && response.isSuccessful()){
                                    String code = response.body().getCode();
                                    Log.d("Agromitra", "onResponse:"+code);
                                    Toast.makeText(Login.this, "Successful login", Toast.LENGTH_LONG).show();
                                    Intent intent  = new Intent(Login.this , Dashboard.class);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onFailure(Call<FarmerLogin> call, Throwable t) {

                            }
                        });
                    }

                });


            }
        });


    }

    private void setView(){
        mobile = findViewById(R.id.login_mobile);
        mpin = findViewById(R.id.login_mpin);
        signUp = findViewById(R.id.txtSignUp);
        submit = findViewById(R.id.btn_login);
    }


}