package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Class is used for displaying information about the app
 *
 * @author Irina Konovalova
 * @version 1.1 3/2020
 */

public class InfoActivity extends MainActivity {

    private TextView tv, tv2, tv3, titleTv1, titleTv2, titleTv3;
    private Button homeButton, testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        tv = findViewById(R.id.info_text_tv);
        tv2 = findViewById(R.id.info_text_tv2);
        tv3 = findViewById(R.id.info_text_tv3);
        titleTv1 = findViewById(R.id.info_title1_tv);
        titleTv2 = findViewById(R.id.info_title2_tv);
        titleTv3 = findViewById(R.id.info_title3_tv);

        homeButton = findViewById(R.id.home_button_info);
        testButton = findViewById(R.id.test_button_info);

        titleTv1.setText("About Anxiety Log\n");

        tv.setText("The purpose of this questionnaire app is to track the user's anxiety level. Tracking anxiety level can be useful for observing changes in anxiety over time." +
                "This information may be of use for both self-monitoring purpose as well as communicating the symptoms to healthcare professionals.\n "
        );

        titleTv2.setText("GAD-7 Questionnaire\n");

        tv2.setText("The anxiety questionnaire used in this app is Generalised Anxiety Disorder (GAD-7) inventory. " +
                "GAD-7 is a 7-item inventory used by healthcare professionals to screen and measure anxiety symptoms" +
                "This tool should be used for monitoring symptom severity and it cannot replace a clinical diagnosis or " +
                "assessments.\n\n" +
                "The GAD-7 score is calculated by assigning scores of 0, 1, 2 and 3 to the response categpries of 'not at all', 'several days', " +
                "'more than half the days' and 'nearly every day', respectively. The results are categorised as follows:\n\n" +
                "0-4 -- low anxiety\n5-9 -- mild anxiety\n10-14 -- moderate anxiety\n15+ -- severe anxiety\n\n" +
                " "
        );

        titleTv3.setText("Disclaimer\n");

        tv3.setText("This app is not to be used as a diagnostic tool nor as a treatment for any medical conditions. " +
                "The content of this app should not be used as a substitute for advice of health care professionals.");


        //OnClickListeners for switching between activities with buttons
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivityIntent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(homeActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent testActivityIntent = new Intent(InfoActivity.this, QuizActivity.class);
                startActivity(testActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}
