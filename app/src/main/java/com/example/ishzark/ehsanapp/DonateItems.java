package com.example.ishzark.ehsanapp;

public class DonateItems {

    private String Donor_name;
    private String Item_type;
    private String quantity;
    private String Item_status;
    private String Location;
    private String phone;
    private String ItemDetails;
    private String ItemValue;
    private String Date;




    private String request_status;
    private String Url;
    private String key;



    public DonateItems() {

    }

    public void setRequestStatus(String requeststatus) {
        request_status = requeststatus;
    }

    public void setDonor_name(String donor_name) {
        Donor_name = donor_name;
    }

    public void setItem_type(String item_type) {
        Item_type = item_type;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setItem_status(String item_status) {
        Item_status = item_status;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUrl(String url) {
        Url = url;
    }


    public String getRequestStatus() {
        return request_status;
    }

    public String getUrl() {
        return Url;
    }

    public String getDonor_name() {
        return Donor_name;
    }

    public String getItem_type() {
        return Item_type;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getItem_status() {
        return Item_status;
    }

    public String getLocation() {
        return Location;
    }

    public String getPhone() {
        return phone;
    }

    public void setItemDetails(String itemDetails) {
        ItemDetails = itemDetails;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setItemValue(String itemValue) {
        ItemValue = itemValue;
    }

    public String getItemDetails() {
        return ItemDetails;
    }

    public String getDate() {
        return Date;
    }

    public String getItemValue() {
        return ItemValue;
    }

    public void setKey(String key) {
        this.key = key;
    }
}