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
import android.widget.Toast;

import com.example.agrimitra.R;
import com.example.agrimitra.views.Retrofit.FarmerSignup;
import com.example.agrimitra.views.Retrofit.FarmerSignupApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Signup extends AppCompatActivity {

    Call<FarmerSignup> call;

    EditText name , mobile , mpin, cmpin , adhar ;
    Button signup;
    String fname , mobileNo , mpinS , adharNo;
    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http:// 192.168.43.198:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FarmerSignupApi signupApi = retrofit.create(FarmerSignupApi.class);



        setView();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fname = name.getText().toString();
                mpinS = mpin.getText().toString();
                mobileNo = mobile.getText().toString();
                adharNo = adhar.getText().toString();



                call = signupApi.register(fname , mobileNo , mpinS , adharNo);
                Log.d("AgroMitra" , "onResponse: ConfigurationListener::"+call.request().url());


                call.enqueue(new Callback<FarmerSignup>() {
                    @Override
                    public void onResponse(Call<FarmerSignup> call, Response<FarmerSignup> response) {
                        if(response.code()==200 && response.isSuccessful() ){
                            Log.d("AgroMitra", "onClick: Done registration");
                            Toast.makeText(Signup.this,"Success"+response.code(),Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(Signup.this , Login.class);
//                            startActivity(intent);
                        }
                        else{
                            Log.d("AgroMitra", "onClick: Done registration");
                            Toast.makeText(Signup.this , "Error"+response.code()+""+response.message() , Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FarmerSignup> call, Throwable t) {
                        Toast.makeText(Signup.this , "Error"+t.getMessage() , Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });
    }

    private void setView(){
        name = findViewById(R.id.signup_username);
        mobile = findViewById(R.id.signup_mobile);
        mpin = findViewById(R.id.signup_mpin);
        cmpin = findViewById(R.id.signup_confirm_pin);
        adhar = findViewById(R.id.signup_adhar);
        signup = findViewById(R.id.btn_signup);
    }
}