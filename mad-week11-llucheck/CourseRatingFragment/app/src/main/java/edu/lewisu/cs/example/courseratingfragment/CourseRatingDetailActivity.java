package edu.lewisu.cs.example.courseratingfragment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;


public class CourseRatingDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_rating_detail);

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.

            CourseRatingDetailFragment fragment = new CourseRatingDetailFragment();
            if(getIntent().getStringExtra(CourseRatingDetailFragment.ARG_REVIEW_ID) != null){
                String id = getIntent().getStringExtra(CourseRatingDetailFragment.ARG_REVIEW_ID);
                Bundle arguments = new Bundle();
                arguments.putString(CourseRatingDetailFragment.ARG_REVIEW_ID, id);
                fragment.setArguments(arguments);
            }
            getSupportFragmentManager().beginTransaction().add(R.id.course_rating_detail_container, fragment).commit();
        }
    }


}
