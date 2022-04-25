package com.example.uscdoordrink;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.models.Shop;
import com.example.uscdoodrink.request.GsonGlobal;
import com.example.uscdoodrink.request.RequestGlobal;
import com.example.uscdoodrink.request.Session;
import com.example.uscdoordrink.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.xml.sax.DTDHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Executor mainExecutor;
    Session sesh;
    private ArrayList<Shop> shopList;
    private HashMap<Integer, Marker> shopMap;

    private ActivityMapsBinding binding;
    ArrayList<Shop> shops;
    LatLng currLoc;
    ScheduledExecutorService backgroundExecutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shops = new ArrayList<Shop>();
        shopMap = new HashMap<>();
        sesh = new Session(this);

        //setup main thread executor and background work thread pool
        mainExecutor = ContextCompat.getMainExecutor(this);
        ScheduledExecutorService backgroundExecutor = Executors.newSingleThreadScheduledExecutor();


        //start databasae requests to setup map
        grabUserData("http://10.0.2.2:8080/USCDoorDrinkBackend/Data");


    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(34,-118);

        for(Shop shop: shops){
            LatLng location = new LatLng(shop.getLat(),shop.getLng());
            Marker marker = mMap.addMarker(new MarkerOptions().position(location).title(shop.getName()).snippet(shop.getAddress()));
            shopMap.put(shop.getId(),marker);

        }



        if(currLoc == null){
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }else{
            mMap.addMarker(new MarkerOptions().position(currLoc).title("Current Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currLoc));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currLoc,15));
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                if(marker.isInfoWindowShown()){
                    marker.hideInfoWindow();
                }else{
                    marker.showInfoWindow();
                }



                return false;
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(@NonNull Marker marker) {
                if(marker.getTitle().equals("Current Location")){
                    Intent dataIntent = new Intent(MapsActivity.this, DataActivity.class);
                    startActivity(dataIntent);
                }
            }
        });


    }


    class SearchThread extends Thread{
        String response;
        SearchThread(String rep){
            response = new String(rep);
        }
        public void run(){

            Type shopListType = new TypeToken<ArrayList<Shop>>(){}.getType();
            shops = GsonGlobal.getInstance().getGson().fromJson(response, shopListType);

            mainExecutor.execute(new Runnable(){

                @Override
                public void run() {

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsActivity.this);

                }
            });
        }
    }

    class MapThread extends Thread{


        String response;
        MapThread(String rep){
            response = new String(rep);
        }

        public void run(){
            JsonArray ar = GsonGlobal.getInstance().getGson().fromJson(response, JsonArray.class);
            JsonObject obj = ar.get(0).getAsJsonObject();
            String lat = obj.get("lat").getAsString();
            String lng = obj.get("lng").getAsString();

            currLoc = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));

            Log.d("test1","first request");
            Log.d("test1",lat + ":" + lng);
            findShops(lat,lng);


        }

    }


    private void grabUserData(String url){

//        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/Data";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        MapThread thread = new MapThread(response);
                        thread.start();
//                        findShops(lat,lng);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                Log.d("test1","response not sent");
                Log.d("test1",error.toString());
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
        RequestGlobal.getInstance(MapsActivity.this).getRequestQueue().add(stringRequest);
    }

    private void findShops(String lat, String lng){
        Log.d("findshop","ret");
        String url = "http://10.0.2.2:8080/USCDoorDrinkBackend/Search";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        SearchThread thread = new SearchThread(response);
                        thread.start();

                        Log.d("test1","second request");


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                Log.d("test1","second request didnt work");
                Log.d("test1",error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();


                params.put("lat",lat);
                params.put("lng",lng);

                return params;
            }


        };
        RequestGlobal.getInstance(MapsActivity.this).getRequestQueue().add(stringRequest);


    }



}