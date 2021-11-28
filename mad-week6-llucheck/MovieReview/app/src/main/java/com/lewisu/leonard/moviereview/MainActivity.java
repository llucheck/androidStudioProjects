package com.lewisu.leonard.moviereview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final String API_KEY = "APzV72bdy1mZ7lJfGDK3ADkwSGi1DcOF";
     private ProgressBar progressBar;
     private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);


        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("api.nytimes.com")
                .appendPath("svc")
                .appendPath("movies")
                .appendPath("v2")
                .appendPath("reviews")
                .appendPath("search.json")
                .appendQueryParameter("query", "elephant")
                .appendQueryParameter("api-key", API_KEY);

        Uri nytReviews = builder.build();
        Log.d("url", nytReviews.toString());

        DownloadMovie downloadMovie = new DownloadMovie(this);
        downloadMovie.execute(nytReviews);
    }

    @Override
    public void onClick(Movie movie) {
        Intent detailIntent = new Intent(this, MovieDetailActivity.class);
        detailIntent.putExtra("movie", movie);
        startActivity(detailIntent);
    }

    private static class DownloadMovie extends AsyncTask<Uri, Void, ArrayList<Movie>> {
        private WeakReference<MainActivity> activityWeakReference;

        public DownloadMovie(MainActivity mainActivity){
            activityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        protected void onPreExecute() {
            MainActivity activity = activityWeakReference.get();
            activity.progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected ArrayList<Movie> doInBackground(Uri... uris) {
            OkHttpClient client = new OkHttpClient();
            String jsonData = "";
            ArrayList<Movie> movies = new ArrayList<>();

            try{
                URL url = new URL(uris[0].toString());
                Request.Builder builder = new Request.Builder();
                builder.url(url);
                Request request = builder.build();
                Response response = client.newCall(request).execute();

                if(response.body() != null){
                    jsonData = response.body().string();

                    String title;
                    String rating;
                    String summary;
                    String reviewLink;
                    int numMovies = 10;

                    JSONObject results = new JSONObject((jsonData));
                    JSONArray movieList = results.getJSONArray("results");
                    if(movieList.length() < 10)
                        numMovies = movieList.length();
                    for(int i=0; i<numMovies; i++) {
                        JSONObject movieObject = movieList.getJSONObject(i);
                        title = movieObject.getString("display_title");
                        rating = movieObject.getString("mpaa_rating");
                        summary = movieObject.getString("summary_short");
                        JSONObject reviewObj = movieObject.getJSONObject("link"); //this is a JSON object
                        reviewLink = reviewObj.getString("url");
                        Movie movie = new Movie(title, rating, summary, reviewLink);
                        movies.add(movie);
                    }
                    return movies;

                }
            }catch(Exception e){
                Log.d(TAG, e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Movie> movies) {
            MainActivity activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()) return;
            activity.progressBar.setVisibility(View.GONE);
            if(movies != null){
                activity.movieAdapter.setMovieData(movies);
            }

        }
    }
}
