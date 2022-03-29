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
import com.example.uscdoodrink.request.RequestGlobal;
import com.example.uscdoodrink.request.Session;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.CollationElementIterator;

public class DataActivity extends AppCompatActivity {
    EditText getUsername, getPassword;
    Button buttonUserSignIn, buttonSignInBack;
    Session sesh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        sesh = new Session(DataActivity.this);
        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/Data";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        CollationElementIterator textView = null;
//                        textView.setText("Response is: " + response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
            }
        });
        RequestGlobal.getInstance(DataActivity.this).addToRequestQueue(stringRequest);


    }

    private Response.ErrorListener createMyReqErrorListener() {
        return null;
    }

    private Response.Listener<String> createMyReqSuccessListener() {
        return null;
    }
}