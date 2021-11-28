package com.lewisu.leonard.cs.bookrating;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int RATING_INTENT_RESULT = 100;
    private int currentTheme;
    private BookRatingListAdapter adapter;
    private BookRatingViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        Settings.setPreferences(this);
        setTheme(Settings.DEFAULT_THEME);
        currentTheme =Settings.DEFAULT_THEME;
        setContentView(R.layout.activity_main);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RatingActivity.class);
                startActivityForResult(intent, RATING_INTENT_RESULT);
            }
        });

        RecyclerView recyclerView=findViewById(R.id.recycler_view);
        adapter = new com.lewisu.leonard.cs.bookrating.BookRatingListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = new ViewModelProvider(this).get(BookRatingViewModel.class);

        viewModel.getRatings().observe(this, new Observer<List<BookRating>>() {
            @Override
            public void onChanged(List<BookRating> bookRatings) {
                adapter.setRatings(bookRatings);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int id = (int)viewHolder.itemView.getTag();
                viewModel.deleteRating(id);

            }
        }).attachToRecyclerView(recyclerView);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(Settings.DEFAULT_THEME != currentTheme){
            currentTheme = Settings.DEFAULT_THEME;
            setTheme(currentTheme);
            recreate();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.action_settings:
                Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
                startActivity(startSettingsActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == RATING_INTENT_RESULT) {
            int rating = data.getIntExtra("rating", 0);
            String book = data.getStringExtra("bookName");

            //the first rating selects the string, the second is the value inserted into the string
            String ratingString = getResources().getQuantityString(R.plurals.star_rating, rating, rating);
            String toastString = "Rating entered\n";
            toastString += "Book name: " + book + "\n";
            toastString += ratingString;

            Log.d("debug", toastString);

            Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();


        }
    }


}
