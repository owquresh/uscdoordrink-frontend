package com.example.uscdoordrink;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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

public class AddMenuItem extends AppCompatActivity{

    Session sesh;
    private static final int RESULT_LOAD_IMAGE = 1;
    ImageView imageItem;
    Button backButton, saveItem;
    EditText itemName, itemDescription, itemCaffine, itemPrice, itemDiscountPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu_item);
        sesh = new Session(AddMenuItem.this);

        Log.d("session1", sesh.getEmail().toString());
        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/AddMenuItem";

        imageItem = (ImageView) findViewById(R.id.itemImage);
        backButton = (Button) findViewById(R.id.buttonAddItemBack);
        saveItem = (Button) findViewById(R.id.saveItem);
        itemName = (EditText) findViewById(R.id.itemName);
        itemDescription = (EditText) findViewById(R.id.itemDescription);
        itemCaffine = (EditText) findViewById(R.id.itemCaffine);
        itemPrice = (EditText) findViewById(R.id.itemPrice);
        itemDiscountPrice = (EditText) findViewById(R.id.itemDiscountPrice);

        imageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
            }
        });

        backButton.setOnClickListener(view -> startActivity(new Intent(AddMenuItem.this, DataActivity.class)));

        saveItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Intent loginIntent = new Intent(AddMenuItem.this, DataActivity.class);
                                startActivity(loginIntent);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog alertDialog = new AlertDialog.Builder(AddMenuItem.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Could not add menu items, please fill fields and try again!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", sesh.getEmail());
                        params.put("name", itemName.getText().toString());
                        params.put("description", itemDescription.getText().toString());
                        params.put("caffine", itemCaffine.getText().toString());
                        params.put("price", itemPrice.getText().toString());
                        params.put("discountprice", itemDiscountPrice.getText().toString());
                        return params;
                    }


                };
                RequestGlobal.getInstance(AddMenuItem.this).addToRequestQueue(stringRequest);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            imageItem.setImageURI(selectedImage);
        }
    }
}