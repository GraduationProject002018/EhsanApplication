package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class EventHome extends AppCompatActivity {



private RelativeLayout ListEvent;
private RelativeLayout AddEvent;
Intent intent1,intent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventmng);
        ListEvent= findViewById(R.id.ListEventBtn);
        AddEvent= findViewById(R.id.AddEventBtn);
        intent1 = new Intent(this, RecyclerEvent.class);
        intent2 = new Intent(this, NewEventActivity.class);


        ListEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });
        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent2);
            }
        });
    }
}
