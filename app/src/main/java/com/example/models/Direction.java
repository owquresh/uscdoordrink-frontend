package com.example.models;

import java.util.ArrayList;

public class Direction {
    private String duration;
    private String distance;

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public ArrayList<LatLngWrapper> getPath() {
        return path;
    }

    public void setPath(ArrayList<LatLngWrapper> path) {
        this.path = path;
    }

    public Direction(String duration, String distance, ArrayList<LatLngWrapper> path) {
        this.duration = duration;
        this.distance = distance;
        this.path = path;
    }

    private ArrayList<LatLngWrapper> path;

}
