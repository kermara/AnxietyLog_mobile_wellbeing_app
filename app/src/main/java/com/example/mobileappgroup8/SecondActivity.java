package com.example.mobileappgroup8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Button startTestButton = (Button) findViewById(R.id.start_button);

        startTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizActivityIntent = new Intent(SecondActivity.this, QuizActivity.class);
                startActivity(quizActivityIntent);
            }
        });

        Button createUserButton = (Button) findViewById(R.id.create_user_button);

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });
    }

    protected void createUser() {

        EditText editName = (EditText) findViewById(R.id.nameTextView);
        String nameInput = editName.getText().toString();
        TextView emailError = (TextView) findViewById(R.id.email_error);

        EditText editEmail = (EditText) findViewById(R.id.emailTextView);

        if (validEmail(editEmail.getText().toString()) == false) {
            emailError.setText("Please insert a valid email address");
        } else {
            String emailInput = editEmail.getText().toString();
            User user = new User(nameInput, emailInput);
            Intent quizActivityIntent = new Intent(SecondActivity.this, QuizActivity.class);
            startActivity(quizActivityIntent);


        }
    }

    protected boolean validEmail(CharSequence emailInput) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(emailInput).matches();
    }
}
