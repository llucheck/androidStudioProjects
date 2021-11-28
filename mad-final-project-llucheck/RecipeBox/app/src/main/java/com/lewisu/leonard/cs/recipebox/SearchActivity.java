package com.lewisu.leonard.cs.recipebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.LinearLayout;
import org.json.JSONArray;
import org.json.JSONObject;
import android.util.Log;
import android.view.View;
import android.os.AsyncTask;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.SearchAdapterOnClickHandler {
    private static final String TAG = MainActivity.class.getSimpleName();
    private final String API_KEY = "1";
    private SearchAdapter searchAdapter;
    private String searchTerm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        searchTerm = "search.php?s=" + intent.getStringExtra("searchTerm");

        RecyclerView recyclerView=findViewById(R.id.search_recycler_view);
        searchAdapter=new SearchAdapter(this, this);
        recyclerView.setAdapter(searchAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("themealdb.com")
                .appendPath("api")
                .appendPath("json")
                .appendPath("v1")
                .appendPath(API_KEY)
                .appendEncodedPath(searchTerm);

        Uri foodSearch = builder.build();
        Log.d("url", foodSearch.toString());

        DownloadRecipes downloadRecipes = new DownloadRecipes(this);
        downloadRecipes.execute(foodSearch);

    }

    @Override
    public void onClick(Recipe recipe){
        Intent detailIntent = new Intent(this, RecipeDetailActivity.class);
        detailIntent.putExtra("id", 0);
        detailIntent.putExtra("recipe", recipe);
        startActivity(detailIntent);
    }


    private static class DownloadRecipes extends AsyncTask<Uri, Void, ArrayList<Recipe>> {
        private WeakReference<SearchActivity> activityWeakReference;

        public DownloadRecipes(SearchActivity searchActivity){
            activityWeakReference = new WeakReference<>(searchActivity);
        }

        @Override
        protected void onPreExecute() {
            SearchActivity activity = activityWeakReference.get();
        }

        @Override
        protected ArrayList<Recipe> doInBackground(Uri... uris) {
            OkHttpClient client = new OkHttpClient();
            String jsonData = "";
            ArrayList<Recipe> recipes = new ArrayList<>();

            try{
                URL url = new URL(uris[0].toString());
                Request.Builder builder = new Request.Builder();
                builder.url(url);
                Request request = builder.build();
                Response response = client.newCall(request).execute();

                if(response.body() != null){
                    jsonData = response.body().string();

                    String recipeTitle;
                    String author;
                    String ingredientList = "";
                    String directions;
                    String ingredient;
                    String amount;
                    String searchIngredient;
                    String searchAmount;
                    int numRecipes = 10;

                    JSONObject results = new JSONObject((jsonData));
                    JSONArray recipeList = results.getJSONArray("meals");
                    if(recipeList.length() > numRecipes)
                        numRecipes = recipeList.length();
                    for(int i=0; i<recipeList.length(); i++) {
                        ingredientList = "";
                        JSONObject recipeObject = recipeList.getJSONObject(i);
                        recipeTitle = recipeObject.getString("strMeal");
                        author = "unknown";
                        for(int j=1; j<20; j++){
                            searchIngredient = "strIngredient" + j;
                            searchAmount = "strMeasure" + j;
                            ingredient = recipeObject.getString(searchIngredient);
                            amount = recipeObject.getString(searchAmount);
                            ingredientList = ingredientList + amount + " " + ingredient + "\n";
                        }
                        directions = recipeObject.getString("strInstructions");
                        Recipe recipe = new Recipe(recipeTitle, author, ingredientList, directions);
                        recipes.add(recipe);
                    }
                    return recipes;

                }
            }catch(Exception e){
                Log.d(TAG, e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Recipe> recipes) {
            SearchActivity activity = activityWeakReference.get();
            if(activity == null || activity.isFinishing()) return;
            if(recipes != null){
                activity.searchAdapter.setRecipeData(recipes);
            }

        }
    }
}
