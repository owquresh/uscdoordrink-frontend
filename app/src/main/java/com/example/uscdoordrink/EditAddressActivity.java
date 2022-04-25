package com.example.uscdoordrink;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.uscdoodrink.request.RequestGlobal;
import com.example.uscdoodrink.request.Session;

import java.util.HashMap;
import java.util.Map;

public class EditAddressActivity extends AppCompatActivity {
    Button buttonSave, buttonBack;
    EditText address, city, state, postal;
    Session sesh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        buttonBack = (Button) findViewById(R.id.buttonEditAddressBack);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        address = (EditText) findViewById(R.id.inputAddress1);
        city = (EditText) findViewById(R.id.inputCity1);
        state = (EditText) findViewById(R.id.inputState1);
        postal = (EditText) findViewById(R.id.inputPostalCode1);

        buttonBack.setOnClickListener(view -> startActivity(new Intent(EditAddressActivity.this, DataActivity.class)));
        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/EditAddress";
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent registerIntent = new Intent(EditAddressActivity.this, DataActivity.class);
                                startActivity(registerIntent);

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                AlertDialog alertDialog = new AlertDialog.Builder(EditAddressActivity.this).create();
                                alertDialog.setTitle("Alert");
                                alertDialog.setMessage("Unable to update address, try again!");
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
                        params.put("email", sesh.getEmail());
                        params.put("type", sesh.getType());
                        params.put("address",address.getText().toString());
                        params.put("city",city.getText().toString());
                        params.put("state",state.getText().toString());
                        params.put("postal",postal.getText().toString());
                        return params;
                    }
                };
                RequestGlobal.getInstance(EditAddressActivity.this).getRequestQueue().add(stringRequest);
            }
        });


    }
}