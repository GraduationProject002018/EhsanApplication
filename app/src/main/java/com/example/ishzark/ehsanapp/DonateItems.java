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
     private String Request_status;

    public DonateItems() {

    }




    public DonateItems(String donor_name, String item_type, String quantity,
                       String item_status, String location, String phone,
                       String itemDetails,String date,String itemValue,String Requeststatus) {
        Donor_name = donor_name;
        Item_type = item_type;
        this.quantity = quantity;
        Item_status = item_status;
        Location = location;
        this.phone = phone;
        this.ItemDetails=itemDetails;
        this.Date=date;
        ItemValue=itemValue;
        Request_status = Requeststatus;

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


    public String getRequest_status() {
        return Request_status;
    }

    public void setRequest_status(String request_status) {
        Request_status = request_status;
    }
}