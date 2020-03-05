package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends QuizActivity {
    protected TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        resultView = findViewById(R.id.score_result);

        Intent lastActivityIntent = getIntent();
        float totalPoints = lastActivityIntent.getIntExtra("Total points", 0);

        resultView.setText(Float.toString(totalPoints));
    }
}
