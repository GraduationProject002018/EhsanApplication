package com.example.ishzark.ehsanapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button RegButton;
private TextView Events;
private TextView About;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        // Register Button Listener

        RegButton = findViewById(R.id.donatebtn);

        RegButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity();
            }

            public void openActivity(){
                Intent intent = new Intent(MainActivity.this,
                        LoginActivity.class);
                startActivity(intent);
            }
        });

        Events=findViewById(R.id.events);
        About=findViewById(R.id.aboutalber);


        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        EventsActivityDonor.class);
                startActivity(intent);
            }
        });


        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        AboutAlberActivity.class);
                startActivity(intent);
            }
        });
    }
}
