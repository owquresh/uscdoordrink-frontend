package com.example.uscdoordrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    Button buttonRegisterBack, buttonRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        buttonRegisterBack = (Button) findViewById(R.id.buttonRegisterBack);
        Spinner isCustomerSpinner = (Spinner) findViewById(R.id.spinnerCustomer);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        ArrayAdapter<String> isCustomerAdapter = new ArrayAdapter<>(RegisterActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.registerPageOptions));
        isCustomerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isCustomerSpinner.setAdapter(isCustomerAdapter);

        buttonRegisterBack.setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this, MainActivity.class)));
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}