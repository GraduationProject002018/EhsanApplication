package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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
    ConstraintLayout donateButton;
    TextView username;
    public String Donorname="";
    private ConstraintLayout eventsview;
    private ConstraintLayout aboutalberview;
    private ConstraintLayout benefitsview;
    private ConstraintLayout historyview;
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

        eventsview=findViewById(R.id.eventslayout);
        aboutalberview=findViewById(R.id.aboutuslayout);
        benefitsview=findViewById(R.id.membershiplayout);
        historyview=findViewById(R.id.historylayout);

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
                Intent intent = new Intent(DonorHomeActivity.this, MembershipforDonor.class);
                startActivity(intent);
            }
        });
        historyview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DonorHomeActivity.this,DonationHistoryTypes.class);
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

                    username.setText(Donorname);
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
                    progressBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.BLUE));
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
                if(value>=10000 && value<=24999){
                    progressBar.setProgress(25);
                }
                else if(value>=25000 && value<=49999){
                    progressBar.setProgress(50);
                }
                else if(value>=50000 && value<=74999){
                    progressBar.setProgress(75);
                }
                else if(value>=75000 && value<=99999){
                    progressBar.setProgress(100);
                }
                break;
            case "3":
                if(value>=100000 && value<=114999){
                    progressBar.setProgress(25);
                }
                else if(value>=115000 && value<=124999){
                    progressBar.setProgress(50);
                }
                else if(value>=125000 && value<=187499){
                    progressBar.setProgress(75);
                }
                else if(value>=187500 && value<=249999){
                    progressBar.setProgress(100);
                }
                break;
            case "4":
                if(value>=250000 && value<=374999){
                    progressBar.setProgress(25);
                }
                else if(value>= 315499&& value<=375000){
                    progressBar.setProgress(50);
                }
                else if(value>=315500 && value<=374999){
                    progressBar.setProgress(75);
                }
                else if(value>=375000 && value<= 499999){
                    progressBar.setProgress(100);
                }
                break;
            case "5":
                if(value>=500000 && value<=2499999){
                    progressBar.setProgress(25);
                }
                else if(value>=2500000 && value<=4999999){
                    progressBar.setProgress(50);
                }
                else if(value>=5000000 && value<=7499999){
                    progressBar.setProgress(75);
                }
                else if(value>=7500000 && value<= 10000000){
                    progressBar.setProgress(100);
                }

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
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#3fb7ff"), PorterDuff.Mode.SRC_IN);
            LevelView.setTextColor(Color.parseColor("#3fb7ff"));

        }
        if (level.equals("2"))
        {
            Lname="برونزي";
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#d28c47"), PorterDuff.Mode.SRC_IN);
            LevelView.setTextColor(Color.parseColor("#d28c47"));
        }
        if (level.equals("3"))
        {
            Lname="فضي";
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#C0C0C0"), PorterDuff.Mode.SRC_IN);
            LevelView.setTextColor(Color.parseColor("#C0C0C0"));
        }
        if (level.equals("4"))
        {
            Lname="ذهبي";
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#cba135"), PorterDuff.Mode.SRC_IN);
            LevelView.setTextColor(Color.parseColor("#cba135"));
        }
        if (level.equals("5"))
        {
            Lname="بلاتيني";
            progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#e5e4e2"), PorterDuff.Mode.SRC_IN);
            LevelView.setTextColor(Color.parseColor("#e5e4e2"));

        }

        return Lname;
    }
}



