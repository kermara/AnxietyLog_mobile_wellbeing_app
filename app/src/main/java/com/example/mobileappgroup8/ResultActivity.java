package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.mobileappgroup8.Quiz.whichAnxietyLevel;

/**
 * Manages quiz results and feeds them to history and analysis
 *
 * @author Irina, Pauli and Kerttuli
 * created on 10.3.2020
 */

public class ResultActivity extends QuizActivity {
    private DatabaseHelper myDb;
    private TextView resultView, resultInfo, resultInfoTwo;
    private float totalPoints;
    private int totalPointsInt;
    private Points pointsdb = new Points();
    private Button history, analysis, home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        resultView = findViewById(R.id.score_result);
        resultInfo = findViewById(R.id.info_result);
        resultInfoTwo = findViewById(R.id.desc_result);
        history = findViewById(R.id.history_button_result);
        analysis = findViewById(R.id.analysis_button_result);
        home = findViewById(R.id.home_button_result);

        myDb = new DatabaseHelper(this);
        /*The score result from QuizActivity is received as an intent
        and the correct texts for the score amount are displayed on the screen with the WhichAnxietyLevel-method*/
        Intent quizIntent = getIntent();
        totalPoints = quizIntent.getFloatExtra("Total points", 0);
        totalPointsInt = (int) (totalPoints);
        resultView.setText("Your anxiety score is " + totalPointsInt);
        whichAnxietyLevel(totalPoints, resultInfo, resultInfoTwo);

        /**
         * @param Points object
         * @ feeds data for Points Object, needs data for ListView
         */
        pointsdb = new Points();
        String PointsResult = Float.toString(totalPoints);
        //String resultAdded = pointsdb.setResult(PointsResult);
        String newPoints = pointsdb.getNewPoints(PointsResult);
        String newDate = pointsdb.getNewDate();
        //String newResult = pointsdb.getNewResult(resultAdded);
        String newResult = "";
        if (resultView.length() != 0) {
            AddData(newPoints, newDate, newResult);
        }
        //OnClickListeners, animations and intents for the history, analysis and home buttons and switching to each of the activities
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

    /**
     * @param newPoints
     * @param newDate
     * @param newResult
     * @author Kerttuli
     * @result sets data to database
     */

    public void AddData(String newPoints, String newDate, String newResult) {
        boolean insertData = myDb.insertData(newPoints, newDate, newResult);
        if (insertData == true) {
            Toast.makeText(this, "Points added to history", Toast.LENGTH_LONG).show();
            Log.d("addData", "on tallentanut");
        } else {
            Toast.makeText(this, "Points not added", Toast.LENGTH_LONG).show();
        }
    }
}