package com.example.uscdoordrink;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.models.Direction;
import com.example.models.LatLngWrapper;
import com.example.models.MarkerModel;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;
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
    private HashMap<Integer, Marker> shopMap;
    private HashMap<Marker, MarkerModel> markerMap;
    private ActivityMapsBinding binding;
    ArrayList<Shop> shops;
    Direction path;
    ArrayList<LatLng> actualPath;
    ArrayList<Polyline> polyLines;
    LatLng currLoc;
    LatLng dest;
    Marker currMarker;
    private Button[] btn = new Button[4];
    private Button btn_unfocus;
    private int[] btn_id = {R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3};
    TextView shopName;
    TextView shopDistance;
    TextView shopDuration;
    TextView travelMode;


    ScheduledExecutorService backgroundExecutor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        shopName = (TextView) findViewById(R.id.shopName);
        shopDistance = (TextView) findViewById(R.id.shopDistance);
        shopDuration = (TextView) findViewById(R.id.shopDuration);
        travelMode = (TextView) findViewById(R.id.mode);

        shops = new ArrayList<Shop>();
        shopMap = new HashMap<>();
        markerMap = new HashMap<>();
        path = null;
        actualPath = new ArrayList<>();
        polyLines = new ArrayList<>();
        sesh = new Session(this);




        for(int i=0;i<btn.length;i++){
            btn[i] = (Button) findViewById(btn_id[i]);
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()){
                        case R.id.btn0:
                            clearRoutes();
                            grabDirections("http://10.0.2.2:8080/USCDoorDrinkBackend/Direction","walk",currLoc,dest.latitude,dest.longitude,currMarker);
                            travelMode.setText("Walking");
                            break;
                        case R.id.btn1:
                            clearRoutes();
                            grabDirections("http://10.0.2.2:8080/USCDoorDrinkBackend/Direction","bike",currLoc,dest.latitude,dest.longitude,currMarker);
                            travelMode.setText("Biking");
                            break;
                        case R.id.btn2:
                            clearRoutes();
                            grabDirections("http://10.0.2.2:8080/USCDoorDrinkBackend/Direction","car",currLoc,dest.latitude,dest.longitude,currMarker);
                            travelMode.setText("Driving");
                            break;
                        case R.id.btn3:
                            clearRoutes();
                            travelMode.setText(travelMode.getText().toString() + ": Cleared");
                            break;

                    }
                }
            });
            btn_unfocus=btn[0];
        }

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
            MarkerModel model = new MarkerModel(shop.getName(), shop,shop.getId());
            shopMap.put(shop.getId(),marker);
            markerMap.put(marker, model );


        }



        if(currLoc == null){
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }else{
            mMap.addMarker(new MarkerOptions().position(currLoc).title("Current Location").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(currLoc));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currLoc,15));
        }



        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Nullable
            @Override
            public View getInfoContents(@NonNull Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.info_window,null);
                TextView name = v.findViewById(R.id.name);
                TextView address = v.findViewById(R.id.address);

                name.setText(marker.getTitle());
                address.setText(markerMap.get(marker).getShop().getAddress());

                return v;

            }

            @Nullable
            @Override
            public View getInfoWindow(@NonNull Marker marker) {
                return null;
            }
        });

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                clearRoutes();
                double lat = markerMap.get(marker).getShop().getLat();
                double lng = markerMap.get(marker).getShop().getLng();

                currMarker = marker;
                dest = new LatLng(lat, lng);
                Log.d("map1","marker click test");
                grabDirections("http://10.0.2.2:8080/USCDoorDrinkBackend/Direction","car",currLoc,lat, lng,marker);

                return false;
            }
        });



    }

    class DirectionThread extends Thread{
        String response;
        Marker marker;
        DirectionThread(String rep, Marker mark){
            response = new String(rep);
            marker = mark;
        }
        public void run(){
            Log.d("map1","thread  test");
            Log.d("map1",response);
            Type pathType = new TypeToken<Direction>(){}.getType();
            path = GsonGlobal.getInstance().getGson().fromJson(response, pathType);


            if(path.getPath().size() > 0){
                for(LatLngWrapper pt: path.getPath()){
                    LatLng value = new LatLng(pt.getLat(),pt.getLng());
                    actualPath.add(value);
                }
            }

            mainExecutor.execute(new Runnable() {
                @Override
                public void run() {

                    if(path.getPath().size()>0) {
                        PolylineOptions opts = new PolylineOptions().addAll(actualPath).color(Color.BLUE).width(5);
                        polyLines.add(mMap.addPolyline(opts));

                    }
                    shopName.setText(marker.getTitle());
                    shopDistance.setText(path.getDistance());
                    shopDuration.setText(path.getDuration());
                }

            });
        }
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

    private void grabDirections(String url, String type, LatLng currLoc, double lat, double lng,Marker marker){
        for(Polyline line: polyLines){
            line.remove();
        }
        polyLines.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                DirectionThread thread = new DirectionThread(response,marker);
//                currMarker = marker;
                thread.start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("map1",error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();


                params.put("origin_lat",Double.toString(currLoc.latitude));
                params.put("origin_lng",Double.toString(currLoc.longitude));
                params.put("dest_lat",Double.toString(lat));
                params.put("dest_lng",Double.toString(lng));
                params.put("type",type);

                return params;
            }
        };
        RequestGlobal.getInstance(MapsActivity.this).addToRequestQueue(stringRequest);
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

    public boolean clearRoutes(){
        if(actualPath.size()>0){
            for(Polyline line: polyLines){
                line.remove();
            }
            actualPath.clear();
            polyLines.clear();
            path = null;
            return true;
        }
        return false;
    }



}