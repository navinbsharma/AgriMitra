package com.example.agrimitra.views.Retrofit;

import com.google.gson.annotations.SerializedName;

public class FarmerLogin {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private String code;

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

}
