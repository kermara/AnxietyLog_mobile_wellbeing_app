package com.example.mobileappgroup8;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Quiz class contains static methods used in QuizActivity for storing, changing and displaying
 * values. The Quiz class is used to keep track of the point result of Quiz Activity.
 *
 * @author Pauli Vuolle-Apiala
 * @version 1.1 3/2020
 */
public class Quiz {

    /**
     * This method returns an integer with a value between 1 - 4 depending on
     * which of the 4 radio buttons is ticked. 1 for radio button 1, 2 for radio button 2 etc.
     *
     * @param rb2 RadioButton passed as a parameter
     * @param rb3 RadioButton passed as a parameter
     * @param rb4 RadioButton passed as a parameter
     * @return an integer between 1 - 4 depending on which radio button is ticked
     */
    public static int whichRadioButtonIsTicked(RadioButton rb2, RadioButton rb3, RadioButton rb4) {
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

    /**
     * This method is used for storing points in the matrix. The top row of the matrix is used for storing the numbers 1 - 7,
     * each representing a question in the
     *
     * @param questionNumber
     * @param answeredQuestions
     * @param radioButtonChoice
     */
    public static void storePoints(int questionNumber, int[][] answeredQuestions, int radioButtonChoice) {
        switch (radioButtonChoice) {
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
    }

    public static float pointTotal(int[][] matrix) {
        float pointsTotal = 0f;
        for (int i = 0; i < 7; i++) {
            pointsTotal += matrix[1][i];
        }
        return pointsTotal;
    }

    public static void progressBarAnimation(ProgressBar pBar, boolean nextQuestion) {
        if (nextQuestion == true) {
            ObjectAnimator.ofInt(pBar, "progress", (pBar.getProgress() + 2000)).setDuration(100).start();
        } else {
            ObjectAnimator.ofInt(pBar, "progress", (pBar.getProgress() - 2000)).setDuration(100).start();
        }
    }

    public static void questionViewUpdate(int answeredQuestions[][], String[] questions, int questionNumber, TextView a, TextView b, TextView c, RadioGroup rg1) {
        answeredQuestions[1][(questionNumber - 1)] = 0;
        a.setText(null);
        b.setText(questions[(questionNumber - 1)]);
        c.setText(questionNumber + "/7");
        rg1.clearCheck();
    }

    public static int questionNumberChanges(int questionNumber, boolean nextQuestion, TextView a) {
        if (nextQuestion == true) {
            questionNumber++;
        } else {
            questionNumber--;
        }
        if (questionNumber > 1) {
            a.setVisibility(View.VISIBLE);
        } else {
            a.setVisibility(View.INVISIBLE);
        }
        return questionNumber;
    }

    /**
     * This method takes two textviews and a float as a parameter and displays text that correspond to certain
     * point limits on those text views.
     *
     * @param totalPointsForMode Score result that the ResultActivity receives as an intent from the QuizActivity
     * @param a                  One of the TextViews that the text is displayed on
     * @param b                  One of the TextViews that the text is displayed on
     */
    public static void whichAnxietyLevel(float totalPointsForMode, TextView a, TextView b) {
        int whichMode = 1;
        if (totalPointsForMode > 4 && totalPointsForMode < 10) {
            whichMode = 2;
        } else if (totalPointsForMode > 9 && totalPointsForMode < 15) {
            whichMode = 3;
        } else if (totalPointsForMode > 14) {
            whichMode = 4;
        }
        switch (whichMode) {
            case 1:
                a.setText("Low anxiety");
                break;
            case 2:
                a.setText("Mild anxiety");
                b.setText("Monitor");
                break;
            case 3:
                a.setText("Moderate anxiety");
                b.setText("Possible clinically significant condition");
                break;
            case 4:
                a.setText("Severe anxiety");
                b.setText("Active treatment probably warranted");
                break;
        }
    }
}
