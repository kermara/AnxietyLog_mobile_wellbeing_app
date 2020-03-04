package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistoryActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        Button home = findViewById(R.id.home_button_history);
        Button analysis = findViewById(R.id.analysis_button_history);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivityIntent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(homeActivityIntent);
            }
        });

        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent analysisActivityIntent = new Intent(HistoryActivity.this, AnalysisActivity.class);
                startActivity(analysisActivityIntent);
            }
        });

    }
}
