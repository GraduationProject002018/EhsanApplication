package com.example.ishzark.ehsanapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MembershipPop extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ListView membershiplist;
    private List<Memberships>membershipList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {

       // membershiplist=findViewById(R.id.memrecycler);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Memberships");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.membershipadapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<String> keys = new ArrayList<>();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                   // keys.add(D.getKey());
                    String name=child.child("type").getValue().toString();
                  String feature=child.child("feature").getValue().toString();
                    membershipList.add(new Memberships(name,feature));

                }
                recyclerView=findViewById(R.id.memrecycler);
                recyclerView.setHasFixedSize(true);
                layoutManager=new LinearLayoutManager(MembershipPop.this);
                adapter=new DonorfirebaseDatabaseMembership((ArrayList<Memberships>) membershipList);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

}