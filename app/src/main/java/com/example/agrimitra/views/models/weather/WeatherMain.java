package com.example.agrimitra.views.models.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WeatherMain implements Serializable {

    @SerializedName("temp")
    @Expose
    private Double temp;

    public WeatherMain(Double temp) {
        this.temp = temp;
    }

    public Double getTemp() {
        return temp;
    }


}



