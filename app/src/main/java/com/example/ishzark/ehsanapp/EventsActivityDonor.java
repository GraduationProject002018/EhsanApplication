package com.example.ishzark.ehsanapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import java.util.List;

public class EventsActivityDonor extends AppCompatActivity {
    private android.support.v7.widget.RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_event);

        recyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.recyclerV);

        new firebaseDatabaseActivity().readEvents(new firebaseDatabaseActivity.DataStatues() {
            @Override
            public void DataLoaded(List<Events> event, List<String> keys) {

                new RecyclerViewActivity().setConfig(recyclerView, EventsActivityDonor.this, event, keys);
            }




        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                return true;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

    }


}
