package com.example.agrimitra.views.Retrofit;

import com.example.agrimitra.views.models.weather.Weather;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherForecastApi {


    String BASE_URL = "https://api.openweathermap.org/data/2.5/";
//"+latitude+""+longitude+""+appid+"
    @GET("forecast?lat=35&lon=139&cnt=10&appid=5c415791a04f2360930c239462e1e491")
    Call<Weather> getWeather();
    //getWeather(@Query("lat") String lat,@Query("lon") String longi,@Query("cnt") int cnt, @Query("appid") String appId);
}
