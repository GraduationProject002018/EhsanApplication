package com.example.ishzark.ehsanapp;

public class Users {

    public String name, email, phone,pass,gen,bd,city;

    public Users(){

    }



    public Users(String name, String phone, String pass,String gen,String bd,String city) {
        this.name = name;
        this.phone = pass;
        this.gen = gen;
        this.bd=bd;
        this.city=city;
    }


    public void setBd(String bd) {
        this.bd = bd;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}


