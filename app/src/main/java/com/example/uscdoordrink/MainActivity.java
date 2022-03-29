package com.example.uscdoordrink;

import android.content.Intent;
import android.os.Bundle;

import com.example.uscdoodrink.request.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.uscdoordrink.ui.main.SectionsPagerAdapter;
import com.example.uscdoordrink.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    Button buttonSignIn, buttonRegister, buttonData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

        buttonData = (Button) findViewById(R.id.buttonData);


        buttonSignIn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LoginActivity.class)));

        buttonRegister.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, RegisterActivity.class)));

        buttonData.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, DataActivity.class)));
    }
}
