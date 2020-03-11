package com.example.mobileappgroup8;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import static com.example.mobileappgroup8.Quiz.pointTotal;
import static com.example.mobileappgroup8.Quiz.progressBarAnimation;
import static com.example.mobileappgroup8.Quiz.questionNumberChanges;
import static com.example.mobileappgroup8.Quiz.questionViewUpdate;
import static com.example.mobileappgroup8.Quiz.storePoints;
import static com.example.mobileappgroup8.Quiz.whichRadioButtonIsTicked;

/**
 * QuizActivity shows 7 different questions in the quiz_activity. The results are stored and deleted in a 2d matrix based on the
 * RadioButton choices and the clicks of next/back buttons. The point result is sent to ResultActivity with an Intent.
 * The methods used in QuizActivity are static methods from the Quiz class.
 *
 * @author Pauli Vuolle-Apiala
 * @version 1.1 3/2020
 */
public class QuizActivity extends MainActivity {

    private String[] questions = {"Feeling nervous, anxious or on edge?", "Not being able to stop or control worrying?", "Worrying too much about different things?", "Trouble relaxing?",
            "Being so restless that it is hard to sit still?", "Becoming easily annoyed or irritable?", "Feeling afraid as if something awful might happen?"};
    private int[][] answeredQuestions = new int[2][7];
    private int questionNumber = 1;
    private RadioButton rb1, rb2, rb3, rb4;
    private RadioGroup rg1;
    private TextView questionTV, errorView, numberView;
    private Button nextButton, backButton, homeButton;
    private Animation slideIn, slideOut, slideInBackwards, slideOutBackwards, rgSlideIn, rgSlideOut, rgSlideInBackwards, rgSlideOutBackwards;
    private ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        rg1 = findViewById(R.id.radio_group1);
        rb1 = findViewById(R.id.radio_button_1);
        rb2 = findViewById(R.id.radio_button_2);
        rb3 = findViewById(R.id.radio_button_3);
        rb4 = findViewById(R.id.radio_button_4);
        questionTV = findViewById(R.id.text_view_question);
        numberView = findViewById(R.id.question_number_view);
        errorView = findViewById(R.id.error_textView);
        nextButton = findViewById(R.id.next_button);
        backButton = findViewById(R.id.back_button);
        homeButton = findViewById(R.id.home_button_quiz);
        pBar = findViewById(R.id.progress_bar);

        //Animations for switching between the activities.
        slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        slideInBackwards = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        slideOutBackwards = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
        rgSlideIn = AnimationUtils.loadAnimation(this, R.anim.rg_slide_in_right);
        rgSlideOut = AnimationUtils.loadAnimation(this, R.anim.rg_slide_out_left);
        rgSlideInBackwards = AnimationUtils.loadAnimation(this, R.anim.rg_slide_in_left);
        rgSlideOutBackwards = AnimationUtils.loadAnimation(this, R.anim.rg_slide_out_right);

        //The quiz begins
        questionTV.setText(questions[(questionNumber - 1)]);
        numberView.setText(questionNumber + "/7");
        pBar.setMax(12000);
        //Next button onClickListener
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg1.getCheckedRadioButtonId() == -1) {
                    errorView.setText("You have to select one of the choices");
                } else {
                    //the storePoints method checks which radiobutton is ticked and stores the points on the second row of the matrix
                    storePoints(questionNumber, answeredQuestions, whichRadioButtonIsTicked(rb2, rb3, rb4));
                    //if questionNumber is 7 the quiz ends and the point result is sent to ResultActivity with an intent.
                    if (questionNumber == 7) {
                        Intent nextActivity = new Intent(QuizActivity.this, ResultActivity.class);
                        nextActivity.putExtra("Total points", pointTotal(answeredQuestions));
                        startActivity(nextActivity);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return;
                    } else {
                        //if the questionNumber is not 7 the next question is displayed
                        nextQuestion();
                    }
                }
            }
        });
        //OnClickListeners for the back and home buttons.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if questionNumber is greater than 1 the back button click shows the previous question
                if (questionNumber > 1) {
                    previousQuestion();
                }
            }
        });
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AlertDialogListener that asks the "Exit the test?" when the home button is pressed
                AlertDialog.Builder quizAlert = new AlertDialog.Builder(QuizActivity.this);
                DialogInterface.OnClickListener quizDialogListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int option) {
                        if (option == -1) {
                            //if the Yes button in the Alert Dialog is pressed the user is taken back to the Main Activity(starting activity)
                            Intent nextActivity = new Intent(QuizActivity.this, MainActivity.class);
                            startActivity(nextActivity);
                        }
                    }
                };
                quizAlert.setMessage("Exit the test?").setPositiveButton("Yes", quizDialogListener).setNegativeButton("No", quizDialogListener);
                quizAlert.show();
            }
        });
    }

    //A method where multiple static methods from Quiz-class are grouped and used together.
    //The booleans for methods are set "true" for the nextQuestion value, so that the correct messages and animations are displayed and played.
    private void nextQuestion() {
        questionNumber = questionNumberChanges(questionNumber, true, backButton);
        startAnimations(true, questionTV, rg1);
        progressBarAnimation(pBar, true);
        questionViewUpdate(answeredQuestions, questions, questionNumber, errorView, questionTV, numberView, rg1);
    }

    //A method where multiple static methods from Quiz-class are grouped and used together.
    //The booleans for methods are set "false" for the previousQuestion value, so that the correct messages and animations are displayed and played.
    private void previousQuestion() {
        questionNumber = questionNumberChanges(questionNumber, false, backButton);
        startAnimations(false, questionTV, rg1);
        progressBarAnimation(pBar, false);
        questionViewUpdate(answeredQuestions, questions, questionNumber, errorView, questionTV, numberView, rg1);
    }

    //A method where animations are grouped and played together. The nextQuestion boolean determines if the animations are played forwards or backwards.
    private void startAnimations(boolean nextQuestion, TextView tv, RadioGroup rg) {
        if (nextQuestion == true) {
            tv.startAnimation(slideOut);
            tv.startAnimation(slideIn);
            rg.startAnimation(rgSlideOut);
            rg.startAnimation(rgSlideIn);
        } else {
            tv.startAnimation(slideOutBackwards);
            tv.startAnimation(slideInBackwards);
            rg.startAnimation(rgSlideOutBackwards);
            rg.startAnimation(rgSlideInBackwards);
        }
    }
}