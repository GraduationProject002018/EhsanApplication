package com.example.ishzark.ehsanapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MembershipHome extends AppCompatActivity {
    private Button AddMembership;
    Intent intent1;
    private android.support.v7.widget.RecyclerView recyclerView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membershipmng);
        recyclerView = (android.support.v7.widget.RecyclerView) findViewById(R.id.recyclerV);

        new firebaseDatabaseMembership().readEnterd(new firebaseDatabaseMembership.DataStatues() {
            @Override
            public void DataMembership(List<Memberships> membership, List<String> keys) {
                new RecyclerViewMembership().setConfig(MembershipHome.this, recyclerView, membership, keys);
            }


        });

        AddMembership=(Button)findViewById(R.id.AddMembership);

        intent1 = new Intent(this, NewMembershipActivity.class);



        AddMembership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent1);
            }
        });

    }



}


