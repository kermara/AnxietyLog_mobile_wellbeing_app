package com.example.mobileappgroup8;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The class is used to display information and store it in the SQL-database based on the point result received in the QuizActivity.
 *
 * @author Pauli Vuolle-Apiala, Kerttuli Ratilainen
 * @Version 1.1 3/2020
 */
public class ResultActivity extends QuizActivity {
    private DatabaseHelper myDb;
    private TextView resultView, resultInfo, resultInfoTwo;
    private float totalPoints;
    private int totalPointsInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        resultView = findViewById(R.id.score_result);
        resultInfo = findViewById(R.id.info_result);
        resultInfoTwo = findViewById(R.id.desc_result);

        Button history = findViewById(R.id.history_button_result);
        Button analysis = findViewById(R.id.analysis_button_result);
        Button home = findViewById(R.id.home_button_result);

        myDb = new DatabaseHelper(this);
        //The intent is received from the QuizActivity and the points are used to show information in the TextViews.
        Intent quizIntent = getIntent();
        totalPoints = quizIntent.getFloatExtra("Total points", 0);
        //The float is converted to an Int to show the number in the textView without any decimals.
        totalPointsInt = (int) (totalPoints);
        resultView.setText("Your anxiety score is " + totalPointsInt);

        //whichAnxietyLevel-method is used to display the correct message in the TextView.
        whichAnxietyLevel(totalPoints);

        collectData();

        //OnclickListeners to switch between the activities with animations by pressing the buttons.
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(ResultActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent analysisActivityIntent = new Intent(ResultActivity.this, AnalysisActivity.class);
                startActivity(analysisActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doneActivityIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(doneActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    public void collectData() {
        String newPoints = Float.toString(totalPoints);
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date currentDate = new Date();
        String newDate = format.format(currentDate);

        String newResult = "";

        if (resultView.length() != 0) {
            AddData(newPoints, newDate, newResult);
        }
    }


    public void AddData(String newPoints, String newDate, String newResult) {
        boolean insertData = myDb.insertData(newPoints, newDate, newResult);
        if (insertData == true) {
            Toast.makeText(this, "Points added to history", Toast.LENGTH_LONG).show();
            Log.d("addData", "on tallentanut");
        } else {
            Toast.makeText(this, "Points not added", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * The method is used to set the right messages to the textViews based on the points the user received in the ResultActivity.
     *
     * @param totalPointsForMode totalPointsForMode is used to find out which case the totalPoints fall under and which text to set to the textViews.
     */
    protected void whichAnxietyLevel(float totalPointsForMode) {
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
                resultInfo.setText("Low anxiety");
                break;
            case 2:
                resultInfo.setText("Mild anxiety");
                resultInfoTwo.setText("Monitor");
                break;
            case 3:
                resultInfo.setText("Moderate anxiety");
                resultInfoTwo.setText("Possible clinically significant condition");
                break;
            case 4:
                resultInfo.setText("Severe anxiety");
                resultInfoTwo.setText("Active treatment probably warranted");
                break;
        }
    }
}