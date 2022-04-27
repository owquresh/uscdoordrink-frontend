package com.example.uscdoordrink;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.models.Order;
import com.example.uscdoodrink.request.GsonGlobal;
import com.example.uscdoodrink.request.RequestGlobal;
import com.example.uscdoodrink.request.Session;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopCurrOrdersActivity extends AppCompatActivity {

    Button buttonBack;
    Session sesh;
    ArrayList<Map<String,String>> ordersList;
    LinearLayout order1, order2, order3, order4;
    TextView oNumber1, oAddress1, oTime1, oItem1,
            oNumber2, oAddress2, oTime2, oItem2,
            oNumber3, oAddress3, oTime3, oItem3,
            oNumber4, oAddress4, oTime4, oItem4;
    Button deliver1, deliver2, deliver3, deliver4;
    ArrayList<Order> orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_curr_orders);

        sesh = new Session(ShopCurrOrdersActivity.this);
        //listview = findViewById(R.id.currOrderView);
        ordersList = new ArrayList<>();

        order1 = findViewById(R.id.order1);
        order2 = findViewById(R.id.order2);
        order3 = findViewById(R.id.order3);
        order4 = findViewById(R.id.order4);

        oNumber1 = findViewById(R.id.orderNumber1);
        oAddress1 = findViewById(R.id.orderAddress1);
        oTime1 = findViewById(R.id.orderTime1);
        oItem1 = findViewById(R.id.orderItem1);
        deliver1 = findViewById(R.id.deliver1);

        oNumber2 = findViewById(R.id.orderNumber2);
        oAddress2 = findViewById(R.id.orderAddress2);
        oTime2 = findViewById(R.id.orderTime2);
        oItem2 = findViewById(R.id.orderItem2);
        deliver2 = findViewById(R.id.deliver2);

        oNumber3 = findViewById(R.id.orderNumber3);
        oAddress3 = findViewById(R.id.orderAddress3);
        oTime3 = findViewById(R.id.orderTime3);
        oItem3 = findViewById(R.id.orderItem3);
        deliver3 = findViewById(R.id.deliver3);

        oNumber4 = findViewById(R.id.orderNumber4);
        oAddress4 = findViewById(R.id.orderAddress4);
        oTime4 = findViewById(R.id.orderTime4);
        oItem4 = findViewById(R.id.orderItem4);
        deliver4 = findViewById(R.id.deliver4);

        order1.setVisibility(View.GONE);
        oNumber1.setVisibility(View.GONE);
        oAddress1.setVisibility(View.GONE);
        oTime1.setVisibility(View.GONE);
        oItem1.setVisibility(View.GONE);
        deliver1.setVisibility(View.GONE);
        oNumber1.setText("");
        oAddress1.setText("");
        oTime1.setText("");
        oItem1.setText("");

        order2.setVisibility(View.GONE);
        oNumber2.setVisibility(View.GONE);
        oAddress2.setVisibility(View.GONE);
        oTime2.setVisibility(View.GONE);
        oItem2.setVisibility(View.GONE);
        deliver2.setVisibility(View.GONE);
        oNumber2.setText("");
        oAddress2.setText("");
        oTime2.setText("");
        oItem2.setText("");

        order3.setVisibility(View.GONE);
        oNumber3.setVisibility(View.GONE);
        oAddress3.setVisibility(View.GONE);
        oTime3.setVisibility(View.GONE);
        oItem3.setVisibility(View.GONE);
        deliver3.setVisibility(View.GONE);
        oNumber3.setText("");
        oAddress3.setText("");
        oTime3.setText("");
        oItem3.setText("");

        order4.setVisibility(View.GONE);
        oNumber4.setVisibility(View.GONE);
        oAddress4.setVisibility(View.GONE);
        oTime4.setVisibility(View.GONE);
        oItem4.setVisibility(View.GONE);
        deliver4.setVisibility(View.GONE);
        oNumber4.setText("");
        oAddress4.setText("");
        oTime4.setText("");
        oItem4.setText("");

        buttonBack = findViewById(R.id.buttonCurrOrderBack);
        buttonBack.setOnClickListener(view -> startActivity(new Intent(ShopCurrOrdersActivity.this, DataActivity.class)));

        ((Activity) ShopCurrOrdersActivity.this).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/CurrOrder";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        response -> {
                            orders = null;
                            Type orderListType = new TypeToken<ArrayList<Order>>(){}.getType();
                            orders = GsonGlobal.getInstance().getGson().fromJson(response, orderListType);

                            if(orders.size()>0) {
                                order1.setVisibility(View.VISIBLE);
                                oNumber1.setVisibility(View.VISIBLE);
                                oAddress1.setVisibility(View.VISIBLE);
                                oTime1.setVisibility(View.VISIBLE);
                                oItem1.setVisibility(View.VISIBLE);
                                deliver1.setVisibility(View.VISIBLE);
                                oNumber1.setText("OrderNo: " + orders.get(0).getOrderNumber());
                                oAddress1.setText("Address: " + orders.get(0).getAddress());
                                oTime1.setText("Order Time: " + orders.get(0).getOrderTime());
                                oItem1.setText("Order Item: " + orders.get(0).getOrderItem());
                                deliver1.setMinHeight(0);

                                if (orders.size() > 1) {
                                    order2.setVisibility(View.VISIBLE);
                                    oNumber2.setVisibility(View.VISIBLE);
                                    oAddress2.setVisibility(View.VISIBLE);
                                    oTime2.setVisibility(View.VISIBLE);
                                    oItem2.setVisibility(View.VISIBLE);
                                    deliver2.setVisibility(View.VISIBLE);
                                    oNumber2.setText("OrderNo: " + orders.get(1).getOrderNumber());
                                    oAddress2.setText("Address: " + orders.get(1).getAddress());
                                    oTime2.setText("Order Time: " + orders.get(1).getOrderTime());
                                    oItem2.setText("Order Item: " + orders.get(1).getOrderItem());

                                    if (orders.size() > 2) {
                                        order3.setVisibility(View.VISIBLE);
                                        oNumber3.setVisibility(View.VISIBLE);
                                        oAddress3.setVisibility(View.VISIBLE);
                                        oTime3.setVisibility(View.VISIBLE);
                                        oItem3.setVisibility(View.VISIBLE);
                                        deliver3.setVisibility(View.VISIBLE);
                                        oNumber3.setText("OrderNo: " + orders.get(2).getOrderNumber());
                                        oAddress3.setText("Address: " + orders.get(2).getAddress());
                                        oTime3.setText("Order Time: " + orders.get(2).getOrderTime());
                                        oItem3.setText("Order Item: " + orders.get(2).getOrderItem());

                                        if (orders.size() > 3) {
                                            order4.setVisibility(View.VISIBLE);
                                            oNumber4.setVisibility(View.VISIBLE);
                                            oAddress4.setVisibility(View.VISIBLE);
                                            oTime4.setVisibility(View.VISIBLE);
                                            oItem4.setVisibility(View.VISIBLE);
                                            deliver4.setVisibility(View.VISIBLE);
                                            oNumber4.setText("OrderNo: " + orders.get(3).getOrderNumber());
                                            oAddress4.setText("Address: " + orders.get(3).getAddress());
                                            oTime4.setText("Order Time: " + orders.get(3).getOrderTime());
                                            oItem4.setText("Order Item: " + orders.get(3).getOrderItem());
                                        }
                                    }
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog alertDialog = new AlertDialog.Builder(ShopCurrOrdersActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("No Current Orders!");
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
                        params.put("email",sesh.getEmail());
                        return params;
                    }
                };
                RequestGlobal.getInstance(ShopCurrOrdersActivity.this).addToRequestQueue(stringRequest);
            }
        });

        String url2 = "http://10.0.2.2:8080/USCDoorDrinkBackend/DeliveryComplete";
        deliver1.setOnClickListener(view -> {
            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2,
                    response -> {
                        cleanActivity();
                        Intent registerIntent = new Intent(ShopCurrOrdersActivity.this, ShopCurrOrdersActivity.class);
                        startActivity(registerIntent);

                    },
                    error -> {
                        AlertDialog alertDialog = new AlertDialog.Builder(ShopCurrOrdersActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Unable to mark as delivered, try again!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                (dialog, which) -> dialog.dismiss());
                        alertDialog.show();

                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("orderID", oNumber1.getText().toString());
                    return params;
                }
            };
            RequestGlobal.getInstance(ShopCurrOrdersActivity.this).addToRequestQueue(stringRequest1);
        });

        deliver2.setOnClickListener(view -> {
            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2,
                    response -> {
                        cleanActivity();
                        Intent registerIntent = new Intent(ShopCurrOrdersActivity.this, ShopCurrOrdersActivity.class);
                        startActivity(registerIntent);

                    },
                    error -> {
                        AlertDialog alertDialog = new AlertDialog.Builder(ShopCurrOrdersActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Unable to mark as delivered, try again!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                (dialog, which) -> dialog.dismiss());
                        alertDialog.show();

                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("orderID", oNumber2.getText().toString());
                    return params;
                }
            };
            RequestGlobal.getInstance(ShopCurrOrdersActivity.this).addToRequestQueue(stringRequest1);
        });

        deliver3.setOnClickListener(view -> {
            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2,
                    response -> {
                        cleanActivity();
                        Intent registerIntent = new Intent(ShopCurrOrdersActivity.this, ShopCurrOrdersActivity.class);
                        startActivity(registerIntent);

                    },
                    error -> {
                        AlertDialog alertDialog = new AlertDialog.Builder(ShopCurrOrdersActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Unable to mark as delivered, try again!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                (dialog, which) -> dialog.dismiss());
                        alertDialog.show();

                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("orderID", oNumber3.getText().toString());
                    return params;
                }
            };
            RequestGlobal.getInstance(ShopCurrOrdersActivity.this).addToRequestQueue(stringRequest1);
        });

        deliver4.setOnClickListener(view -> {
            StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url2,
                    response -> {
                        cleanActivity();
                        Intent registerIntent = new Intent(ShopCurrOrdersActivity.this, ShopCurrOrdersActivity.class);
                        startActivity(registerIntent);

                    },
                    error -> {
                        AlertDialog alertDialog = new AlertDialog.Builder(ShopCurrOrdersActivity.this).create();
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Unable to mark as delivered, try again!");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                                (dialog, which) -> dialog.dismiss());
                        alertDialog.show();

                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("orderID", oNumber4.getText().toString());
                    return params;
                }
            };
            RequestGlobal.getInstance(ShopCurrOrdersActivity.this).addToRequestQueue(stringRequest1);
        });
    }

    public void cleanActivity(){

    }
}


//public class ShopCurrOrdersActivity extends AppCompatActivity {
//    Session sesh;
//    private SimpleAdapter ad;
//    List<Map<String, String>> orderList = new ArrayList<Map<String, String>>();
//    ListView listview;
//    Button buttonBack;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_shop_curr_orders);
//
//        sesh = new Session(ShopCurrOrdersActivity.this);
//        buttonBack = findViewById(R.id.buttonCurrOrderBack);
//        buttonBack.setOnClickListener(view -> startActivity(new Intent(ShopCurrOrdersActivity.this, DataActivity.class)));
//
//        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/CurrOrder";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        JsonArray ar = new Gson().fromJson(response, JsonArray.class);
//
//                        for (int i = 0; i < ar.size(); i++) {
//                            JsonObject obj = ar.get(i).getAsJsonObject();
//                            Map<String, String> currOrder = new HashMap<String, String>();
//                            currOrder.put("orderNumber", obj.get("orderNumber").getAsString());
//                            currOrder.put("orderAddress", obj.get("address").getAsString());
//                            currOrder.put("orderTime", obj.get("orderTime").getAsString());
//                            currOrder.put("orderItem", obj.get("orderItem").getAsString());
//                            orderList.add(currOrder);
//                        }
//                        listview = findViewById(R.id.currOrderList);
//                        String[] fromw = {"orderNumber", "orderAddress", "orderTime", "orderItem"};
//                        int[] tow = {R.id.orderNumber, R.id.address, R.id.orderTime, R.id.orderItem};
//                        ad = new SimpleAdapter(ShopCurrOrdersActivity.this, orderList, R.layout.currorderlayouttemplate, fromw, tow);
//                        listview.setAdapter(ad);
//                    }
//
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                AlertDialog alertDialog = new AlertDialog.Builder(ShopCurrOrdersActivity.this).create();
//                alertDialog.setTitle("Alert");
//                alertDialog.setMessage("No Current Orders!");
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog.show();
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("email", sesh.getEmail());
//                return params;
//            }
//
//
//        };
//        RequestGlobal.getInstance(ShopCurrOrdersActivity.this).addToRequestQueue(stringRequest);
//    }
//}
