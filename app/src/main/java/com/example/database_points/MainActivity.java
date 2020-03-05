package com.example.database_points;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * created by Kerttuli 5.3.2020
 * To manage the database
 */

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    DatabaseHelper myDb;
    Button btnAdd,btnView;
    //TextView textView;
    EditText editText;
    Button btnDelete;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: Started");

        //textView = (TextView) findViewById(R.id.showpoints);
        editText = (EditText)findViewById(R.id.showpoints);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        btnDelete = (Button)findViewById(R.id.btnDelete);

        myDb = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //String newEntry = textView.getText().toString(); //textView.getText().toString();
                //newEntry = "test";
                String newEntry = editText.getText().toString();
                Date newDate = Calendar.getInstance().getTime();
                Log.i("Kello", "Kello"+ Calendar.getInstance().getTime());
                //if(textView.length()!= 0){
                if(editText.length()!= 0){
                    AddData(newEntry,newDate);
                    //textView.setText("");
                    editText.setText("");
                }else{
                    Toast.makeText(MainActivity.this, "Add something, please!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewListContent.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deleteRows = myDb.deleteData(5);

                if(deleteRows > 0){
                    Toast.makeText(MainActivity.this,"Data Deleted", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Points not Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void AddData(String newEntry, Date newDate) {
        boolean insertData = myDb.insertData(newEntry, newDate);
        if(insertData==true){
            Toast.makeText(this, "Points stored", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Points not stored", Toast.LENGTH_LONG).show();
        }
    }

}


