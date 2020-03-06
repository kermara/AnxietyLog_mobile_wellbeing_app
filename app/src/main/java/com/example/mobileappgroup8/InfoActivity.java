package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        TextView tv = findViewById(R.id.info_text_tv);

        Button homeButton = findViewById(R.id.home_button_info);
        Button testButton = findViewById(R.id.test_button_info);

        tv.setText("GAD-7 (General Anxiety Disorder-7) inventory measures severity of anxiety.");

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivityIntent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(homeActivityIntent);
                overridePendingTransition(R.anim. slide_in_right, R.anim. slide_out_left);
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testActivityIntent = new Intent(InfoActivity.this, QuizActivity.class);
                startActivity(testActivityIntent);
                overridePendingTransition(R.anim. slide_in_right, R.anim. slide_out_left);
            }
        });
    }

}
