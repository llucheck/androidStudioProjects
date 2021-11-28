package edu.lewisu.cs.example.weather;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String API_KEY = "344ddbe1cc2904334a43e92a5cf9c64a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up RecyclerView here

        getForecast();

    }

    private void getForecast(){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority("api.openweathermap.org")
                .appendPath("data")
                .appendPath("2.5")
                .appendPath("forecast")
                .appendQueryParameter("zip", "60446")
                .appendQueryParameter("units", "imperial")
                .appendQueryParameter("appid", API_KEY);

        Uri forecastUri = builder.build();
        Log.d("uri", forecastUri.toString());

        DownloadWeather downloadWeather = new DownloadWeather(this);
        downloadWeather.execute(forecastUri.toString());
    }

    private static class DownloadWeather extends
            AsyncTask<String, Void, ArrayList<Forecast>> {

        private WeakReference<MainActivity> activityReference;


        DownloadWeather(MainActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected ArrayList<Forecast> doInBackground(String... strings) {
            return null;
        }
    }
}
