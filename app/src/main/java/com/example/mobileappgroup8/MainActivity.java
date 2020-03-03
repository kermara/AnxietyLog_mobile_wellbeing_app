package com.example.mobileappgroup8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_activity);

        Button startTest = (Button) findViewById(R.id.start_test_button);
        Button history = (Button) findViewById(R.id.history_button);
        Button analysis = (Button) findViewById(R.id.analysis_button);
        Button info = (Button) findViewById(R.id.info_button);

        startTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizActivityIntent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(quizActivityIntent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
            }
        });

        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent analysisActivityIntent = new Intent(MainActivity.this, AnalysisActivity.class);
                startActivity(analysisActivityIntent);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoActivityIntent = new Intent(MainActivity.this, AnalysisActivity.class);
                startActivity(infoActivityIntent);
            }
        });
    }
}
