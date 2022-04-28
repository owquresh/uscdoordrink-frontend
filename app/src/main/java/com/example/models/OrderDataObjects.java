package com.example.models;
public class OrderDataObjects {
    private int orderID;
    private int shopID;
    private double total_caffine;
    private String orderitem;

    public OrderDataObjects(int orderID, int shopID, double total_caffine, String orderitem){
        this.orderID = orderID;
        this.shopID = shopID;
        this.total_caffine = total_caffine;
        this.orderitem = orderitem;
    }

    public int getShopID() {
        return shopID;
    }

    public double getTotal_caffine() {
        return total_caffine;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getOrderitem() {
        return orderitem;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderitem(String orderitem) {
        this.orderitem = orderitem;
    }

    public void setShopID(int shopID) {
        this.shopID = shopID;
    }

    public void setTotal_caffine(double total_caffine) {
        this.total_caffine = total_caffine;
    }

}
