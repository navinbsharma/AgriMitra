package com.example.agrimitra.views.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroClient {
    private static Retrofit instance;

    public static Retrofit getInstance() {
        if(instance == null){
            instance = new Retrofit.Builder().baseUrl("http://10.0.0.2:3000")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }
        return instance;
    }
}