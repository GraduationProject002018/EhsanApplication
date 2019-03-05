package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
private TextView LevelView;
private TextView donationlevel;
    private String level;
    private String value;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepagedonor);

        LevelView=findViewById(R.id.levelview);
        donationlevel=findViewById(R.id.donationlevel);
        progressBar=findViewById(R.id.level);

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
        Intent intent = new Intent(DonorHomeActivity.this, Aboutus.class);
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
                        level=child.child("donorlevel").getValue().toString();
                        value=child.child("donationvalue").getValue().toString();

                    }
                }

                if(found)
                {

                    username.setText("مرحبا"+" "+Donorname);
                    //LEVEL IN CIRCLE
                    donationlevel.setText(level+"/5");
                    //LEVEL NAME
                    LevelView.setText(returnlevelname(level));

                    showPrgoress(value,level);

Intent in=getIntent();
String p=in.getStringExtra("mobile");
Log.d("DonorHomeActivity","got from register:"+p);

                  }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showPrgoress(String v,String level) {
int value=Integer.parseInt(v);

switch (level){
    case "1":
        if(value>=1 && value<=2499){
            progressBar.setProgress(25);
        }
        else if(value>=2500 && value<=4999){
            progressBar.setProgress(50);
        }
        else if(value>=5000 && value<=7499){
            progressBar.setProgress(75);
        }
        else if(value>=7500 && value<=9999){
            progressBar.setProgress(100);
        }
        break;
    case "2":
        break;
    case "3":
        break;
    case "4":
        break;
    case "5":
        break;
    default:
        break;
        }

if(value>=1 && value<=2499){

}


    }

    public String returnlevelname(String level){
        String Lname="";
        String chosencolor="blue";

        if (level.equals("1"))
        {
            Lname="الأزرق";
            LevelView.setTextColor(Color.parseColor("#FFFFFF"));

        }
    if (level.equals("2"))
    {
        Lname="برونزي";
    }
    if (level.equals("3"))
    {
        Lname="فضي";
    }
    if (level.equals("4"))
    {
        Lname="ذهبي";
    }
    if (level.equals("5"))
    {
        Lname="بلاتيني";
    }

    return Lname;
}
}



