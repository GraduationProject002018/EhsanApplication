package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DonorHomeActivity extends AppCompatActivity {
Button donateButton;
TextView username;
public String Donorname="";
private TextView eventsview;
private TextView aboutalberview;
private TextView benefitsview;
private TextView historyview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepagedonor);

donateButton=findViewById(R.id.donatebtn);
donateButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
     //   finish();
        Intent intent = new Intent(DonorHomeActivity.this, DonationTypeActivity.class);
        startActivity(intent);
    }
});

eventsview=findViewById(R.id.events);
aboutalberview=findViewById(R.id.aboutalber);
benefitsview=findViewById(R.id.benefits);
historyview=findViewById(R.id.history2);

eventsview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DonorHomeActivity.this, EventsActivityDonor.class);
        startActivity(intent);
    }
});

aboutalberview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DonorHomeActivity.this, AboutAlberActivity.class);
        startActivity(intent);
    }
});

benefitsview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        //Show pop up
    }
});
historyview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DonorHomeActivity.this,DonationHistoryAcitivty .class);
        startActivity(intent);
    }
});

username=findViewById(R.id.welcome);
        Intent intent = getIntent();
        String phone=intent.getStringExtra("mobile");
        Getname(phone);
        intent.putExtra("mobile",phone);
        intent.putExtra("donorname",Donorname);




    }

    public void Getname ( final String number){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Donors");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean found=false;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String phone = child.child("phone").getValue().toString();
                    Log.d("phone found",phone);
                    if (phone.equals(number)) {
                        found=true;
                        Donorname = child.child("name").getValue().toString();


                    }
                }

                if(found)
                {

                    username.setText("مرحبا"+" "+Donorname);

                }
                else
                {
                    Toast.makeText(DonorHomeActivity.this, getString(R.string.Phonedontexist), Toast.LENGTH_LONG).show();
                  }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}



