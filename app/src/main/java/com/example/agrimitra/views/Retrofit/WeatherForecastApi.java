package com.example.agrimitra.views.Retrofit;

import com.example.agrimitra.views.models.weather.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherForecastApi {


    String BASE_URL = "https://api.openweathermap.org/data/2.5/";
//"+latitude+""+longitude+""+appid+"
    //
    @GET("forecast")
    Call<Weather> getWeather(@Query("lat") String lat,@Query("lon") String longi,@Query("cnt") int cnt, @Query("appid") String appId);
    //getWeather(@Query("lat") String lat,@Query("lon") String longi,@Query("cnt") int cnt, @Query("appid") String appId);
}
