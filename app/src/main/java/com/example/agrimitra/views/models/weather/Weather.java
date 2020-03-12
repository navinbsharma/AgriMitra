package com.example.agrimitra.views.models.weather;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Weather implements Serializable {


    @SerializedName("cnt")
    @Expose
    private Integer cnt;

    @SerializedName("list")
    @Expose
    private java.util.List<WeatherList> list = new ArrayList<WeatherList>();
    @SerializedName("city")
    @Expose
    private WeatherCity city;

    public Weather(Integer cnt, List<WeatherList> list, WeatherCity city){
        Log.d("WeatherChecking", "Weather: "+city.getCityName());
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

    public Integer getCnt() {
        return cnt;
    }
    public java.util.List<WeatherList> getList() {
        return list;
    }
    public WeatherCity getCity() {
        return city;
    }
}


