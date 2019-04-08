package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class DonationTypes_mng extends AppCompatActivity {

    RelativeLayout itemButton;
    RelativeLayout moneyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donationtypes_mng);
        itemButton=findViewById(R.id.Monetrydonationbg);
        moneyButton=findViewById(R.id.itemdonationbg);



        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // finish();
                Intent intent = new Intent(DonationTypes_mng.this, ItemsRequestsHome.class);
                startActivity(intent);
            }
        });


        moneyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  finish();
                Intent intent = new Intent(DonationTypes_mng.this, PrepaidInvoiceHome.class);
                startActivity(intent);
            }
        });

    }

}
