package com.example.ishzark.ehsanapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewMngAdmin extends AppCompatActivity {

    private EditText AdminNameInput;
    private EditText AdminNumberInput;
    private Button Save;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;
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

                                           } else if (Phone.isEmpty()) {
                                               AdminNumberInput.setError("الرجاء إدخال رقم المشرف");
                                               AdminNumberInput.requestFocus();
                                               progressBar.setVisibility(View.GONE);

                                           } else {


                                               String key = databaseReference.push().getKey();
                                               Admins admin =new Admins(Name,Phone);
                                               databaseReference.child(key).setValue(admin);

                                               Toast.makeText(NewMngAdmin.this, "تمت إضافة المشرف الجديد ", Toast.LENGTH_LONG).show();

                                               finish();
                                               progressBar.setVisibility(View.GONE);
                                           }

                                       }

                                   }

        );}


}
