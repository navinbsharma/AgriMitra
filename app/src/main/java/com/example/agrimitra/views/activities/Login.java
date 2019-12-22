package com.example.agrimitra.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

//import io.reactivex.schedulers.Schedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;




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
import com.example.agrimitra.views.Retrofit.RetroClient;


public class Login extends AppCompatActivity {
    Button btnLogin;
    Intent i;
    TextView txtSignUp;
    EditText mobileNoView, mpinNoView;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyService iMyService;


    @Override
    protected void onStop(){
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Retrofit retrofitClient = RetroClient.getInstance();
        iMyService = retrofitClient.create(IMyService.class);







        viewSetter();


        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(Login.this, Signup.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser(mobileNoView.toString(),mpinNoView.toString());

//                i = new Intent(Login.this, Dashboard.class);
//                startActivity(i);
            }
        });


    }

    private void loginUser(String mobile, String mpin) {
        if (TextUtils.isEmpty(mobile)){
            Toast.makeText(Login.this, "Please insert a mobile number", Toast.LENGTH_SHORT);
            return;
        } else if(TextUtils.isEmpty(mpin)) {
            Toast.makeText(Login.this, "Please insert a mobile number", Toast.LENGTH_SHORT);
            return;
        }

        compositeDisposable.add(iMyService.loginUser(mobile,mpin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        Toast.makeText(Login.this,""+response,Toast.LENGTH_SHORT);

                    }
                }));

    }

    private void viewSetter() {
        mobileNoView = findViewById(R.id.login_mobile);
        mpinNoView = findViewById(R.id.login_mpin);

        btnLogin = findViewById(R.id.btn_login);
        txtSignUp = findViewById(R.id.txtSignUp);

    }
}