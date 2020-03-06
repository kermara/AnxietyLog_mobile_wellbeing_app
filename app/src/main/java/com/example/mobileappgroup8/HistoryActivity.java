package com.example.mobileappgroup8;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class HistoryActivity extends MainActivity {

    protected DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        Button home = findViewById(R.id.home_button_history);
        Button analysis = findViewById(R.id.analysis_button_history);
        ListView listView = findViewById(R.id.listView);

        db = new DatabaseHelper(this);

        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.viewData();

        if (cursor.getCount() == 0) {
            Toast.makeText(HistoryActivity.this, "The Database was empty", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                list.add(cursor.getString(1));
                list.add(cursor.getString(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this, R.layout. adapterview_activity, list);
                listView.setAdapter(listAdapter);
            }
        }

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivityIntent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(homeActivityIntent);
                overridePendingTransition(R.anim. slide_in_right, R.anim. slide_out_left);
            }
        });

        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent analysisActivityIntent = new Intent(HistoryActivity.this, AnalysisActivity.class);
                startActivity(analysisActivityIntent);
                overridePendingTransition(R.anim. slide_in_right, R.anim. slide_out_left);
            }
        });
    }
}
