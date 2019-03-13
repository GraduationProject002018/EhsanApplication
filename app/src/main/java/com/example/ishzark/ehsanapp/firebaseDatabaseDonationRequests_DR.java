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

public class firebaseDatabaseDonationRequests_DR {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<PrepaidInvoice> RList = new ArrayList<>();


    public interface DataStatues {

        void DataInvoice(List<PrepaidInvoice> pre, List<String> keys);

    }
    public firebaseDatabaseDonationRequests_DR() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Invoices");
    }

    public void readrequests(final DataStatues dataStatues) {

        Query applyQuery = databaseReference.orderByChild("request_status").equalTo("معلق");

        applyQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RList.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot D : dataSnapshot.getChildren()) {
                    keys.add(D.getKey());
                    PrepaidInvoice pre = D.getValue(PrepaidInvoice.class);
                    RList.add(pre);
                }
                dataStatues.DataInvoice(RList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
