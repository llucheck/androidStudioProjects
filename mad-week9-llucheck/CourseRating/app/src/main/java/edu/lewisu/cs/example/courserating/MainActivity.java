package edu.lewisu.cs.example.courserating;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private static final int RATING_INTENT_RESULT = 100;
    private int currentTheme;
    private CourseRatingListAdapter adapter;

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
        adapter = new CourseRatingListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
            String course = data.getStringExtra("courseName");

            //the first rating selects the string, the second is the value inserted into the string
            String ratingString = getResources().getQuantityString(R.plurals.star_rating, rating, rating);
            String toastString = "Rating entered\n";
            toastString += "Course name: " + course + "\n";
            toastString += ratingString;

            Log.d("debug", toastString);

            Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();


        }
    }


}
