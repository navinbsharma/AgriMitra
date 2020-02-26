package com.example.agrimitra.views.activities.weather;



import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.agrimitra.R;
import com.example.agrimitra.views.Retrofit.WeatherForecastApi;
import com.example.agrimitra.views.models.weather.Weather;
import com.example.agrimitra.views.models.weather.WeatherCity;
import com.loopj.android.http.RequestParams;


public class WeatherForecastActivity extends AppCompatActivity {
    int REQUEST_CODE = 123;
    final String APP_ID = "5c415791a04f2360930c239462e1e491";
    String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;

    // Time between location updates (5000 milliseconds or 5 seconds)
    final long MIN_TIME = 5000;
    // Distance between location updates (1000m or 1km)
    final float MIN_DISTANCE = 1000;


    LocationManager mLocationManager;
    LocationListener mLocationListener;


    float latitude=108;
    float longitude=72;



    protected void onResume() {
        super.onResume();
        Log.d("Agromitra", "onResume: ");
        Log.d("AgromMItra", "Getting weather location");
        getWeatherForCurrentLocation();
    }
    // TODO: Add getWeatherForCurrentLocation() here:
    private void getWeatherForCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

        } else {

            mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            mLocationListener = new LocationListener() {

                @Override
                public void onLocationChanged(Location location) {
                    Log.d("agromitra", "onLocationChanged: ");
                    latitude = Float.valueOf(String.valueOf(location.getLatitude()));
                    longitude = Float.valueOf(String.valueOf(location.getLongitude()));

                    Log.d("AgroMitra", "onLocationChanged: " + latitude + "" + longitude);

                    //RequestParams params = new RequestParams();
                    //params.put("lat", latitude);
                    //params.put("lon", longitude);
                    //params.put("appid", APP_ID);
                    //letsDoSomeNetworking(params);

                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            mLocationManager.requestLocationUpdates(LOCATION_PROVIDER, MIN_TIME, MIN_DISTANCE, mLocationListener);
            mLocationManager.requestLocationUpdates(LOCATION_PROVIDER, 0, 0, mLocationListener);
            mLocationManager.requestLocationUpdates(mLocationManager.NETWORK_PROVIDER, 0,0, mLocationListener);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_show);

        getWeatherForCurrentLocation();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherForecastApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //latitude = getLatitude();

        //longitude = getLongitude();


        WeatherForecastApi weatherForecastApi = retrofit.create(WeatherForecastApi.class);
        Call<Weather> call = weatherForecastApi.getWeather();
        //= weatherForecastApi.getWeather(latitude,longitude,10,APP_ID);

        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                Log.d("WeatherForecastDone", "onResponse: "+response.body());
                Weather weather = response.body();
                WeatherCity weatherCity = weather.getCity();
                updateCityDetails(weatherCity);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    private void updateCityDetails(WeatherCity city){

    }

}

