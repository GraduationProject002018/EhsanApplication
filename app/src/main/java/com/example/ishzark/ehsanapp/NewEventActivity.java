package com.example.ishzark.ehsanapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
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

    //notification variables
    public static final String EVENTS_CHANNEL_ID = "فعالية جديدة";
    private NotificationManagerCompat notificationManager;


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

        //notifications stuff
        createNotificationChannels();
        notificationManager = NotificationManagerCompat.from(this);


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

                        //sending notification
                        sendOnEventsChannel();
                   finish(); }

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
                .setContentText(EvDate.getText().toString())
                .setColor(Color.GREEN)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(1, notification);

    }
}


