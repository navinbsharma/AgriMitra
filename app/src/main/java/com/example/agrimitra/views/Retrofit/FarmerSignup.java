package com.example.agrimitra.views.Retrofit;

import com.google.gson.annotations.SerializedName;

public class FarmerSignup {

    private String id;

    @SerializedName("mobile")
    private String mobileNo;

    @SerializedName("name")
    private String name;

    @SerializedName("mpin")
    private String mpin;

    @SerializedName("adhar")
    private String adhar;

    public String getId() {
        return id;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public String getName() {
        return name;
    }

    public String getMpin() {
        return mpin;
    }

    public String getAdhar() {
        return adhar;
    }



}
