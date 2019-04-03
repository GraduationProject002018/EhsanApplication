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
                Uri instawebpage = Uri.parse("https://www.instagram.com/asharqiaber/?hl=en");
                Intent instaintent = new Intent(Intent.ACTION_VIEW, instawebpage);
                startActivity(instaintent);
            }
        });

        //Youtube

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri ytwebpage = Uri.parse("https://www.youtube.com/channel/UC_vslRzEZads8yhpO5wkUzA");
                Intent ytintent = new Intent(Intent.ACTION_VIEW, ytwebpage);
                startActivity(ytintent);
            }
        });

        //Twitter

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri twitterwebpage = Uri.parse("https://twitter.com/AlberSharqia");
                Intent twitterintent = new Intent(Intent.ACTION_VIEW, twitterwebpage);
                startActivity(twitterintent);
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
