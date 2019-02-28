package com.example.ishzark.ehsanapp;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class firebaseDatabaseAdmins {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Admins> adminList = new ArrayList<>();


    public interface DataStatues {

        void DataAdmins(List<Admins> admin, List<String> keys);

    }
    public firebaseDatabaseAdmins() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Admins");
    }

    public void readEnterd(final firebaseDatabaseAdmins.DataStatues dataStatues) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                adminList.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot D : dataSnapshot.getChildren()) {
                    keys.add(D.getKey());
                    Admins admin = D.getValue(Admins.class);
                    adminList.add(admin);
                }
                dataStatues.DataAdmins(adminList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

