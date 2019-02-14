package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

public class AdminHomeActivity extends AppCompatActivity {
    private RelativeLayout manageadmins;
    private RelativeLayout managerequests;
    private RelativeLayout manageevents;
    private RelativeLayout managebenefits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepageadmin);

        manageadmins=findViewById(R.id.mngadmin);
        managebenefits=findViewById(R.id.mngmemberships);
        manageevents=findViewById(R.id.mngevents);
        managerequests=findViewById(R.id.mngrequests);


        manageadmins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHomeActivity.this, ManageAdminsHome.class);
                startActivity(intent);
            }
        });

        managerequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHomeActivity.this, ItemsRequestsHome.class);
                startActivity(intent);
            }
        });

        manageevents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHomeActivity.this, EventHome.class);
                startActivity(intent);
            }
        });

        managebenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminHomeActivity.this, MembershipHome.class);
                startActivity(intent);
            }
        });
    }
}
