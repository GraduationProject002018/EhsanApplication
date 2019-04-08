package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class DonationHistoryTypes extends AppCompatActivity {

        RelativeLayout itemButton;
        RelativeLayout moneyButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.donationhistory_types);
            itemButton=findViewById(R.id.Monetrydonationbg);
            moneyButton=findViewById(R.id.itemdonationbg);


            itemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(com.example.ishzark.ehsanapp.DonationHistoryTypes.this, DonationHistoryActivity2.class);
                    startActivity(intent);
                }
            });


            moneyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(com.example.ishzark.ehsanapp.DonationHistoryTypes.this, DonationHistoryActivity.class);
                    startActivity(intent);
                }
            });

        }




}
