package com.example.models;
public class Items {
    private int itemID;
    private int shopID;
    private String itemName;
    private String description;
    private double itemPrice;
    private int caffeineAmt;


    public Items(int itemID, int shopID, String itemName, String description, int caffeineAmt, double itemPrice){
        this.itemID = itemID;
        this.shopID = shopID;
        this.caffeineAmt = caffeineAmt;
        this.itemName = itemName;
        this.description = description;
        this.itemPrice = itemPrice;
    }


    public String getItemName(){
        return this.itemName;
    }

    public double getItemPrice(){
        return this.itemPrice;
    }

    public int getItemID( ) { return this.itemID; }

    public String getDescription() { return this.description; }

    public int getShopID () { return this.shopID; }

    public int getCaffeineAmt() {
        return caffeineAmt;
    }

    public void setCaffeineAmt(int caffeineAmt) {
        this.caffeineAmt = caffeineAmt;
    }

    public void setShopID (int i) { this.shopID = i; }

    public void setItemName(String i){
        this.itemName = i;
    }

    public void setItemPrice(double p){
        this.itemPrice = p;
    }

    public void setDescription (String d) { this.description = d; }

    public void setItemID ( int id) { this.itemID = id; }
}
