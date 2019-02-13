package com.example.ishzark.ehsanapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends Activity {
    private Button LoginButton;
    private EditText phone;
    private EditText editTextMobile;
private Button  RegisterButton;
public String Usertype;
public boolean found=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        LoginButton = findViewById(R.id.verifynumbtn);
        editTextMobile = findViewById(R.id.loginnumberinput);
        RegisterButton=findViewById(R.id.loginbtn3);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,PhoneAuth.class);
                startActivity(intent);
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String mobile = editTextMobile.getText().toString().trim();


                if (mobile.isEmpty() || mobile.length() < 9) {
                    editTextMobile.setError("الرجاء ادخال رقم جوال مكون من ٩ ارقام");
                    editTextMobile.requestFocus();

                }


                checkNumber(mobile);
            }


        });
    }
        public void checkNumber ( final String number){


            DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("SuperAdmin");

            ref1.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Intent intent = new Intent(LoginActivity.this, loginverifyphone.class);
                    intent.putExtra("mobile",number);

                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        String phone = child.getValue().toString();
                        Log.d("phone found",phone);
                        if (phone.equals(number)) {
                            found=true;


                        }
                    }

                    if(found)
                    {  Usertype="SuperAdmin";
                    intent.putExtra("usertype",Usertype);
                        finish();
                        startActivity(intent);
                    }
                    else
                    {
                        checkifAdmin(number);

                    }

        }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }


            });}

    private void checkifAdmin(final String number) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Admins");

                ref.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Intent intent = new Intent(LoginActivity.this, loginverifyphone.class);
                        intent.putExtra("mobile",number);
                        boolean found=false;
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            String phone = child.child("phone").getValue().toString();
                            Log.d("phone found",phone);
                            if (phone.equals(number)) {
                                found=true;


                            }
                        }

                        if(found)
                        {Usertype="Admin";
                            intent.putExtra("usertype",Usertype);
                            finish();
                            startActivity(intent);}
                        else
                        {
                            checkifDonor(number);
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }

    private void checkifDonor(final String number) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Donors");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Intent restart=getIntent();
                Intent intent = new Intent(LoginActivity.this, loginverifyphone.class);
                intent.putExtra("mobile",number);
                boolean found=false;
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String phone = child.child("phone").getValue().toString();
                    Log.d("phone found",phone);
                    if (phone.equals(number)) {
                        found=true;


                    }
                }

                if(found)
                {Usertype="Donor";
                    intent.putExtra("usertype",Usertype);
                    finish();
                    startActivity(intent);}
                else
                {Toast.makeText(LoginActivity.this, getString(R.string.Phonedontexist), Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(restart);}


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


}