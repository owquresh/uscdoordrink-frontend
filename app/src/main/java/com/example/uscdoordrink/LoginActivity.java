package com.example.uscdoordrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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

        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/Register";


        buttonUserSignIn.setOnClickListener(new View.OnClickListener() {




            public void onClick(View view) {



            }
        });

        buttonSignInBack.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
    }

    private Response.ErrorListener createMyReqErrorListener() {
        return null;
    }

    private Response.Listener<String> createMyReqSuccessListener() {
        return null;
    }
}