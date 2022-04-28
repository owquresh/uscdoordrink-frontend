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
import com.example.models.OrderDataObjects;
import com.example.uscdoodrink.request.RequestGlobal;
import com.example.uscdoodrink.request.Session;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class OrderDataPageActivity extends AppCompatActivity {
    Session sesh;
    ItemsInCart menuItems;
    OrderDataObjects curr;
    ArrayList<OrderDataObjects> orderDataList = new ArrayList<OrderDataObjects>();
    List<Map<String, String>> orderList = new ArrayList<Map<String, String>>();

    private SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("orderData", "entered onCreate");
        super.onCreate(savedInstanceState);
        sesh = new Session(OrderDataPageActivity.this);
        if(!sesh.getType().equals("customer")) return;
        setContentView(R.layout.activity_orderdata);


        Log.d("session1", sesh.getEmail().toString());
        String url = "http://10.0.2.2:8082/USCDoorDrinkBackend/OrderDataDAO?customerID=1";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("orderData", "entered Response");


                        JsonArray ar = new Gson().fromJson(response, JsonArray.class);


                        for (int i = 0; i<ar.size(); i++){
                            JsonObject obj = ar.get(i).getAsJsonObject();
                            Map<String, String> currItemMap = new HashMap<String, String>();
                            int orderID = obj.get("orderID").getAsInt();
                            int shopID = obj.get("shopID").getAsInt();
                            double total_caffine = obj.get("total_caffine").getAsDouble();
                            String orderitem = obj.get("orderitem").getAsString();

                            currItemMap.put("orderitem", obj.get("orderitem").getAsString());
                            currItemMap.put("total_caffine", obj.get("total_caffine").getAsString());
                            currItemMap.put("orderID", obj.get("orderID").getAsString());
                            orderList.add(currItemMap);
                            orderDataList.add(curr);
                            Log.d("orderData", "orderID" + orderID + "shopID" + shopID + "total_caffine" + total_caffine + "orderitem" + orderitem );
                        }



                        ListView listView = (ListView) findViewById(R.id.orderDataListView);



                        String[] fromw={"orderitem", "total_caffine", "orderID"};
                        int[] tow={R.id.item, R.id.caffine, R.id.orderID};
                        simpleAdapter = new SimpleAdapter(OrderDataPageActivity.this, orderList, R.layout.listlaytemplate_orderdata, fromw, tow);
                        listView.setAdapter(simpleAdapter);

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                Log.d("orderData", "the data is not loading");
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
        RequestGlobal.getInstance(OrderDataPageActivity.this).addToRequestQueue(stringRequest);





    }

}
