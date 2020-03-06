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

public class AnalysisActivity extends MainActivity {

    protected DatabaseHelper db;
    protected SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analysis_activity);

        TextView averageTv = findViewById(R.id.average_tv);
        TextView countTv = findViewById(R.id.total_entries_tv);
        TextView changeTv = findViewById(R.id.change_tv);
        TextView avgDescTv = findViewById(R.id.avg_desc_tv);

        Button homeButton = findViewById(R.id.home_button_analysis);
        Button historyButton = findViewById(R.id.history_button_analysis);

        db = new DatabaseHelper(this);
        database = db.getWritableDatabase();
        String change = "SELECT(" + KEY_POINTS + ") FROM " + DB_TABLE;
        Cursor cursorLast = database.rawQuery(change, null);
        cursorLast.moveToLast();

        if (cursorLast.getCount() > 1) {
            countTv.setText(Long.toString(DatabaseUtils.queryNumEntries(database, DB_TABLE)));
            float lastEntry = Float.valueOf(cursorLast.getString(0));
            cursorLast.moveToPosition(cursorLast.getCount() - 2);
            float secondLastEntry = Float.valueOf(cursorLast.getString(0));
            //float changeSinceLast = Math.round((lastEntry - secondLastEntry)/secondLastEntry * 100 * 100)/100.0f;
            float changeSinceLast = lastEntry - secondLastEntry;

            if (changeSinceLast < 0) {
                changeTv.setText(Float.toString(changeSinceLast));
            } else {
                changeTv.setText("+" + changeSinceLast);
            }
            String average = "SELECT AVG(" + KEY_POINTS + ") FROM " + DB_TABLE;
            Cursor cursorAvg = database.rawQuery(average, null);
            cursorAvg.moveToFirst();
            float averagePoints = Math.round(Float.valueOf(cursorAvg.getString(0)) * 100) / 100.0f;
            averageTv.setText(cursorAvg.getString(0));

            if (averagePoints <= 4) {
                avgDescTv.setText("No anxiety");
            } else if (averagePoints >= 5 && averagePoints <= 9) {
                avgDescTv.setText("Mild anxiety");
            } else if (averagePoints >= 10 && averagePoints <= 14) {
                avgDescTv.setText("Moderate anxiety");
            } else if (averagePoints > 15) {
                avgDescTv.setText("Severe anxiety");
            }
        } else {
            countTv.setText("");
            averageTv.setText("");
            avgDescTv.setText("");
            changeTv.setText("");
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


}



