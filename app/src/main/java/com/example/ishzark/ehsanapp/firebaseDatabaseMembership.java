package com.example.ishzark.ehsanapp;
import android.support.annotation.NonNull;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
public class firebaseDatabaseMembership {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Memberships>membershipList = new ArrayList<>();


    public interface DataStatues {

void DataMembership(List<Memberships> membership, List<String> keys);

    }
    public firebaseDatabaseMembership() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Memberships");
    }

    public void readEnterd(final firebaseDatabaseMembership.DataStatues dataStatues) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                membershipList.clear();
                List<String> keys = new ArrayList<>();
                for (DataSnapshot D : dataSnapshot.getChildren()) {
                    keys.add(D.getKey());
                    Memberships membership = D.getValue(Memberships.class);
                    membershipList.add(membership);
                }
                dataStatues.DataMembership(membershipList, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
