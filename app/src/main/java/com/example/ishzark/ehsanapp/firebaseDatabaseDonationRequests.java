package com.example.ishzark.ehsanapp;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class firebaseDatabaseDonationRequests {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<DonateItems> RList = new ArrayList<>();


    public interface DataStatues {

        void DataDR(List<DonateItems> items, List<String> keys);

    }
    public firebaseDatabaseDonationRequests() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Items");
    }

    public void readrequests(final DataStatues dataStatues) {


        Query applyQuery = databaseReference.orderByChild("requestStatus").equalTo("معلق");
                applyQuery.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                RList.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot D : dataSnapshot.getChildren()) {
                    keys.add(D.getKey());
                    DonateItems items = D.getValue(DonateItems.class);
                    RList.add(items);
                }
                dataStatues.DataDR(RList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
