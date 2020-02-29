package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends SecondActivity {

    private String[] questions = {"Feeling nervous, anxious, or on edge", "kysymys 2", "kysymys 3", "kysymys 4", "kysymys 5 ", "kysymys 6", "kysymys 7"};
    private int[][] answeredQuestions = new int[2][7];
    private int questionNumber = 1;
    private RadioButton rb1, rb2, rb3, rb4;
    private RadioGroup rg1;
    private TextView questionTV, errorView;
    private Button nextButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        rg1 = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button_1);
        rb2 = findViewById(R.id.radio_button_2);
        rb3 = findViewById(R.id.radio_button_3);
        rb4 = findViewById(R.id.radio_button_4);
        questionTV = findViewById(R.id.text_view_question);
        errorView = findViewById(R.id.error_textView);
        nextButton = findViewById(R.id.next_button);
        backButton = findViewById(R.id.back_button);

        questionTV.setText(questions[(questionNumber - 1)]);
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
                            answeredQuestions[0][questionNumber] = questionNumber;
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
                }
            }
        });
    }

    protected void nextQuestion() {
        questionNumber++;
        errorView.setText("");
        questionTV.setText(questions[(questionNumber - 1)]);
        rg1.clearCheck();
    }

    protected void previousQuestion() {
        questionNumber--;
        answeredQuestions[1][(questionNumber - 1)] = 0;
        errorView.setText("");
        questionTV.setText(questions[(questionNumber - 1)]);
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

    protected int pointTotal(int[][] matrix) {
        int pointsTotal = 0;
        for (int i = 0; i < 7; i++) {
            pointsTotal += matrix[1][i];
        }
        return pointsTotal;
    }
}