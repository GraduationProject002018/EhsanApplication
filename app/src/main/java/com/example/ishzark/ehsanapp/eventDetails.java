package com.example.ishzark.ehsanapp;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class eventDetails extends AppCompatActivity {
    private EditText ModifyTitle;
    private EditText ModifyDate;
    private EditText ModifyDesc;
    private Button UpdateBtn;
    private Button DeleteBtn;
    private ProgressBar progressBar;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    Events event = new Events();
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    String key, title, date, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_edit);
        progressBar = findViewById(R.id.progressBar5);
        progressBar.setVisibility(View.GONE);
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


///////////////////////////////////Date Picker ///////////////////////////


        ModifyDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance(new Locale("@calendar=islamic-umalqura"));
                //IslamicCalendar cal=IslamicCalendar.getInstance(locale);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                // System.currentTimeMillis();
                DatePickerDialog dialog = new DatePickerDialog(
                        eventDetails.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                ModifyDate.setText(date);
            }
        };


        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Events event = new Events();
                event.setTitle(ModifyTitle.getText().toString());
                event.setDate(ModifyDate.getText().toString());
                event.setDescription(ModifyDesc.getText().toString());

               Update(key, event); }

            public void Update(String key, Events event) {
                progressBar.setVisibility(View.VISIBLE);
                databaseReference.child(key).setValue(event);

                Toast.makeText(eventDetails.this, " تم تحديث الحدث", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                finish();
            }

        });

        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(key);}


            public void Delete(String key) {
                databaseReference.child(key).setValue(null);


                Toast.makeText(eventDetails.this, " تم حذف الحدث", Toast.LENGTH_LONG).show();
                finish();


            }

        });


    }
}
