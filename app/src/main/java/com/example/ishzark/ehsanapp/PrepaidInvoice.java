package com.example.ishzark.ehsanapp;

public class PrepaidInvoice {
    private String DonorName;
    private String DonationAmount;
    private String InvoiceNum;
    private String date;
    private String bankName;
    private String phone;
    private String Program;
    private String request_status;





    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequest_status() {
        return request_status;
    }

    public void setRequest_status(String request_status) {
        this.request_status = request_status;
    }

    public PrepaidInvoice() {
    }

    public PrepaidInvoice(String donorName, String donationAmount, String invoiceNum, String date, String bankName, String phone, String program, String request_status) {
        DonorName = donorName;
        DonationAmount = donationAmount;
        InvoiceNum = invoiceNum;
        this.date = date;
        this.bankName = bankName;
        this.phone = phone;
        Program = program;
        this.request_status = request_status;
    }

    public String getDonorName() {
        return DonorName;
    }

    public void setDonorName(String donorName) {
        DonorName = donorName;
    }

    public String getDonationAmount() {
        return DonationAmount;
    }

    public void setDonationAmount(String donationAmount) {
        DonationAmount = donationAmount;
    }

    public String getInvoiceNum() {
        return InvoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        InvoiceNum = invoiceNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProgram() {
        return Program;
    }

    public void setProgram(String program) {
        Program = program;
    }
}