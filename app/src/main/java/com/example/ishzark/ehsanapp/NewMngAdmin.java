package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewMngAdmin extends AppCompatActivity {

    private EditText AdminNameInput;
    private EditText AdminNumberInput;
    private Button Save;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;
    private static final String TAG = "PhoneAuth";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminnew);
       progressBar = findViewById(R.id.progressBar7);
        progressBar.setVisibility(View.GONE);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Admins");

        AdminNameInput=(EditText)findViewById(R.id.AdminNameInput);
        AdminNumberInput=(EditText)findViewById(R.id.AdminNumberInput);

        Save=(Button)findViewById(R.id.Save);


        Save.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {


                                           Add();
                                       }

                                       public void Add( ){
                                           progressBar.setVisibility(View.VISIBLE);
                                           String Name=AdminNameInput.getText().toString();
                                           String Phone =AdminNumberInput.getText().toString();



                                           if (Name.isEmpty()) {
                                               AdminNameInput.setError("الرجاء إدخال اسم المشرف");
                                               AdminNameInput.requestFocus();
                                               progressBar.setVisibility(View.GONE);

                                           } else if (Phone.isEmpty()|| Phone.length() < 9) {
                                               AdminNumberInput.setError("الرجاء ادخال رقم جوال مكون من ٩ ارقام");
                                               AdminNumberInput.requestFocus();
                                               progressBar.setVisibility(View.GONE);

                                           } else {

                                               checkNumber(Phone,Name);

                                           }

                                       }
           public void checkNumber (final String number, final String name) {
                progressBar.setVisibility(View.VISIBLE);
                DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("Admins");

                ref.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean found=false;
                        Intent restart=getIntent();
                        for (DataSnapshot child:dataSnapshot.getChildren()){
                            String phone= child.child("phone").getValue().toString();
                            Log.d(TAG,"phone found"+phone);
                            if(phone.equals(number)){
                                found=true;
                            }
                        }
                        if(found){
                            progressBar.setVisibility(View.GONE);
                            Log.d(TAG,"number is "+number);
                            Toast.makeText(NewMngAdmin.this, "الرقم المدخل مسجل مسبقاً يرجى ادخال رقم اخر", Toast.LENGTH_LONG).show();
                            startActivity(restart);
                            finish();
                        }else {
                            Admins admin =new Admins(name,number);
                            String key = databaseReference.push().getKey();
                            databaseReference.child(key).setValue(admin);

                            Toast.makeText(NewMngAdmin.this, "تمت إضافة المشرف الجديد ", Toast.LENGTH_LONG).show();

                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(NewMngAdmin.this, NewMngAdmin.class);
                            intent.putExtra("mobile", number);
                            finish();
                            startActivity(intent);
                        }





                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });}

                                   }

        );}


}
