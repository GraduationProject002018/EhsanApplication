package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class ManageAdminsHome extends AppCompatActivity {
    private Button AddAdminBtn;
    Intent intent;
    private android.support.v7.widget.RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminmng);
        recyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.recyclerV);
        new firebaseDatabaseAdmins().readEnterd(new firebaseDatabaseAdmins.DataStatues() {
            @Override
            public void DataAdmins(List<Admins> admin, List<String> keys) {
new RecyclerAdmin().setConfig(ManageAdminsHome.this, recyclerView, admin, keys);
            }
        });



        AddAdminBtn=(Button)findViewById(R.id.addadminbtn);

        intent = new Intent(this, NewMngAdmin.class);



        AddAdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

    }
}
