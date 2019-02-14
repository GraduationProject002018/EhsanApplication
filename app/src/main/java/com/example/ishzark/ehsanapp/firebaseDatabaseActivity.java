package com.example.ishzark.ehsanapp;

import android.support.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class firebaseDatabaseActivity {

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Events> eventsList = new ArrayList<>();


    public interface DataStatues {
        void DataLoaded(List<Events> event, List<String> keys);
//void DataL(List<Memberships> membership, List<String> keys);

    }

    public firebaseDatabaseActivity() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Events");
    }

    public void readEvents(final DataStatues dataStatues) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventsList.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot D : dataSnapshot.getChildren()) {
                    keys.add(D.getKey());
                    Events event = D.getValue(Events.class);
                    eventsList.add(event);
                }
                dataStatues.DataLoaded(eventsList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }






    }

