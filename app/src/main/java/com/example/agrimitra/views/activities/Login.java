package com.example.agrimitra.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor shEdit;
    Call<FarmerLogin> call;
    EditText mobile, mpin;


    TextView signUp;
    Button submit;
    ProgressDialog dialog;

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Retrofit retrofit = new Retrofit.Builder()
                //.baseUrl("https://cryptic-shelf-53020.herokuapp.com")
                .baseUrl("http://172.17.20.35:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        setView();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("login", "onClick: ");


                FarmerLoginAPi loginAPi = retrofit.create(FarmerLoginAPi.class);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mobileNo = mobile.getText().toString();
                        String mPin = mpin.getText().toString();

                        if (mobileNo.length() != 10) {
                            Toast.makeText(Login.this, "Mobile number can be only of 10 digit", Toast.LENGTH_LONG).show();
                        } else if (mPin.length() != 4) {
                            Toast.makeText(Login.this, "MPin must be only of four digit!", Toast.LENGTH_LONG).show();
                        } else {
                            dialog = ProgressDialog.show(Login.this, "",
                                    "Loading. Please wait...", true);

                            call = loginAPi.login(mobileNo, mPin);
                            call.enqueue(new Callback<FarmerLogin>() {
                                @Override
                                public void onResponse(Call<FarmerLogin> call, Response<FarmerLogin> response) {
                                    if (response.code() == 200 && response.isSuccessful()) {
                                        int code = Integer.parseInt(response.body().getCode());
                                        Log.d("SharedPreference", "onResponse: "+response.body().getId());
                                        dialog.dismiss();
                                        if (code == 200) {
                                            Toast.makeText(Login.this, response.body().getStatus(), Toast.LENGTH_LONG).show();
                                            setPrefrences(response.body().getId(),response.body().getName());
                                            Intent intent = new Intent(Login.this, Dashboard.class);

                                            startActivity(intent);
                                        } else if (code == 303) {
                                            Toast.makeText(Login.this, response.body().getStatus(), Toast.LENGTH_LONG).show();

                                        } else if (code == 301) {
                                            Toast.makeText(Login.this, response.body().getStatus(), Toast.LENGTH_LONG).show();
                                        }

                                        Log.d("Agromitra", "onResponse:" + code);
                                    }

                                }

                                @Override
                                public void onFailure(Call<FarmerLogin> call, Throwable t) {
                                    dialog.dismiss();

                                }
                            });
                        }
                    }

                });
            }
        });
    }

    private void setView() {
        mobile = findViewById(R.id.login_mobile);
        mpin = findViewById(R.id.login_mpin);
        signUp = findViewById(R.id.txtSignUp);
        submit = findViewById(R.id.btn_login);
    }
    private void setPrefrences(String id,String name){
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        shEdit = sharedPreferences.edit();
        Log.d("SharePreference", "setPrefrences: "+name+" "+id);
        shEdit.putString("id",id);
        shEdit.putString("name",name);
        Log.d("SharePreference",""+sharedPreferences.getString("id",null));
        shEdit.apply();
    }
}