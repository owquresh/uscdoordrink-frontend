package com.example.uscdoodrink.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;
    public Session (Context cnt){
        prefs = PreferenceManager.getDefaultSharedPreferences(cnt);


    }

    public void setLogin(boolean bool){
        prefs.edit().putBoolean("login", true).commit();
    }

    public void setEmail(String email){
        prefs.edit().putString("email", email).commit();

    }

    public void setType(String type){
        prefs.edit().putString("type", type).commit();
    }

    public String getType(){
        return  prefs.getString("type","");

    }
    public boolean getBoolean(){

        return prefs.getBoolean("login", false);
    }
    public String getEmail(){
        return prefs.getString("email","");

    }
}
