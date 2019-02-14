package com.example.ishzark.ehsanapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
public class NewMembershipActivity extends AppCompatActivity {
    private EditText MembershipType;
    private EditText MembershipFeaure;
    private Button SaveBtn, AddFeatureBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    Memberships membership = new Memberships();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membershipnew);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Memberships");

        MembershipType=(EditText)findViewById(R.id.MembershipType);
        MembershipFeaure=(EditText)findViewById(R.id.MembershipFeature);

        SaveBtn=(Button)findViewById(R.id.SaveBtn);


        SaveBtn.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {


                                           Add();
                                       }

                                       public void Add( ){
                                           String Type=MembershipType.getText().toString();
                                           String Feature =MembershipFeaure.getText().toString();




                                           if (Type.isEmpty()) {
                                               Toast.makeText(NewMembershipActivity.this, "الرجاء إدخال اسم العضوية", Toast.LENGTH_LONG).show();
                                           } else if (Feature.isEmpty()) {
                                               Toast.makeText(NewMembershipActivity.this, "الرجاء إدخال مميزات العضوية", Toast.LENGTH_LONG).show();
                                           } else {


                                               String key = databaseReference.push().getKey();
                                              Memberships membership=new Memberships(Type,Feature);
                                               databaseReference.child(key).setValue(membership);

                                               Toast.makeText(NewMembershipActivity.this, "العضوية تم إضافتها ", Toast.LENGTH_LONG).show();

                                               finish(); }

                                       }

                                   }

        );}



}
