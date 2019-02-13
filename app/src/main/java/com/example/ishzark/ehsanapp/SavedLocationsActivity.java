package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SavedLocationsActivity extends AppCompatActivity {
private RelativeLayout newlocation;
private ListView locationlist;
private ArrayList<String>list;
private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savedlocations);


        Intent intent = getIntent();
        String phone=intent.getStringExtra("mobile");
        intent.putExtra("mobile",phone);

        newlocation=findViewById(R.id.newlocationlayout);
        newlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedLocationsActivity.this, pickLocationActivity.class);
                startActivity(intent);
            }
        });
/////////////////////////Retrive Location List////////////////////////////////////////////////
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Donors");

        locationlist=findViewById(R.id.LocationListView);
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.locationlisttext,list);
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        String Userid=currentFirebaseUser.getUid();

        DatabaseReference loc= ref.child(Userid).child("Location");

        loc.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren())
                {
                    String loct=ds.getKey().toString();
                    list.add(loct);
                }
                locationlist.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


locationlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String chosencity=locationlist.getItemAtPosition(position).toString();
        Intent intent = new Intent(SavedLocationsActivity.this, ItemDonationActivity.class);
        intent.putExtra("chosencity",chosencity);
        String Donorname=intent.getStringExtra("donorname");
        intent.putExtra("donorname",Donorname);
        finish();
        startActivity(intent);
    }
});
/////////////////////////End of Retrive Location List////////////////////////////////////////////////
    }
}