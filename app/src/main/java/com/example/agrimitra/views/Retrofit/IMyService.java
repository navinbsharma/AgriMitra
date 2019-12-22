package com.example.agrimitra.views.Retrofit;



import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import io.reactivex.Observable;

public interface IMyService {
    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("fname") String name, @Field("mobile") String mobile, @Field("mpin") String password,@Field("adhar") String adharNo);

    @POST("login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("mobile") String email, @Field("mpin") String password);

}