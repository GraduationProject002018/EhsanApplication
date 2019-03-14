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

public class adminDetails extends AppCompatActivity {
    private EditText ModifyNumber;
    private EditText ModifyName;

    private Button UpdateBtn;
    private Button DeleteBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;
    //Memberships  membership = new Memberships();

    String key, name, number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_edit);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Admins");
        progressBar = findViewById(R.id.progressBar8);
        progressBar.setVisibility(View.GONE);
        key = getIntent().getStringExtra("key");
        name = getIntent().getStringExtra("name");
        number = getIntent().getStringExtra("phone");


        ModifyName= (EditText) findViewById(R.id.ModifyName);
        ModifyNumber = (EditText) findViewById(R.id.ModifyNumber);

        ModifyName.setText(name);
        ModifyNumber.setText(number);


        UpdateBtn = (Button) findViewById(R.id.UpdateAdmin);
        DeleteBtn = (Button) findViewById(R.id.DeleteAdmin);


        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Admins admin = new Admins();
                admin.setName(ModifyName.getText().toString());
                admin.setPhone(ModifyNumber.getText().toString());


                Update(key, admin); }

            public void Update(String key, Admins admin) {
                progressBar.setVisibility(View.VISIBLE);
                databaseReference.child(key).setValue(admin);

                Toast.makeText(adminDetails.this, " تم تحديث المشرف", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                finish();
            }






        });

        DeleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Delete(key);}


            public void Delete(String key) {
                progressBar.setVisibility(View.VISIBLE);

                databaseReference.child(key).removeValue();

                Toast.makeText(adminDetails.this, " تم حذف المشرف", Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
                finish();


            }

        });


    }
}
