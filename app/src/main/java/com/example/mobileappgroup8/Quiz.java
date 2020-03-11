package com.example.mobileappgroup8;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.Button;
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
     * @param questionNumber the number of the current question in the quiz
     * @param answeredQuestions the matrix scores and questionNumbers are stored in, scores are on the bottom row
     * @param radioButtonChoice which radio button was ticked as an integer
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

    /**
     * Calculates the sum of the elements on the bottom row of a 2 row matrix.
     * Calculates the score result of the QuizActivity.
     *
     * @param matrix matrix that has the question numbers on the top row and the score results on the bottom row
     * @return total sum of the elements on the bottom row of the matrix
     */
    public static float pointTotal(int[][] matrix) {
        float pointsTotal = 0f;
        for (int i = 0; i < 7; i++) {
            pointsTotal += matrix[1][i];
        }
        return pointsTotal;
    }

    /**
     * Gives the progress bar a "smooth" animation of slowly progressing.
     *
     * @param pBar         the progress bar the animation is wanted on
     * @param nextQuestion Boolean that determines if the progress bar progress increases or decreases
     */
    public static void progressBarAnimation(ProgressBar pBar, boolean nextQuestion) {
        if (nextQuestion == true) {
            ObjectAnimator.ofInt(pBar, "progress", (pBar.getProgress() + 2000)).setDuration(100).start();
        } else {
            ObjectAnimator.ofInt(pBar, "progress", (pBar.getProgress() - 2000)).setDuration(100).start();
        }
    }

    /**
     * Updates the textviews, sets the bottom row of the matrix on the current question to a zero and resets the radio buttons.
     *
     * @param answeredQuestions Matrix that stores the points of the quiz
     * @param questions         String array that contains the questions
     * @param questionNumber    Number of the current question(1 - 7)
     * @param a                 textView that is set to a null on method call
     * @param b                 the quiz question is displayed on this textView
     * @param c                 the number of the current question is displayed on this textView
     * @param rg1               RadioButtons are cleared of any selection
     */
    public static void questionViewUpdate(int answeredQuestions[][], String[] questions, int questionNumber, TextView a, TextView b, TextView c, RadioGroup rg1) {
        answeredQuestions[1][(questionNumber - 1)] = 0;
        a.setText(null);
        b.setText(questions[(questionNumber - 1)]);
        c.setText(questionNumber + "/7");
        rg1.clearCheck();
    }

    /**
     * This method increments and decrements the questionNumber variable that keeps track of the current question the quiz is on.
     * This method also hides the backButton on the first question.
     *
     * @param questionNumber the current question that the quiz is on
     * @param nextQuestion   this boolean determines if the quiz is going forward or backward
     * @param b              Button that the method sets invisible if the questionNumber is 1 (quiz is on the first question)
     * @return questionNumber after incrementing or decrementing
     */
    public static int questionNumberChanges(int questionNumber, boolean nextQuestion, Button b) {
        if (nextQuestion == true) {
            questionNumber++;
        } else {
            questionNumber--;
        }
        if (questionNumber > 1) {
            b.setVisibility(View.VISIBLE);
        } else {
            b.setVisibility(View.INVISIBLE);
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
