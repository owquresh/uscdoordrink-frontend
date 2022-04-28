package com.example.models;

public class LatLngWrapper {
    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public LatLngWrapper(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
