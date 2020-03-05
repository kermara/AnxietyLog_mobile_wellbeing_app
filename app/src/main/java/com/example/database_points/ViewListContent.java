package com.example.database_points;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * created by Kerttuli 5.3.2020
 * To view the database content
 */

public class ViewListContent extends AppCompatActivity {

    DatabaseHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        ListView listView = (ListView) findViewById(R.id.listView);
        db = new DatabaseHelper(this);

        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = db.viewData();

        if(cursor.getCount() == 0){
            Toast.makeText(ViewListContent.this,"The Database was empty", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()){
                list.add(cursor.getString(1));
                list.add(cursor.getString(2));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list); //TÄHÄN PITÄISI SAADA lAYOUT
                listView.setAdapter(listAdapter);
            }
        }




    }



}
