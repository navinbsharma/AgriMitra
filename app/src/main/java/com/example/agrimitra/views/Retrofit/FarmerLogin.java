package com.example.agrimitra.views.Retrofit;

import com.google.gson.annotations.SerializedName;

public class FarmerLogin {

    @SerializedName("status")
    private String status;

    @SerializedName("code")
    private String code;

    @SerializedName("_id")
    private String id;

    @SerializedName("name")
    private String name;

    public String getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
    public String getId(){ return id;}
    public String getName(){ return name;}

}
