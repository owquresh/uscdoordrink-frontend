package com.example.uscdoordrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.uscdoodrink.request.RequestGlobal;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    Button buttonRegisterBack, buttonRegister;
    EditText inputName, inputUsername, inputEmail, inputPassword,inputPostalCode, inputAddress, inputCity, inputState;
    Spinner spinnerCustomer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonRegisterBack = (Button) findViewById(R.id.buttonRegisterBack);
        Spinner isCustomerSpinner = (Spinner) findViewById(R.id.spinnerCustomer);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        inputName = (EditText) findViewById(R.id.inputName);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        inputAddress = (EditText) findViewById(R.id.inputAddress);
        inputCity = (EditText) findViewById(R.id.inputCity);
        inputState = (EditText) findViewById(R.id.inputState);
        inputPostalCode = (EditText) findViewById(R.id.inputPostalCode);

        spinnerCustomer = (Spinner) findViewById(R.id.spinnerCustomer);


        ArrayAdapter<String> isCustomerAdapter = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.registerPageOptions));
        isCustomerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isCustomerSpinner.setAdapter(isCustomerAdapter);

        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/Register";
        buttonRegisterBack.setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this, MainActivity.class)));
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("name",inputName.getText().toString());
//                        params.put("username",inputUsername.getText().toString());
                        params.put("email",inputEmail.getText().toString());
                        params.put("password",inputPassword.getText().toString());
                        params.put("address",inputAddress.getText().toString());
                        params.put("city",inputCity.getText().toString());
                        params.put("state",inputState.getText().toString());
                        params.put("postal",inputPostalCode.getText().toString());
                        params.put("type", spinnerCustomer.getSelectedItem().toString());

                        return params;
                    }
                };
                RequestGlobal.getInstance(RegisterActivity.this).getRequestQueue().add(stringRequest);

            }
        });
    }
}