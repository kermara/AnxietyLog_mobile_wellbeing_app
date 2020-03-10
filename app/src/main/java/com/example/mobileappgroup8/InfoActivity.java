package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_activity);

        TextView tv = findViewById(R.id.info_text_tv);
        TextView tv2 = findViewById(R.id.info_text_tv2);
        TextView tv3 = findViewById(R.id.info_text_tv3);
        TextView titleTv1 = findViewById(R.id.info_title1_tv);
        TextView titleTv2 = findViewById(R.id.info_title2_tv);
        TextView titleTv3 = findViewById(R.id.info_title3_tv);

        Button homeButton = findViewById(R.id.home_button_info);
        Button testButton = findViewById(R.id.test_button_info);

        titleTv1.setText("About Anxiety Log\n");

        tv.setText("The purpose of this questionnaire app is to track anxiety level. Tracking anxiety level can be useful for observing changes in anxiety over time." +
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
