package com.example.mobileappgroup8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;

public class QuizActivity extends SecondActivity {

    /**
     * @param questions: String array of the questions used in the quiz; each question in the array is cast onto the textview depending on their index
     * @param answeredQuestions: An integer array which keeps track of which questions have already been asked
     * @param questionNumber: Used to tell which question is currently being displayed
     */
    private String[] questions = {"Feeling nervous, anxious, or on edge", "kysymys 2", "kysymys 3", "kysymys 4", "kysymys 5 ", "kysymys 6", "kysymys 7"};
    private int[][] answeredQuestions = new int[7][2];
    private int questionNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        final TextView questionTV = findViewById(R.id. text_view_question);
        questionTV.setText(questions[questionNumber]);

        Button nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionNumber == 6){
                    //INTENT TO NEXT ACTIVITY
                } else {
                    if(!(Arrays.asList(answeredQuestions).contains(questionNumber))) {
                        answeredQuestions[questionNumber][0] = questionNumber;
                        questionNumber++;
                        questionTV.setText(questions[questionNumber]);
                    }
                }
            }
        });

        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionNumber > 0){
                    questionNumber--;
                    questionTV.setText(questions[questionNumber]);
                }
            }
        });
    }
}