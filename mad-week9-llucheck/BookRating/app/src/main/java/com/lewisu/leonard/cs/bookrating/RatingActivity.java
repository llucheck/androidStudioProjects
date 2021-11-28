package com.lewisu.leonard.cs.bookrating;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class RatingActivity extends AppCompatActivity {

    private final String TAG = RatingActivity.class.getSimpleName();
    private BookRating bookRating;
    private EditText bookNameEditText;
    private EditText authorNameEditText;
    private Spinner bookTypeSpinner;
    private RatingBar ratingBar;
    private Button submitButton;
    private BookRatingViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Settings.DEFAULT_THEME);
        setContentView(R.layout.activity_rating);
        bookRating = new BookRating();

        viewModel = new ViewModelProvider(this).get(BookRatingViewModel.class);

        bookNameEditText = findViewById(R.id.bookNameEditText);
        authorNameEditText = findViewById(R.id.authorNameEditText);
        bookNameEditText.addTextChangedListener(new NameTextListener(bookNameEditText));
        authorNameEditText.addTextChangedListener(new NameTextListener(authorNameEditText));

        bookTypeSpinner = findViewById(R.id.bookTypeSpinner);
        bookTypeSpinner.setOnItemSelectedListener(new BookTypeSelectedListener());

        ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingChangedListener());
        ratingBar.setRating(Settings.DEFAULT_RATING);

        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new SubmitClickListener());



    }


    private class NameTextListener implements TextWatcher {
        private View editText;

        public NameTextListener(View v){
            editText = v;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if(editText == bookNameEditText) {
                bookRating.setBookName(charSequence.toString());
                Log.d(TAG, "Book updated to " + bookRating.getBookName());
            }else if(editText == authorNameEditText) {
                bookRating.setAuthorName(charSequence.toString());
                Log.d(TAG, "Author updated to " + bookRating.getAuthorName());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private class BookTypeSelectedListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String Genre = (String)adapterView.getItemAtPosition(i);
            if (i!=0) {
                bookRating.setGenre(Genre);
                Log.d(TAG, "Genre: " + Genre);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    private class RatingChangedListener implements RatingBar.OnRatingBarChangeListener{
        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            bookRating.setRating((int)v);
            Log.d(TAG, "rating " + v);
        }
    }

    private class SubmitClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            //String bookName = bookRating.getBookName();
            //int rating = bookRating.getRating();

            viewModel.insertRatings(bookRating);

            Intent returnIntent = new Intent();
            returnIntent.putExtra("bookName", bookRating.getBookName());
            returnIntent.putExtra("rating", bookRating.getRating());
            setResult(RESULT_OK, returnIntent);
            finish();

        }
    }
}
