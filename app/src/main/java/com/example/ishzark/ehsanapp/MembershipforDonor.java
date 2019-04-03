package com.example.ishzark.ehsanapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MembershipforDonor extends AppCompatActivity {
TextView blue;
TextView bronze;
TextView gold;
TextView plat;
TextView silver;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    protected void onCreate(Bundle savedInstanceState) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Memberships");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.membershippopwindow);
        blue=findViewById(R.id.benefits0);
        bronze=findViewById(R.id.benefits1);
        silver=findViewById(R.id.benefits2);
        gold=findViewById(R.id.benefits3);
        plat=findViewById(R.id.benefits4);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    // keys.add(D.getKey());
                    String name=child.child("type").getValue().toString();
                    String feature=child.child("feature").getValue().toString();
                    if(name.equals("أزرق")){
                        blue.setText(feature);
                    }
                    else if(name.equals("برونزي")){
                        bronze.setText(feature);

                    } else if(name.equals("فضي")){
                        silver.setText(feature);

                    } else if(name.equals("ذهبي")){
                        gold.setText(feature);

                    } else if(name.equals("بلاتيني")){
                        plat.setText(feature);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}