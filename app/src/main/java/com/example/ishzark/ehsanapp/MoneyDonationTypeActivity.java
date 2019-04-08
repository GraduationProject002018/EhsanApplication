package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MoneyDonationTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moneydonationtype);

        RelativeLayout sanad = findViewById(R.id.sanaddonationbg);
        RelativeLayout money = findViewById(R.id.fastdonationbg);

        sanad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MoneyDonationTypeActivity.this, PrepaidInvoice_main.class);
                startActivity(i);

            }
        });

        money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toast.makeText(MoneyDonationTypeActivity.this, "قريباً", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MoneyDonationTypeActivity.this, Gpay.class);
                startActivity(i);

            }
        });

    }
}
