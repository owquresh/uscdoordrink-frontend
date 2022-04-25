package com.example.models;

public class Shop {

    private String name;
    private String email;
    private String pass;
    private int id;
    private String address;
    private String state;
    private String postal;
    private String city;
    private double lat;
    private double lng;
    public Shop(String name, String email, String pass, String address, String state, String city, String postal) {

        this.name = name;
        this.pass = pass;
        this.city = city;
        this.email = email;
        this.id = id;
        this.address = address;
        this.state = state;
        this.postal = postal;
        this.lat = lat;
        this.lng = lng;
    }

    public Shop(String name, String email, String address, String state, String city, String postal,
                double lat, double lng, int id
    ) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.state = state;
        this.city = city;
        this.postal = postal;
        this.id = id;
        this.lat = lat;
        this.lng = lng;

    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {return name;}
    public String getPass() {return pass;}
    public String getCity() {return city;}
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getPostal() {
        return postal;
    }
    public void setPostal(String postal) {
        this.postal = postal;
    }
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




}