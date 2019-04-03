package com.example.ishzark.ehsanapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DonationHistoryActivity2 extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase Ehsan;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    DonateItems items ;
    private FirebaseAuth auth;
    String TAG="DonationHistoryAct2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donationhistory);
        items=new DonateItems();

        listView = (ListView) findViewById(R.id.listView);
        Ehsan = FirebaseDatabase.getInstance();
        ref=Ehsan.getReference("Items");
        list= new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.donation_info,R.id.textView6,list);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        final String mobile=user.getPhoneNumber();

        Log.d(TAG,"phone found"+mobile.replace("+966",""));




        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot ds: dataSnapshot.getChildren())
                {
                    String p = ds.child("phone").getValue().toString();
                    Log.d(TAG, "Fphone found " + p);
                    if(p.equals(mobile))
                    {
                        items = ds.getValue(DonateItems.class);

                        list.add(items.getItem_type().toString() + " - حالة الطلب : "+ items.getRequestStatus());


                    } //end for
                    listView.setAdapter(adapter);
                }}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}