package com.example.uscdoordrink;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText getEmail, getPassword;
    Button buttonUserSignIn, buttonSignInBack;
    Spinner spinnerCustomer;

    Session sesh;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Spinner isCustomerSpinner = (Spinner) findViewById(R.id.spinnerCustomer);
        //sesh = new Session(LoginActivity.this);
        getPassword = (EditText) findViewById(R.id.getPassword);
        getEmail = (EditText) findViewById(R.id.getEmail);
        buttonUserSignIn = (Button) findViewById(R.id.buttonUserSignIn);
        buttonSignInBack = (Button) findViewById(R.id.buttonSignInBack);

        spinnerCustomer = (Spinner) findViewById(R.id.spinnerCustomer);


        ArrayAdapter<String> isCustomerAdapter = new ArrayAdapter<>(LoginActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.registerPageOptions));
        isCustomerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isCustomerSpinner.setAdapter(isCustomerAdapter);

        //Need to intialize database here and check to pull data from SQL when buttonUserSignIn is clicked

        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/Login";


        buttonUserSignIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                sesh.setLogin(true);
                                sesh.setEmail(getEmail.toString());
                                sesh.setType(spinnerCustomer.getSelectedItem().toString().toLowerCase());
                                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(loginIntent);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
                                alertDialog.setTitle("Alert");
                                alertDialog.setMessage("Login not possible, try again");
                                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });
                                alertDialog.show();
                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("email",getEmail.getText().toString());
                        params.put("password",getPassword.getText().toString());
                        params.put("type", spinnerCustomer.getSelectedItem().toString());
                        return params;
                    }
                };
                RequestGlobal.getInstance(LoginActivity.this).getRequestQueue().add(stringRequest);

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