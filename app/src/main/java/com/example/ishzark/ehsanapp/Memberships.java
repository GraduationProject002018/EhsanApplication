package com.example.ishzark.ehsanapp;

public class Memberships {
    private String Type;
    private String Feature;

    public Memberships(String type, String feature) {
        Type = type;
        Feature = feature;
    }

    public Memberships() {
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getFeature() {
        return Feature;
    }

    public void setFeature(String feature) {
        Feature = feature;
    }


}
