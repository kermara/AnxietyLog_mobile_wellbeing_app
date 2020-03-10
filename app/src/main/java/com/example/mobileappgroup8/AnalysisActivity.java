package com.example.mobileappgroup8;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.mobileappgroup8.DatabaseHelper.DB_TABLE;
import static com.example.mobileappgroup8.DatabaseHelper.KEY_POINTS;
import static com.example.mobileappgroup8.HistoryActivity.EXTRA;
import static com.example.mobileappgroup8.HistoryActivity.EXTRATWO;

public class AnalysisActivity extends MainActivity {

    private DatabaseHelper db;
    private SQLiteDatabase database;
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
        database = db.getWritableDatabase();
        String change = "SELECT(" + KEY_POINTS + ") FROM " + DB_TABLE;
        Cursor cursorLast = database.rawQuery(change, null);
        cursorLast.moveToLast();

        if (getIntent().getExtras() == null) {
            if (cursorLast.getCount() > 1) {
                countTv.setText(Long.toString(DatabaseUtils.queryNumEntries(database, DB_TABLE)));
                float lastEntry = Float.valueOf(cursorLast.getString(0));
                cursorLast.moveToPosition(cursorLast.getCount() - 2);
                float secondLastEntry = Float.valueOf(cursorLast.getString(0));
                float changeSinceLast = lastEntry - secondLastEntry;

                if (changeSinceLast < 0) {
                    changeTv.setText(Float.toString(changeSinceLast));
                } else {
                    changeTv.setText("+" + changeSinceLast);
                }
                String average = "SELECT AVG(" + KEY_POINTS + ") FROM " + DB_TABLE;
                Cursor cursorAvg = database.rawQuery(average, null);
                cursorAvg.moveToFirst();
                String averagePointsString = String.format("%.2f", Float.valueOf(cursorAvg.getString(0)));
                Float averagePoints = Float.valueOf(averagePointsString);
                averageTv.setText(averagePointsString);
                whichAnxietyLevel(averagePoints, false);
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
        } else {
            Bundle b = getIntent().getExtras();
            String dateFromListView = b.getString(EXTRATWO);
            String pointsFromListView = b.getString(EXTRA);
            changeTitleTv.setText("Points: ");
            averageTv.setText(dateFromListView);
            averageTitleTv.setText("Date: ");
            changeTv.setText(pointsFromListView);
            avgDescTitleTv.setText("Description");
            whichAnxietyLevel(Float.valueOf(pointsFromListView), true);
            pointsFromListView = null;
            dateFromListView = null;
        }

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

    private void whichAnxietyLevel(float points, boolean ThroughHistoryActivity) {
        if (ThroughHistoryActivity == true) {
            countTitleTv.setText(null);
            if (points <= 4) {
                avgDescTv.setText("No anxiety");
            } else if (points >= 5 && points <= 9) {
                avgDescTv.setText("Mild anxiety");
                modeTv.setText("                     Monitor");
            } else if (points >= 10 && points < 15) {
                modeTv.setText("            Moderate anxiety");

            } else if (points > 15) {
                avgDescTv.setText("Severe anxiety");
                modeTv.setText("Active treatment probably warranted");
            }
        } else {
            if (points <= 4) {
                avgDescTv.setText("No anxiety");
            } else if (points >= 5 && points <= 9) {
                avgDescTv.setText("Mild anxiety");
            } else if (points >= 10 && points < 15) {
                avgDescTv.setText("Moderate anxiety");
            } else if (points > 15) {
                avgDescTv.setText("Severe anxiety");
            }
        }
    }
}
