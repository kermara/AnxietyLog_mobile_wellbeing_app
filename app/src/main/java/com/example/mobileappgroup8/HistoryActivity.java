package com.example.mobileappgroup8;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.mobileappgroup8.DatabaseHelper.DB_TABLE;

public class HistoryActivity extends MainActivity {

    private DatabaseHelper db;
    private SQLiteDatabase databaseToDelete;
    private ListView pointsListView;
    private Button home, analysis, delete;
    private TextView databaseMessageView;
    private Points points;
    private List<Points> pointsList;
    protected static final String EXTRA = "com.example.mobileappgroup8.MESSAGE";
    protected static final String EXTRATWO = "com.example.mobileappgroup8.MESSAGE2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        home = findViewById(R.id.home_button_history);
        analysis = findViewById(R.id.analysis_button_history);
        delete = findViewById(R.id.delete_button_history);
        pointsListView = findViewById(R.id.listView_history);
        databaseMessageView = findViewById(R.id.database_view);
        pointsListView = findViewById(R.id.listView_history);

        db = new DatabaseHelper(this);
        databaseToDelete = db.getWritableDatabase();

        pointsList = new ArrayList<>();
        final Cursor cursor = db.viewData();

        while (cursor.moveToNext()) {
            points = new Points(cursor.getString(1), cursor.getString(2), cursor.getString(3));
            pointsList.add(points);
            ListAdapter listAdapter = new com.example.mobileappgroup8.ListAdapter(this, R.layout.adapterview_activity, (ArrayList<Points>) pointsList);
            pointsListView.setAdapter(listAdapter);
        }
        if (cursor.getCount() != 0) {
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder DatabaseAlert = new AlertDialog.Builder(HistoryActivity.this);
                    DialogInterface.OnClickListener historyDialog = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int option) {
                            if (option == -1) {
                                databaseToDelete.execSQL("delete from " + DB_TABLE);
                                finish();
                                overridePendingTransition(0, 0);
                                startActivity(getIntent());
                                overridePendingTransition(0, 0);
                            }
                        }
                    };
                    DatabaseAlert.setMessage("Clear the database?").setPositiveButton("Yes", historyDialog).setNegativeButton("No", historyDialog);
                    DatabaseAlert.show();
                }
            });
        } else {
            databaseMessageView.setText("No results saved");
        }
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeActivityIntent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(homeActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent analysisActivityIntent = new Intent(HistoryActivity.this, AnalysisActivity.class);
                startActivity(analysisActivityIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        pointsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("TAG", "onItemClick(" + i + ")");
                Intent intent = new Intent(HistoryActivity.this,AnalysisActivity.class);
                String pointsFromListView = ((TextView)view.findViewById(R.id.tvPoints)).getText().toString();
                String dateFromListView = ((TextView)view.findViewById(R.id.tvDate)).getText().toString();
                intent.putExtra(EXTRATWO, dateFromListView);
                intent.putExtra(EXTRA, pointsFromListView);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        Collections.reverse(pointsList);
    }
}

