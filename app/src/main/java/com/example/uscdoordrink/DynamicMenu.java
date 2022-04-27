package com.example.uscdoordrink;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.models.Items;
import com.example.uscdoodrink.request.RequestGlobal;
import com.example.uscdoodrink.request.Session;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicMenu extends AppCompatActivity {
    Session sesh;
    ItemsInCart menuItems;
    Items curr;
    List<Map<String, String>> menuList = new ArrayList<Map<String, String>>();

    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sesh = new Session(DynamicMenu.this);
        if(!sesh.getType().equals("customer")) return;
        setContentView(R.layout.activity_dynamicmenu);


        //Log.d("session1", sesh.getEmail().toString());
        String url = "http://10.0.2.2:8082/USCDoorDrinkBackend/Menu?shopID=1";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        JsonArray ar = new Gson().fromJson(response, JsonArray.class);

                        for (int i = 0; i<ar.size(); i++){
                            JsonObject obj = ar.get(i).getAsJsonObject();
                            Map<String, String> currItemMap = new HashMap<String, String>();
                            //currItemMap.put("shopID", obj.get("shopID").getAsString());
                            currItemMap.put("item", obj.get("item").getAsString());
                            if(obj.has("description")){
                                currItemMap.put("description", obj.get("description").getAsString());
                            }
                            else{
                                currItemMap.put("description", "");
                            }

                            currItemMap.put("cost", obj.get("cost").getAsString());
                            Log.d("getMenu", "Item cost: "+ obj.get("cost").getAsString());

                            menuList.add(currItemMap);

                        }


                        ListView listView = (ListView) findViewById(R.id.list_view_menu);



                        String[] fromw={"item", "description", "cost"};
                        int[] tow={R.id.item, R.id.description, R.id.cost};
                        simpleAdapter = new SimpleAdapter(DynamicMenu.this, menuList, R.layout.listlayouttemplate_menu, fromw, tow);
                        listView.setAdapter(simpleAdapter);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                Log.d("getMenu", "the menu is not loading");
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
        RequestGlobal.getInstance(DynamicMenu.this).addToRequestQueue(stringRequest);





    }

}
