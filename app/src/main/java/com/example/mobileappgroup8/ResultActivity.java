package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends QuizActivity {
    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        resultView = findViewById(R.id.score_result);

        Button history = findViewById(R.id.history_button_result);
        Button analysis = findViewById(R.id.analysis_button_result);
        Button home = findViewById(R.id.home_button_result);

        Intent quizIntent = getIntent();
        float totalPoints = quizIntent.getFloatExtra("Total points", 0);
        int totalPointsForDisplaying = (int) (totalPoints);
        resultView.setText(Integer.toString(totalPointsForDisplaying));

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
}
