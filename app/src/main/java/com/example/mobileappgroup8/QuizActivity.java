package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends MainActivity {

    protected String[] questions = {"Feeling nervous, anxious or on edge?", "Not being able to stop or control worrying?", "Worrying too much about different things?", "Trouble relaxing?",
            "Being so restless that it is hard to sit still?", "Becoming easily annoyed or irritable?", "Feeling afraid as if something awful might happen?"};
    protected int[][] answeredQuestions = new int[2][7];
    protected int questionNumber = 1;
    protected RadioButton rb1, rb2, rb3, rb4;
    protected RadioGroup rg1;
    protected TextView questionTV, errorView, questionNumberView;
    protected Button nextButton, backButton;
    protected Animation slideIn, slideOut, slideInBackwards, slideOutBackwards, rgSlideIn, rgSlideOut, rgSlideInBackwards, rgSlideOutBackwards;

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
        errorView = findViewById(R.id.error_textView);
        questionNumberView = findViewById(R.id.text_view_question_number);
        nextButton = findViewById(R.id.next_button);
        backButton = findViewById(R.id.back_button);

        slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        slideInBackwards = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        slideOutBackwards = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);
        rgSlideIn = AnimationUtils.loadAnimation(this, R.anim.rg_slide_in_right);
        rgSlideOut = AnimationUtils.loadAnimation(this, R.anim.rg_slide_out_left);
        rgSlideInBackwards = AnimationUtils.loadAnimation(this, R.anim.rg_slide_in_left);
        rgSlideOutBackwards = AnimationUtils.loadAnimation(this, R.anim.rg_slide_out_right);

        questionTV.setText(questions[(questionNumber - 1)]);
        questionNumberView.setText("Question " + questionNumber + "/7");

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg1.getCheckedRadioButtonId() == -1) {
                    errorView.setText("You have to select one of the choices");
                } else {
                    switch (whichRadioButtonIsTicked()) {
                        case 1:
                            answeredQuestions[1][(questionNumber - 1)] = 0;
                            answeredQuestions[0][(questionNumber - 1)] = questionNumber;
                            break;
                        case 2:
                            answeredQuestions[1][(questionNumber - 1)] = 1;
                            answeredQuestions[0][(questionNumber - 1)] = questionNumber;
                            break;
                        case 3:
                            answeredQuestions[1][(questionNumber - 1)] = 2;
                            answeredQuestions[0][(questionNumber - 1)] = questionNumber;
                            break;
                        case 4:
                            answeredQuestions[1][(questionNumber - 1)] = 3;
                            answeredQuestions[0][(questionNumber - 1)] = questionNumber;
                            break;
                    }
                    if (questionNumber == 7) {
                        Intent nextActivity = new Intent(QuizActivity.this, ResultActivity.class);
                        nextActivity.putExtra("Total points", pointTotal(answeredQuestions));
                        startActivity(nextActivity);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        finish();
                        return;
                    } else {
                        nextQuestion();
                    }
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (questionNumber > 1) {
                    previousQuestion();
                    if (questionNumber != 1) {
                        backButton.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    protected void nextQuestion() {
        questionNumber++;
        if (questionNumber != 1) {
            backButton.setVisibility(View.VISIBLE);
        }
        startAnimations(true);
        errorView.setText("");
        questionTV.setText(questions[(questionNumber - 1)]);
        questionNumberView.setText("Question " + questionNumber + "/7");
        rg1.clearCheck();
    }

    protected void previousQuestion() {
        questionNumber--;
        if (questionNumber == 1) {
            backButton.setVisibility(View.INVISIBLE);
        }
        startAnimations(false);
        answeredQuestions[1][(questionNumber - 1)] = 0;
        errorView.setText("");
        questionTV.setText(questions[(questionNumber - 1)]);
        questionNumberView.setText("Question " + questionNumber + "/7");
        rg1.clearCheck();
    }

    protected int whichRadioButtonIsTicked() {
        int buttonSelection = 1;
        if (rb2.isChecked() == true) {
            buttonSelection = 2;
        } else if (rb3.isChecked() == true) {
            buttonSelection = 3;
        } else if (rb4.isChecked() == true) {
            buttonSelection = 4;
        }
        return buttonSelection;
    }

    protected float pointTotal(int[][] matrix) {
        float pointsTotal = 0f;
        for (int i = 0; i < 7; i++) {
            pointsTotal += matrix[1][i];
        }
        return pointsTotal;
    }

    protected void startAnimations(boolean nextQuestion) {
        if (nextQuestion == true) {
            questionTV.startAnimation(slideOut);
            questionTV.startAnimation(slideIn);
            rg1.startAnimation(rgSlideOut);
            rg1.startAnimation(rgSlideIn);
        } else {
            questionTV.startAnimation(slideOutBackwards);
            questionTV.startAnimation(slideInBackwards);
            rg1.startAnimation(rgSlideOutBackwards);
            rg1.startAnimation(rgSlideInBackwards);
        }
    }
}