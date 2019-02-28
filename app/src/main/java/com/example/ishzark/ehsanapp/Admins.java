package com.example.ishzark.ehsanapp;

public class Admins {
    String name, phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Admins(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Admins() {
    }
}
