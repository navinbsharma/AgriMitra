package com.example.agrimitra.views.models.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherCity {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    String cityName;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;

    @SerializedName("sunset")
    @Expose
    private Integer sunset;

    public WeatherCity(Integer id, String cityName, String country, Integer sunrise, Integer sunset) {
        this.id = id;
        this.cityName = cityName;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public Integer getId() {
        return id;
    }



    public String getCityName() {
        return cityName;
    }
    public String getCountry() {
        return country;
    }
    public Integer getSunrise() {
        return sunrise;
    }
    public Integer getSunset() {
        return sunset;
    }
}


