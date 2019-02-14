package com.example.ishzark.ehsanapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class NewEventActivity extends AppCompatActivity {
private EditText EvTitle;
private EditText EvDate;
private EditText EvDescription;
private Button Savebtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    Events event = new Events();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventnew);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Events");

        EvTitle=(EditText)findViewById(R.id.EventTitleInput);
        EvDate=(EditText)findViewById(R.id.EventDateInput);
        EvDescription=(EditText)findViewById(R.id.EventDescInput);
        Savebtn=(Button)findViewById(R.id.eventsubmit);


        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Add();
            }

                public void Add( ){

                    String Title= EvTitle.getText().toString();
                    String Date= EvDate.getText().toString();
                    String Description= EvDescription.getText().toString();


                    if (Title.isEmpty()) {
                        Toast.makeText(NewEventActivity.this, "الرجاء إدخال اسم الحدث", Toast.LENGTH_LONG).show();
                    } else if (Date.isEmpty()) {
                        Toast.makeText(NewEventActivity.this, "الرجاء إدخال تاريخ الحدث", Toast.LENGTH_LONG).show();
                    } else if (Description.isEmpty()) {
                        Toast.makeText(NewEventActivity.this, "الرجاء إدخال تفاصيل الحدث", Toast.LENGTH_LONG).show();
                    } else {


                        String key = databaseReference.push().getKey();
                        Events event= new Events(Title,Date,Description);
                        databaseReference.child(key).setValue(event);

                        Toast.makeText(NewEventActivity.this, "الحدث تم إضافته", Toast.LENGTH_LONG).show();

                   finish(); }

                }

                }

        );}


    }


