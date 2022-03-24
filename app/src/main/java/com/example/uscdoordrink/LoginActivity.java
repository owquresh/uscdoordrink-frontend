package com.example.uscdoordrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText getUsername, getPassword;
    Button buttonUserSignIn, buttonSignInBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getPassword = (EditText) findViewById(R.id.getPassword);
        getUsername = (EditText) findViewById(R.id.getUsername);
        buttonUserSignIn = (Button) findViewById(R.id.buttonUserSignIn);
        buttonSignInBack = (Button) findViewById(R.id.buttonSignInBack);

        //Need to intialize database here and check to pull data from SQL when buttonUserSignIn is clicked

        buttonUserSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buttonSignInBack.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
    }
}