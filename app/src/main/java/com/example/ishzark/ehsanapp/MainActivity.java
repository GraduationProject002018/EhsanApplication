package com.example.ishzark.ehsanapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button RegButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        // Register Button Listener

        RegButton = findViewById(R.id.loginbtn3);

        RegButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openActivity();}

            public void openActivity(){
                Intent intent = new Intent(MainActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
