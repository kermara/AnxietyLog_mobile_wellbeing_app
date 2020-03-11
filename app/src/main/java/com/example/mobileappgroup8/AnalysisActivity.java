package com.example.mobileappgroup8;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.mobileappgroup8.DatabaseHelper.DB_TABLE;
import static com.example.mobileappgroup8.HistoryActivity.EXTRA;
import static com.example.mobileappgroup8.HistoryActivity.EXTRATWO;
import static com.example.mobileappgroup8.Stats.checkEntries;
import static com.example.mobileappgroup8.Stats.entriesAverage;
import static com.example.mobileappgroup8.Stats.lastEntriesDifference;
import static com.example.mobileappgroup8.Stats.whichAnxietyLevel;

/**
 * AnalysisActivity uses the methods from Stats class to calculate and display values from the database
 * in this class.
 *
 * @author Irina Konovalova
 * @version 1.1 3/2020
 */

public class AnalysisActivity extends MainActivity {

    private DatabaseHelper db;
    private TextView averageTv, countTv, changeTv, avgDescTv, averageTitleTv, countTitleTv, changeTitleTv, avgDescTitleTv, messageTv, modeTv;
    private Button homeButton, historyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_activity);

        averageTv = findViewById(R.id.average_tv);
        countTv = findViewById(R.id.total_entries_tv);
        changeTv = findViewById(R.id.change_tv);
        avgDescTv = findViewById(R.id.avg_desc_tv);
        averageTitleTv = findViewById(R.id.average_title);
        countTitleTv = findViewById(R.id.total_entries_title);
        changeTitleTv = findViewById(R.id.change_title);
        avgDescTitleTv = findViewById(R.id.most_common_title);
        messageTv = findViewById(R.id.message_analysis);
        modeTv = findViewById(R.id.mode_description_tv);
        homeButton = findViewById(R.id.home_button_analysis);
        historyButton = findViewById(R.id.history_button_analysis);

        db = new DatabaseHelper(this);

        /*If intent is null, the values are calculated and displayed on text views. If there are less than two entries,
        the text views are set empty and the user is told to complete the test at least twice. */
        if (getIntent().getExtras() == null) {
            if (checkEntries(db) == true) {
                countTv.setText(Long.toString(DatabaseUtils.queryNumEntries(db.getDb(), DB_TABLE)));
                float changeSinceLast = lastEntriesDifference(db);

                if (changeSinceLast < 0) {
                    changeTv.setText(Float.toString(changeSinceLast));
                } else {
                    changeTv.setText("+" + changeSinceLast);
                }
                averageTv.setText(Float.toString(entriesAverage(db)));
                whichAnxietyLevel(entriesAverage(db), false, avgDescTv, modeTv, countTitleTv);
            } else {
                countTv.setText("");
                averageTv.setText("");
                avgDescTv.setText("");
                changeTv.setText("");
                countTitleTv.setText("");
                averageTitleTv.setText("");
                avgDescTitleTv.setText("");
                changeTitleTv.setText("");
                messageTv.setText("You need to complete the test at least twice\n to view analysis");
            }
        } else { /* If the intent is not null, the values from the intent are displayed in text views*/
            Bundle b = getIntent().getExtras();
            String dateFromListView = b.getString(EXTRATWO);
            String pointsFromListView = b.getString(EXTRA);
            averageTv.setText(dateFromListView);
            changeTv.setText(pointsFromListView);
            changeTitleTv.setText("Points: ");
            averageTitleTv.setText("Date: ");
            avgDescTitleTv.setText("Description");
            whichAnxietyLevel(Float.valueOf(pointsFromListView), true, avgDescTv, modeTv, countTitleTv);
            pointsFromListView = null;
            dateFromListView = null;
        }

        //OnClickListeners for switching between activities with animations by pressing the buttons.
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivityIntent = new Intent(AnalysisActivity.this, MainActivity.class);
                startActivity(homeActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyActivityIntent = new Intent(AnalysisActivity.this, HistoryActivity.class);
                startActivity(historyActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}
