package com.example.mobileappgroup8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. starting_activity);

        Button b = (Button)findViewById(R.id.start_button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondActivityIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(secondActivityIntent);
                overridePendingTransition(R. anim. slide_in_right, R. anim. slide_out_left);
            }
        });
    }
}
