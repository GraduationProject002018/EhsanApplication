package com.example.ishzark.ehsanapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class eventDetails extends AppCompatActivity {
    private EditText ModifyTitle;
    private EditText ModifyDate;
    private EditText ModifyDesc;
    private Button UpdateBtn;
    private Button DeleteBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    Events event = new Events();

    String key, title, date, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_edit);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Events");

        key = getIntent().getStringExtra("key");
        title = getIntent().getStringExtra("title");
        date = getIntent().getStringExtra("date");
        desc = getIntent().getStringExtra("Desc");

        ModifyTitle = (EditText) findViewById(R.id.edTitleID);
        ModifyTitle.setText(title);
        ModifyDate = (EditText) findViewById(R.id.edEventDateId);
        ModifyDate.setText(date);
        ModifyDesc = (EditText) findViewById(R.id.edeventDescId);
        ModifyDesc.setText(desc);

        UpdateBtn = (Button) findViewById(R.id.UpdatebtnId);
        DeleteBtn = (Button) findViewById(R.id.deletbtnId);


        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Events event = new Events();
                event.setTitle(ModifyTitle.getText().toString());
                event.setDate(ModifyDate.getText().toString());
                event.setDescription(ModifyDesc.getText().toString());

               Update(key, event); }

            public void Update(String key, Events event) {
                databaseReference.child(key).setValue(event);

                Toast.makeText(eventDetails.this, "الحدث تم تحديثه", Toast.LENGTH_LONG).show();

            }






        });

        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(key);}


            public void Delete(String key) {
                databaseReference.child(key).setValue(null);


                Toast.makeText(eventDetails.this, "الحدث تم حذفه", Toast.LENGTH_LONG).show();
                finish();


            }

        });


    }
}
