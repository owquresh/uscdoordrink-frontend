package com.example.models;

import java.sql.Timestamp;

public class Order {
    private int orderNumber;
    private int customerID;
    private String address;
    private int total_caffine;
    private String orderTime;
    private Timestamp deliveryTime;
    private String orderItem;

    public Order(int orderNumber, int customerID, String address, int total_caffine, String orderTime, String orderItem){
        this.orderNumber=orderNumber;
        this.customerID=customerID;
        this.address=address;
        this.total_caffine=total_caffine;
        this.orderTime=orderTime;
        this.orderItem=orderItem;
    }
    public void setDeliveryTime(Timestamp deliveryTime){this.deliveryTime=deliveryTime;}
    public Timestamp getDeliveryTime(){return deliveryTime;}

    public int getCustomerID() { return customerID; }
    public int getOrderNumber() { return orderNumber; }
    public String getAddress() { return address; }
    public int getTotal_caffine() { return total_caffine; }
    public String getOrderItem() { return orderItem; }
    public String getOrderTime() { return orderTime; }
}
