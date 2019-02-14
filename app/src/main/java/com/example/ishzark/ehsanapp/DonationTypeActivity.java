package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class DonationTypeActivity extends AppCompatActivity {



    RelativeLayout itemButton;
    RelativeLayout moneyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donationtype);
       itemButton=findViewById(R.id.Monetrydonationbg);
        moneyButton=findViewById(R.id.itemdonationbg);



        Intent intent = getIntent();
        String phone=intent.getStringExtra("mobile");
        intent.putExtra("mobile",phone);
        String Donorname=intent.getStringExtra("donorname");
        intent.putExtra("donorname",Donorname);

        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(DonationTypeActivity.this, SavedLocationsActivity.class);
                startActivity(intent);
            }
        });


        moneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
                Intent intent = new Intent(DonationTypeActivity.this, MoneyDonationTypeActivity.class);
                startActivity(intent);

            }
        });

    }
}
