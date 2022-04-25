package com.example.uscdoodrink.request;

import com.google.gson.Gson;

public class GsonGlobal {

    private Gson gson = new Gson();

    private static GsonGlobal gsonUtil = new GsonGlobal();

    private GsonGlobal() {

    }

    public static GsonGlobal getInstance() {
        return gsonUtil;
    }


    public Gson getGson() {
        if(gson==null) {
            gson=new Gson();
        }
        return gson;
    }


    public <T> String toJson(final T t) {
        if(t==null) {
            return "";
        }
        return getGson().toJson(t);
    }

}
