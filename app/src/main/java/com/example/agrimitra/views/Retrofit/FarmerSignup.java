package com.example.agrimitra.views.Retrofit;

import com.google.gson.annotations.SerializedName;

public class FarmerSignup {



    private String id;

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private int code;

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }
}
