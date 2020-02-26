package com.example.agrimitra.views.models.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherList {
    @SerializedName("dt")
    @Expose
    private Integer dt;

    @SerializedName("main")
    @Expose
    private WeatherMain main;

    public WeatherList(Integer dt, WeatherMain main) {
        this.dt = dt;
        this.main = main;
    }

    public Integer getDt() {
        return dt;
    }
    public WeatherMain getMain() {
        return main;
    }



}







