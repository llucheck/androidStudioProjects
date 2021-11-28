package edu.lewisu.cs.example.courseratingfragment;

import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A fragment representing a single CourseRating detail screen.
 * This fragment is either contained in a {@link CourseRatingListActivity}
 * in two-pane mode (on tablets) or a {@link CourseRatingDetailActivity}
 * on handsets.
 */
public class CourseRatingDetailFragment extends Fragment {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private CourseRating courseRating;

    private EditText courseNameEditText;
    private EditText instructorNameEditText;
    private Spinner courseTypeSpinner;
    private RatingBar ratingBar;
    private Button submitButton;


    public static final String ARG_REVIEW_ID = "review_id";
    String reference;


    public CourseRatingDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            reference = getArguments().getString(ARG_REVIEW_ID);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_course_rating_detail, container, false);


        courseNameEditText = rootView.findViewById(R.id.courseNameEditText);
        instructorNameEditText = rootView.findViewById(R.id.instructorNameEditText);
        courseNameEditText.addTextChangedListener(new NameTextListener(courseNameEditText));
        instructorNameEditText.addTextChangedListener(new NameTextListener(instructorNameEditText));

        courseTypeSpinner = rootView.findViewById(R.id.courseTypeSpinner);
        courseTypeSpinner.setOnItemSelectedListener(new CourseTypeSelectedListener());

        ratingBar = rootView.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingChangedListener());


        submitButton = rootView.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new SubmitClickListener());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("course_rating");

        courseRating = new CourseRating();

        if (reference != null) {
            databaseReference = firebaseDatabase.getReference().child("course_rating").child(reference);
            PostListener postListener = new PostListener();
            databaseReference.addValueEventListener(postListener);
            submitButton.setOnClickListener(new OnUpdateButtonClick());


        } else {
            databaseReference = firebaseDatabase.getReference().child("course_rating");
            submitButton.setOnClickListener(new SubmitClickListener());
        }

        return rootView;
    }

    private void setUi() {

        if (courseRating != null) {
            courseNameEditText.setText(courseRating.getCourseName());
            instructorNameEditText.setText(courseRating.getInstructorName());
            ratingBar.setRating(courseRating.getRating());

            String[] stringArray = getResources().getStringArray(R.array.course_type);
            String courseType = courseRating.getCourseType();
            int index = 0;
            for(int i=0; i<stringArray.length; i++){
                if (stringArray[i].equals(courseType)){
                    index = i;
                }
            }
            courseTypeSpinner.setSelection(index);
            submitButton.setText(R.string.update_rating);
            submitButton.setOnClickListener(new OnUpdateButtonClick());
        }
    }



    private class NameTextListener implements TextWatcher {
        private View editText;

         NameTextListener(View v) {
            editText = v;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (editText == courseNameEditText) {
                courseRating.setCourseName(charSequence.toString());
            } else if (editText == instructorNameEditText) {
                courseRating.setInstructorName(charSequence.toString());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private class CourseTypeSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String courseType = (String) adapterView.getItemAtPosition(i);
            if (i != 0) {
                courseRating.setCourseType(courseType);

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class RatingChangedListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            courseRating.setRating((int) v);

        }
    }

    private class SubmitClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            //add new rating
            databaseReference= databaseReference.push();
            databaseReference.setValue(courseRating);
            Snackbar.make(view, R.string.rating_added, Snackbar.LENGTH_LONG).show();

            //now listen for changes - avoids duplicate postings
            PostListener postListener = new PostListener();
            databaseReference.addValueEventListener(postListener);
            submitButton.setText(R.string.update_rating);
            submitButton.setOnClickListener(new OnUpdateButtonClick());
        }
    }

    private class OnUpdateButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            databaseReference.setValue(courseRating);
        }
    }

    private class PostListener implements ValueEventListener{

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                courseRating = dataSnapshot.getValue(CourseRating.class);
                setUi();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        }

}
