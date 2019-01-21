package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.google.firebase.auth.FirebaseAuth;


public class PhoneAuth extends AppCompatActivity {
    private ProgressBar progressBar;

    private EditText editTextMobile;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
private String userslocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_verify_phone);

        editTextMobile = findViewById(R.id.numberinput);

        findViewById(R.id.verifynumbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = editTextMobile.getText().toString().trim();


                if (mobile.isEmpty() || mobile.length() < 9) {
                    editTextMobile.setError("الرجاء ادخال رقم جوال مكون من ٩ ارقام");
                    editTextMobile.requestFocus();

                }
                Intent intent = new Intent(PhoneAuth.this, VerifyPhoneActivity.class);
                intent.putExtra("mobile", mobile);
               // userslocation="https://ehsan-c48bc.firebaseio.com/";
                checkNumber(mobile);
                startActivity(intent);

            }
        });




    }
    public void checkNumber (final String number) {

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Donors");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Intent restart=getIntent();
                for (DataSnapshot child:dataSnapshot.getChildren()){
                    String phone= (String) child.child("phone").getValue();

                    if(phone.equals(number)){
                        Toast.makeText(PhoneAuth.this, getString(R.string.Phoneregistration_exsist), Toast.LENGTH_LONG).show();
                        finish();
                        startActivity(restart);
                    }

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });}
    }




