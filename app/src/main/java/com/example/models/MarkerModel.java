package com.example.models;

public class MarkerModel {

    private String title;
    private int id;
    private Shop shop;

    public MarkerModel(String title, Shop shop,int id) {
        this.title = title;
        this.shop = shop;
        this.id = id;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }


}
