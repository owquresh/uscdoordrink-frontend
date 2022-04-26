package com.example.uscdoordrink;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
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

        sesh = new Session(EditAddressActivity.this);
        buttonBack = findViewById(R.id.buttonEditAddressBack);
        buttonSave = findViewById(R.id.buttonSave);
        address = findViewById(R.id.inputAddress1);
        city = findViewById(R.id.inputCity1);
        state = findViewById(R.id.inputState1);
        postal = findViewById(R.id.inputPostalCode1);

        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/EditAddress";
        buttonBack.setOnClickListener(view -> startActivity(new Intent(EditAddressActivity.this, DataActivity.class)));

        buttonSave.setOnClickListener(view -> {
            StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                    response -> {
                        Intent registerIntent = new Intent(EditAddressActivity.this, DataActivity.class);
                        startActivity(registerIntent);

                    },
                    error -> {
                        AlertDialog alertDialog = new AlertDialog.Builder(EditAddressActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Unable to update address, try again!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                (dialog, which) -> dialog.dismiss());
                        alertDialog.show();

                    }){
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<>();
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
        });


    }
}