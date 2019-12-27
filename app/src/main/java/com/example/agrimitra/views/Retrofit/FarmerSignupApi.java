package com.example.agrimitra.views.Retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface FarmerSignupApi {
    @FormUrlEncoded
    @POST("/register_farmer")
    Call<FarmerSignup>  register(@Field("fname") String name, @Field("mobileNo") String mobile, @Field("mpin") String mpin, @Field("adharNo") String adhar);
}
