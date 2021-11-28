package edu.lewisu.cs.example.courseratingfragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Arrays;


public class CourseRatingListActivity extends AppCompatActivity implements CourseRatingAdapter.TodoListAdapterOnClickHandler {


    private static final int RC_SIGN_IN = 1;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private FirebaseDatabase firebaseDatabase;
    private Query query;
    private FirebaseRecyclerAdapter<CourseRating, CourseRatingAdapter.RatingHolder>
            firebaseRecyclerAdapter;

    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_rating_list);
        if(findViewById(R.id.course_rating_detail_container) != null){
            twoPane = true;
        }

        RecyclerView recyclerView=findViewById(R.id.course_rating_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        firebaseDatabase = FirebaseDatabase.getInstance();
        query = firebaseDatabase.getReference().child("course_rating");


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(twoPane){
                    CourseRatingDetailFragment fragment = new CourseRatingDetailFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.course_rating_detail_container, fragment).commit();
                }else{
                    Intent detailIntent = new Intent(getApplicationContext(), CourseRatingDetailActivity.class);
                    startActivity(detailIntent);
                }
            }
        });



        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user == null) {
                    //user is not signed in
                    startActivityForResult(
                            // Get an instance of AuthUI based on the default app
                            AuthUI.getInstance().
                                    createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.EmailBuilder().build(),
                                            new AuthUI.IdpConfig.GoogleBuilder().build()))
                                    .build(),
                            RC_SIGN_IN);
                }
            }

        };
        setupRecyclerView(recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {


        FirebaseRecyclerOptions<CourseRating> options =
                new FirebaseRecyclerOptions.Builder<CourseRating>()
                        .setQuery(query, CourseRating.class)
                        .build();

        firebaseRecyclerAdapter = new CourseRatingAdapter(options, this);
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        firebaseAuth.addAuthStateListener(authStateListener);
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    protected void onPause() {
        super.onPause();
        firebaseAuth.removeAuthStateListener(authStateListener);
        firebaseRecyclerAdapter.stopListening();
    }

    @Override
    public void onClick(int position) {
        DatabaseReference ref = firebaseRecyclerAdapter.getRef(position);
        String id = ref.getKey();

        if(twoPane){
            Bundle arguments = new Bundle();
            arguments.putString(CourseRatingDetailFragment.ARG_REVIEW_ID, id);
            CourseRatingDetailFragment fragment = new CourseRatingDetailFragment();
            fragment.setArguments(arguments);

            getSupportFragmentManager().beginTransaction().replace(R.id.course_rating_detail_container, fragment).commit();
        }else{
            Intent detailIntent = new Intent(this, CourseRatingDetailActivity.class);
            detailIntent.putExtra(CourseRatingDetailFragment.ARG_REVIEW_ID, id);
            startActivity(detailIntent);
        }
    }


}
