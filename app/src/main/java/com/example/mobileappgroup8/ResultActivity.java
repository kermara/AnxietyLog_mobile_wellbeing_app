package com.example.mobileappgroup8;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ResultActivity extends QuizActivity {
    private TextView resultView;
    protected ArrayList testDates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        resultView = findViewById(R.id.score_result);

        Button history = findViewById(R.id.history_button_result);
        Button analysis = findViewById(R.id.analysis_button_result);
        Button home = findViewById(R.id.home_button_result);
        TextView tv = findViewById(R.id.shared_pref);
        testDates = new ArrayList<String>();

        Intent quizIntent = getIntent();
        float totalPoints = quizIntent.getFloatExtra("Total points", 0);
        int totalPointsForDisplaying = (int) (totalPoints);
        resultView.setText(Integer.toString(totalPointsForDisplaying));

        SharedPreferences prefGet = getSharedPreferences("Saved Points", MODE_PRIVATE);
        Map<String, ?> all = prefGet.getAll();

        SharedPreferences prefSave = getSharedPreferences("Saved Points", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefSave.edit();
        //this.getSharedPreferences("Saved Points", 0).edit().clear().commit();

        prefEditor.putInt(getDate(), totalPointsForDisplaying);
        testDates.add(getDate());
        prefEditor.commit();



        /*for(Map.Entry<String, ?> entry : all.entrySet()){
            tv.setText(entry.getKey() + ": " + entry.getValue().toString());
        }*/


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(ResultActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
                overridePendingTransition(R.anim. slide_in_right, R.anim. slide_out_left);
            }
        });

        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent analysisActivityIntent = new Intent(ResultActivity.this, AnalysisActivity.class);
                startActivity(analysisActivityIntent);
                overridePendingTransition(R.anim. slide_in_right, R.anim. slide_out_left);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doneActivityIntent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(doneActivityIntent);
                overridePendingTransition(R.anim. slide_in_right, R.anim. slide_out_left);
            }
        });

    }

    protected String getDate() {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
        Date currentDate = new Date();
        return format.format(currentDate);
    }
}
