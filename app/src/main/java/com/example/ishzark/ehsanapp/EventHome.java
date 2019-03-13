package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.List;

public class EventHome extends AppCompatActivity {

    private android.support.v7.widget.RecyclerView recyclerView;

    private RelativeLayout ListEvent;
    private RelativeLayout AddEvent;
Intent intent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventmng);

        ListEvent= findViewById(R.id.ListEventBtn);
        AddEvent= findViewById(R.id.AddEventBtn);

        intent1 = new Intent(this, NewEventActivity.class);


        recyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.recyclerV);

        new firebaseDatabaseActivity().readEvents(new firebaseDatabaseActivity.DataStatues() {
            @Override
            public void DataLoaded(List<Events> event, List<String> keys) {

                new RecyclerViewActivity().setConfig(recyclerView, EventHome.this, event, keys);
            }




        });
        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });







    }














}
