package com.example.ishzark.ehsanapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ShowImageActivity extends AppCompatActivity  {
    private RecyclerView mRecyclerView;
    private MyAdapter myAdapter;

    private ProgressBar ProgressCircle;

    private FirebaseStorage firebaseStorage;
    private DatabaseReference DatabaseRef;
    private ValueEventListener DBListener;


    private List<DonateItems> mUploads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showimagesactivity);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//arraylist object creation
        mUploads = new ArrayList<>();
//assign list of objects to adapter
        myAdapter = new MyAdapter(ShowImageActivity.this, mUploads);
//link the recyecl view with adapter
        mRecyclerView.setAdapter(myAdapter);


        firebaseStorage = FirebaseStorage.getInstance();
        DatabaseRef = FirebaseDatabase.getInstance().getReference("Items");

        DBListener = DatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                mUploads.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    DonateItems upload = ds.getValue(DonateItems.class);
                   // upload.setKey(ds.getKey());
                    mUploads.add(upload);
                }

               // String ImgUrl = DatabaseRef.child("url").getValue().toString();
               // Log.d(TAG, "URL found " + ImgUrl);
//update the adapter by changes
                myAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ShowImageActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}