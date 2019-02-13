package com.example.ishzark.ehsanapp;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class Users {

    public String name;
    public String email;
    public String phone;
    public String gen;
    public String bd;



    public String city;

    ArrayList<LatLng> mylist = new ArrayList<LatLng>();

    public Users(){

    }



    public Users(String name, String phone,String gen,String bd,ArrayList mylist) {
        this.name = name;
        this.phone = phone;
        this.gen = gen;
        this.bd=bd;
        this.mylist=mylist;
        this.city=city;
    }


    public void setBd(String bd) {
        this.bd = bd;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addlocation(LatLng Coordinates){
        mylist.add(Coordinates);
        Log.d("mysavedlocation",Coordinates.toString()) ;

    }

}


