package com.lewisu.leonard.moviereview;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieDetailActivity extends AppCompatActivity {

    private final static String TAG = MovieDetailActivity.class.getSimpleName();
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = getIntent();
        movie = intent.getParcelableExtra("movie");

        if(movie != null){

            TextView titleTextView = findViewById(R.id.titleTextView);

            titleTextView.setText(movie.getTitle());

            TextView ratingTextView = findViewById(R.id.ratingTextView);
            ratingTextView.setText(movie.getRating());

            TextView summaryTextView = findViewById(R.id.summaryTextView);
            summaryTextView.setText(movie.getSummary());

            TextView reviewUrlTextView = findViewById(R.id.reviewUrlTextView);
            reviewUrlTextView.setText(movie.getReviewLink());
        }
    }

}
