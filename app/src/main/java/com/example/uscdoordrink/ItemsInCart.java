package com.example.uscdoordrink;

import com.example.models.Items;

import java.util.ArrayList;

public class ItemsInCart {
    private ArrayList<Items> items = new ArrayList<Items>();
    public ItemsInCart(Items i ){
        items.add(i);
    }

    public void add(Items i){
        this.items.add(i);
    }


//    public void addToCart(View activity_shopping){
//
//
//    }


}
