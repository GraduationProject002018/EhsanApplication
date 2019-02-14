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
    Button email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutus);
email=findViewById(R.id.email);
instagram=findViewById(R.id.instagram);
twitter=findViewById(R.id.twitter);
youtube=findViewById(R.id.youtube);
        //Instagram

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instaintent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");

                instaintent.setComponent(new ComponentName("com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
                instaintent.setData(Uri.parse("https://www.instagram.com/_u/bitter_truth_lol"));

                //startActivity(instaintent);
            }
        });

        //Youtube

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ytintent = getPackageManager().getLaunchIntentForPackage("com.youtube.android");

                ytintent.setComponent(new ComponentName("com.youtube.android", "com.youtube.android.activity.UrlHandlerActivity"));
                ytintent.setData(Uri.parse("https://www.youtube.com/channel/UC_vslRzEZads8yhpO5wkUzA"));

              // startActivity(ytintent);
            }
        });

        //Twitter

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent twitterintent = getPackageManager().getLaunchIntentForPackage("com.youtube.android");

                twitterintent.setComponent(new ComponentName("com.twitter.android", "com.twitter.android.activity.UrlHandlerActivity"));
                twitterintent.setData(Uri.parse("https://twitter.com/AlberSharqia"));

                //startActivity(twitterintent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendemailbtn = new Intent(Aboutus.this, SendEmail.class);
                startActivity(sendemailbtn);

            }
        });
    }
}
