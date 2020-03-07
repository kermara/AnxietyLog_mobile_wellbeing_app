package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Date;

public class ResultActivity extends QuizActivity {
    private DatabaseHelper myDb;
    private TextView resultView, resultInfo, resultInfoTwo;

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

        Intent quizIntent = getIntent();
        float totalPoints = quizIntent.getFloatExtra("Total points", 0);
        int totalPointsInt = (int) (totalPoints);
        resultView.setText("You got " + totalPointsInt + " points");
        

        whichAnxietyLevel(totalPointsInt);
        
        collectData();
        
        /*
        String newEntry = Float.toString(totalPoints);
        if (resultView.length() != 0) {
            AddData(newEntry, getDateObject());
        }
        */

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
    /*
    private Date getDateObject() {
        Date currentDate = new Date();
        return currentDate;
    }
    */
    
    public void collectData() {

        //String newPoints = resultView.getText().toString();
        String newPoints = Float.toString(totalPoints);

        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date currentDate = new Date();
        String newDate = format.format(currentDate);

        String newResult = "testing testing";

        if (resultView.length() != 0) {
            AddData(newPoints, newDate, newResult);
        }

    }


    private void AddData(String newEntry, Date newDate) {
        boolean insertData = myDb.insertData(newEntry, newDate);
        if (insertData == true) {
            Toast.makeText(this, "Points stored", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Points not stored", Toast.LENGTH_LONG).show();
        }
    }

    private void whichAnxietyLevel(int totalPointsForMode){
        int whichMode = 1;
        if(totalPointsForMode > 4 && totalPointsForMode < 10){
            whichMode = 2;
        } else if (totalPointsForMode > 9 && totalPointsForMode < 15){
            whichMode = 3;
        } else if (totalPointsForMode > 14){
            whichMode = 4;
        }
        switch(whichMode){
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
