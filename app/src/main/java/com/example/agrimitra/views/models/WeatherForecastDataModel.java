package com.example.agrimitra.views.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherForecastDataModel {


    int count;

    public static WeatherForecastDataModel fromJSON(JSONObject response){
        WeatherForecastDataModel weatherForecastDataModel = new WeatherForecastDataModel();
        try {
            weatherForecastDataModel.count = response.getInt("cnt");


        }catch (JSONException e) {
            e.printStackTrace();
            Log.d("Error", "fromJSON:"+e.getMessage());
        }

        return weatherForecastDataModel;
    }
    public int getCount() {
        return count;
    }

}
