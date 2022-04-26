package com.example.uscdoordrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.uscdoodrink.request.RequestGlobal;
import com.example.uscdoodrink.request.Session;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;

public class DataActivity extends AppCompatActivity {
    Button buttonLogout, buttonEditAddress, buttonAddMenuItem;
    TextView name, email, address, banner;
    Session sesh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        sesh = new Session(DataActivity.this);
        name = (TextView) findViewById(R.id.user_name);
        email = (TextView) findViewById(R.id.user_email);
        address = (TextView) findViewById(R.id.user_address);
        banner = (TextView) findViewById(R.id.banner_text);
        buttonAddMenuItem = (Button) findViewById(R.id.buttonAddMenuItem);
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonEditAddress = (Button) findViewById(R.id.buttonEditAddress);

        buttonEditAddress.setOnClickListener(view -> startActivity(new Intent(DataActivity.this, EditAddressActivity.class)));
        buttonLogout.setOnClickListener(view -> startActivity(new Intent(DataActivity.this, LoginActivity.class)));
        buttonAddMenuItem.setOnClickListener(view -> startActivity(new Intent(DataActivity.this, AddMenuItem.class)));

        if(sesh.getType().equals("customer")){
            buttonAddMenuItem.setVisibility(View.GONE);
        }

        Log.d("session1", sesh.getEmail().toString());
        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/Data";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        CollationElementIterator textView = null;
//                        textView.setText("Response is: " + response.substring(0,500));
                        JsonArray ar = new Gson().fromJson(response, JsonArray.class);
                        JsonObject obj = ar.get(0).getAsJsonObject();
                        email.setText(obj.get("email").getAsString());
                        name.setText(obj.get("name").getAsString());

                        String formattedAddress = obj.get("address").getAsString()
                                + " "+ obj.get("city").getAsString() + ", " +
                                obj.get("state").getAsString() + " " + obj.get("postal").getAsString();

                        address.setText(formattedAddress);
                        String bannerFormatted = sesh.getType().toUpperCase() + " " + "DATA";
                        banner.setText(bannerFormatted);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("email",sesh.getEmail());
                params.put("type",sesh.getType());
                return params;
            }


        };
        RequestGlobal.getInstance(DataActivity.this).addToRequestQueue(stringRequest);


    }

    private Response.ErrorListener createMyReqErrorListener() {
        return null;
    }

    private Response.Listener<String> createMyReqSuccessListener() {
        return null;
    }
}