package com.example.ishzark.ehsanapp;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Aboutus extends AppCompatActivity {
    Button instagram;
    Button twitter;
    Button youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent instaintent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");

                instaintent.setComponent(new ComponentName( "com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
                instaintent.setData( Uri.parse( "https://www.instagram.com/_u/bitter_truth_lol") );

                startActivity(instaintent);

            }
        });


    }
}
