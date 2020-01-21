package com.example.agrimitra.views.models;

import com.example.agrimitra.views.models.weather.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherForecast {

    @SerializedName("cnt")
    int count;

    @SerializedName("list")
    List<Weather> weatherForecasts;
}
