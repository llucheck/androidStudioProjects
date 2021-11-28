package com.lewisu.leonard.cs.recipebox;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecipeAdapterOnClickHandler{

    private RecipeAdapter adapter;
    private RecipeRespository repository;
    private String searchTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        adapter=new RecipeAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repository = new RecipeRespository(getApplication());

        EditText searchTermField = findViewById(R.id.search_text_view);
        searchTermField.addTextChangedListener(new MainActivity.SearchListener());

        Button searchButton = findViewById(R.id.search_text_button);
        searchButton.setOnClickListener(new searchOnButtonClick());

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecipeDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private class SearchListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            searchTerm = s.toString();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class searchOnButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View v){
            Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
            searchIntent.putExtra("searchTerm", searchTerm);
            startActivity(searchIntent);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.setRecipes(repository.getAllRecipes());
        }
    }

    @Override
    public void onClick(Recipe recipe) {
        Intent detailIntent = new Intent(this, RecipeDetailActivity.class);
        detailIntent.putExtra("id", recipe.getId());
        startActivity(detailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();
        switch(id) {
            case R.id.reset:
                //add test data to database
                repository.initialize();
                adapter.setRecipes(repository.getAllRecipes());
                Toast.makeText(getApplicationContext(), "Reset DB", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
