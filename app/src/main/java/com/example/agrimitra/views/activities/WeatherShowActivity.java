package com.example.agrimitra.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import cz.msebera.android.httpclient.Header;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.agrimitra.R;
import com.example.agrimitra.views.models.WeatherDataModel;
import com.example.agrimitra.views.models.WeatherForecast;
import com.example.agrimitra.views.models.WeatherForecastDataModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherShowActivity extends AppCompatActivity {

    int REQUEST_CODE = 123;
    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    final String WEATHER_URL1 = "https://api.openweathermap.org/data/2.5/forecast";
    // App ID to use OpenWeather data
    final String APP_ID = "5c415791a04f2360930c239462e1e491";
    // Time between location updates (5000 milliseconds or 5 seconds)
    final long MIN_TIME = 5000;
    // Distance between location updates (1000m or 1km)
    final float MIN_DISTANCE = 1000;

    // TODO: Set LOCATION_PROVIDER here:
    String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;

    SimpleDateFormat simpleDateFormat;


    // Member Variables:
    TextView mCityLabel;
    ImageView mWeatherImage;
    TextView mTemperatureLabel , mMinTemperature, mMaxTemperature;
    TextView mUpdatedTime , mStatus;
    TextView mSunrise, mSunset;
    TextView mWind , mPressure , mHumidity;

    // TODO: Declare a LocationManager and a LocationListener here:
    LocationManager mLocationManager;
    LocationListener mLocationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_show);

        mCityLabel = (TextView) findViewById(R.id.address);
        //mWeatherImage = (ImageView) findViewById(R.id.weatherSymbolIV);
        mTemperatureLabel = (TextView) findViewById(R.id.temp);
        mMinTemperature = (TextView) findViewById(R.id.temp_min);
        mMaxTemperature = (TextView) findViewById(R.id.temp_max);

        //Status of atmostphere
        mStatus = (TextView) findViewById(R.id.status);
        mWind = (TextView) findViewById(R.id.wind);
        mPressure = (TextView) findViewById(R.id.pressure);
        mHumidity = (TextView) findViewById(R.id.humidity);

        //Time View
        mUpdatedTime = (TextView) findViewById(R.id.updated_at);
        mSunrise = (TextView) findViewById(R.id.sunrise);
        mSunset = (TextView) findViewById(R.id.sunset);

        Log.d("agromitra", "onCreate: Hello");

       /* ImageButton changeCityButton = (ImageButton) findViewById(R.id.changeCityButton)*/;

        // TODO: Add an OnClickListener to the changeCityButton here:
    }

    // TODO: Add onResume() here:COmpo
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Agromitra", "onResume: ");
        Log.d("AgromMItra", "Getting weather location");
        getWeatherForCurrentLocation();
    }


    // TODO: Add getWeatherForNewCity(String city) here:


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
                    String latitude = String.valueOf(location.getLatitude());
                    String longitude = String.valueOf(location.getLongitude());

                    Log.d("AgroMitra", "onLocationChanged: " + latitude + "" + longitude);

                    RequestParams params = new RequestParams();
                    params.put("lat", latitude);
                    params.put("lon", longitude);
                    params.put("appid", APP_ID);
                    letsDoSomeNetworking(params);

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("AgroMitra", "onRequestPermissionsResult: Granted ");
                getWeatherForCurrentLocation();
            } else {
                Log.d("AgroMitra", "onRequestPermissionsResult: PermissionDenaid");
            }
        }
    }

    // TODO: Add letsDoSomeNetworking(RequestParams params) here:
    private void letsDoSomeNetworking(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpClient client1 = new AsyncHttpClient();
        client.get(WEATHER_URL, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("AgroMitra", "OnSuccess : OnSuccess");
                Log.d("AgroMitra", "onSuccess: " + response.toString());

                WeatherDataModel weatherData = WeatherDataModel.fromJSON(response);
                updateUI(weatherData);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.d("AgroMitra", "onFailure: " + e.toString());

            }
        });
        client1.get(WEATHER_URL1, params, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("AgroMitra", "OnSuccess : OnSuccess");
                Log.d("AgroMitra", "onSuccess: " + response.toString());

                WeatherForecastDataModel weatherForecastData = WeatherForecastDataModel.fromJSON(response);
                Log.d("WeatherForecast", "onSuccess: "+weatherForecastData.getCount());
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable e, JSONObject response) {
                Log.d("AgroMitra", "onFailure: " + e.toString());

            }
        });
    }


    // TODO: Add updateUI() here:
    public void updateUI(WeatherDataModel weatherData) {
        SimpleDateFormat formatter;
        Log.d("Agromitra", "updateUI: "+weatherData.getHumidity()+" "+weatherData.getDt());

        formatter = new SimpleDateFormat("dd MMMM yyyy, h:mm a");
        long dt = weatherData.getDt();
        String todayDate = formatter.format(new Date(dt * 1000));
        mUpdatedTime.setText(todayDate);
        Log.d("Agromitra", "updateUI: "+todayDate);

        formatter = new SimpleDateFormat("h:mm a");
        long sunRise = weatherData.getSunrise();
        String sunRiseTime = formatter.format(new Date(sunRise * 1000));
        mSunrise.setText(sunRiseTime);
        Log.d("Agromitra", "updateUI: "+sunRiseTime);

        long sunSet = weatherData.getSunset();
        String sunSetTime = formatter.format(new Date(sunSet * 1000));
        mSunset.setText(sunSetTime);
        Log.d("Agromitra", "updateUI: "+sunSetTime);






        mCityLabel.setText(weatherData.getmCity());

        //Temperature Setting
        mTemperatureLabel.setText(weatherData.getmTemperature()+" C" );
        mMinTemperature.setText("Min temp :"+weatherData.getMinTemp()+" C");
        mMaxTemperature.setText("Max temp :"+weatherData.getMaxTemp()+" C");

        //STATUS

        mWind.setText(weatherData.getWindSpeed());
        mStatus.setText(weatherData.getStatus());
        mPressure.setText(weatherData.getPressure());
        mHumidity.setText(weatherData.getHumidity());


        //int resourseID = getResources().getIdentifier(weatherData.getmIconName(), "drawable", getPackageName());
        //mWeatherImage.setImageResource(resourseID);
    }


    // TODO: Add onPause() here:


}
