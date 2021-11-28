package com.lewisu.leonard.cs.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button previousButton;
    private TextView questionTextView;

    private Question[] questions = new Question[]{
         new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3, false)
    };
    private int currentIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        nextButton = findViewById(R.id.next_button);
        previousButton = findViewById(R.id.previous_button);
        questionTextView = findViewById(R.id.question_text_view);

        trueButton.setOnClickListener(new TrueButtonClickListener());
        falseButton.setOnClickListener(new FalseButtonClickListener());
        nextButton.setOnClickListener(new NextButtonClickListener());
        previousButton.setOnClickListener(new PreviousButtonClickListener());

        updateQuestion();
    }

    private void updateQuestion(){
        int question = questions[currentIndex].getTextResId();
        questionTextView.setText(question);
    }

    private void checkAnswer(Boolean userPress){
        boolean correctAnswer = questions[currentIndex].isAnswerTrue();

        int toastTextId;

        if(userPress == correctAnswer){
            toastTextId = R.string.correct;
        }else{
            toastTextId = R.string.incorrect;
        }

        Toast.makeText(this, toastTextId, Toast.LENGTH_SHORT).show();
    }

    private class TrueButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
            checkAnswer(true);
        }
    }
    private class FalseButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
            checkAnswer(false);
        }
    }

    private class NextButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            currentIndex = (currentIndex + 1) % questions.length;
            updateQuestion();
        }
    }
    private class PreviousButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(currentIndex == 0){
                currentIndex = questions.length - 1;
            }
            else {
                currentIndex = (currentIndex - 1);
            }
            updateQuestion();
        }
    }
}
