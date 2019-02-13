package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoadingScreenActivity extends AppCompatActivity {

    private int TIMER=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadingscreen);
        Launcher launch = new Launcher();
        launch.start();

    }


    private class Launcher extends Thread{
        public void run(){
            try{
                sleep(TIMER);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent=new Intent(LoadingScreenActivity.this,MainActivity.class);
            startActivity(intent);
            LoadingScreenActivity.this.finish();
        }



    }
}
