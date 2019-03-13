package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PrepaidInvoiceHome extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    String key;

    private android.support.v7.widget.RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.requestsmng2);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Invoices");
        recyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.recyclerV);




        /*databaseReference.orderByChild("request_status"); {

        };*/
        new firebaseDatabaseDonationRequests_DR().readrequests(new firebaseDatabaseDonationRequests_DR.DataStatues() {



            public void DataInvoice(List<PrepaidInvoice> pre, List<String> keys) {
                new MainDonationRequests2().setConfig(PrepaidInvoiceHome.this, recyclerView, pre, keys);
            }


        });


    }}
