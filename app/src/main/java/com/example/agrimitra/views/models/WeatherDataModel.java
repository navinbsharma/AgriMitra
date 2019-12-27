package com.example.agrimitra.views.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherDataModel {

    // TODO: Declare the member variables here

    String mCity;
    long dt;

    String status;
    String mTemperature;
    String minTemp;


    String maxTemp;

    //int mCondition;

    //String mIconName;

    long sunrise;
    long sunset;
    String windSpeed , pressure, humidity, countryName;

    long miliSec = 3010;

    // Creating date format


    // Creating date from milliseconds


    // TODO: Create a com.example.agrimitra.views.WeatherDataModel from a JSON:
    public static WeatherDataModel fromJSON(JSONObject response){

        WeatherDataModel weatherData = new WeatherDataModel();
        try {

            //CITY AND COUNTRY NAME
            weatherData.mCity = response.getString("name") + ", " + response.getJSONObject("sys").getString("country") ;

            //WEATHER CLOUD
            JSONArray weatherArray = response.getJSONArray("weather");
            Log.d("JSONArray",""+weatherArray);
            JSONObject weatherObject = (JSONObject) weatherArray.get(0);
            weatherData.status = weatherObject.getString("main");


            //TIME STATUS
            weatherData.dt = response.getLong("id");
            weatherData.sunrise =response.getJSONObject("sys").getLong("sunrise");
            weatherData.sunset = response.getJSONObject("sys").getLong("sunset");





            //TEMPERATURE
            double temp = response.getJSONObject("main").getDouble("temp") -273.15;
            weatherData.mTemperature = Integer.toString((int)Math.rint(temp));
            double minTemp = response.getJSONObject("main").getDouble("temp_min") -273.15;
            weatherData.minTemp = Integer.toString((int)Math.rint(minTemp));
            double maxTemp = response.getJSONObject("main").getDouble("temp_max") -273.15;
            weatherData.maxTemp = Integer.toString((int)Math.rint(maxTemp));



            double windSpeedDouble = response.getJSONObject("wind").getDouble("speed");
            weatherData.windSpeed = Double.toString((double)windSpeedDouble);

            double pressureDouble = response.getJSONObject("main").getDouble("pressure");
            weatherData.pressure = Double.toString((double)pressureDouble);

            double humidityDouble = response.getJSONObject("main").getDouble("humidity");
            weatherData.humidity = Double.toString((double)humidityDouble);

        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("Error", "fromJSON:"+e.getMessage());
        }


        return weatherData;
    }



    // TODO: Create getter methods for temperature, city, and icon name:


    public String getmTemperature(){
        return mTemperature+"°";
    }

    public String getmCity() {
        return mCity;
    }

//    public String getmIconName() {
//        return mIconName;
//    }

    public Long getDt() {
        Log.d("Agromitra", "getDt: "+dt);
        return dt;
    }
    public String getStatus() {
        return status;
    }
    public String getMinTemp() {
        return minTemp+"°";
    }

    public String getMaxTemp() {
        return maxTemp+"°";
    }
    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

}
