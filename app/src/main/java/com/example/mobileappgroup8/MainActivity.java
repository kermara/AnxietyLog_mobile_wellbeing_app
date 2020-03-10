package com.example.mobileappgroup8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Class is used for navigating to other activities through buttons.
 * @author Irina Konovalova
 * @version 1.1 3/2020
 */

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_activity);

        Button startTest = findViewById(R.id.start_test_button);
        Button history = findViewById(R.id.history_button);
        Button analysis = findViewById(R.id.analysis_button);
        Button info = findViewById(R.id.info_button);


        //OnClickListeners for switching between activities with animations by pressing the buttons.
        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizActivityIntent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(quizActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent analysisActivityIntent = new Intent(MainActivity.this, AnalysisActivity.class);
                startActivity(analysisActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoActivityIntent = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(infoActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}
