package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class normaladminhome extends AppCompatActivity {
    private RelativeLayout manageadmins;
    private RelativeLayout managerequests;
    private RelativeLayout manageevents;
    private RelativeLayout managebenefits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normaladminhome);

        manageadmins=findViewById(R.id.mngadmin);
        managebenefits=findViewById(R.id.mngmemberships);
        manageevents=findViewById(R.id.AddEventBtn);
        managerequests=findViewById(R.id.mngrequests);




        managerequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(normaladminhome.this, DonationTypes_mng.class);
                startActivity(intent);
            }
        });

        manageevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(normaladminhome.this, EventHome.class);
                startActivity(intent);
            }
        });

        managebenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(normaladminhome.this, MembershipHome.class);
                startActivity(intent);
            }
        });
    }
}
