package com.example.ishzark.ehsanapp;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class NewEventActivity extends AppCompatActivity {
private EditText EvTitle;
private EditText EvDate;
private EditText EvDescription;
private Button Savebtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    Events event = new Events();
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ProgressBar progressBar;
    //notification variables
    public static final String EVENTS_CHANNEL_ID = "فعالية جديدة";
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventnew);
        progressBar = findViewById(R.id.progressBar6);
        progressBar.setVisibility(View.GONE);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Events");

        EvTitle=(EditText)findViewById(R.id.EventTitleInput);
        EvDate=(EditText)findViewById(R.id.EventDateInput);
        EvDescription=(EditText)findViewById(R.id.EventDescInput);
        Savebtn=(Button)findViewById(R.id.eventsubmit);

        //notifications stuff
        createNotificationChannels();
        notificationManager = NotificationManagerCompat.from(this);

///////////////////////////////////Date Picker ///////////////////////////


        EvDate.setOnClickListener(new View.OnClickListener() {
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
                        NewEventActivity.this,
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
                EvDate.setText(date);
            }
        };


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
                        EvTitle.setError("الرجاء إدخال اسم الحدث");
                        EvTitle.requestFocus();
                    } else if (Date.isEmpty()) {
                        EvDate.setError("الرجاء إدخال تاريخ الحدث");
                        EvDate.requestFocus();
                    } else if (Description.isEmpty()) {
                        EvDescription.setError("الرجاء إدخال تفاصيل الحدث");
                        EvDescription.requestFocus();

                    } else {
                        progressBar.setVisibility(View.VISIBLE);


                        String key = databaseReference.push().getKey();
                        Events event= new Events(Title,Date,Description);
                        databaseReference.child(key).setValue(event);

                        Toast.makeText(NewEventActivity.this, " تم إضافةالحدث", Toast.LENGTH_LONG).show();

                        //sending notification
                        sendOnEventsChannel();
                   finish();
                        progressBar.setVisibility(View.GONE);
                    }

                }

                }

        );
    }

    //creating notification channel... required for api 28 (oreo) & up
    private void createNotificationChannels(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel eventschannel = new
                    NotificationChannel(
                    EVENTS_CHANNEL_ID,
                    "Events Notification",
                    NotificationManager.IMPORTANCE_HIGH
            );
            eventschannel.setDescription("هذه الإعدادات لتنبيهات الفعاليات المضافة حديثا");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(eventschannel);

        }
    }

    //sending the notification
    public void sendOnEventsChannel(){

        Intent activityIntent = new Intent(this, DonorHomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, EVENTS_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_event_notification)
                .setContentTitle("فعالية جديدة")
                .setContentText(EvDescription.getText().toString())
                .setColor(Color.GREEN)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1, notification);

    }
}


