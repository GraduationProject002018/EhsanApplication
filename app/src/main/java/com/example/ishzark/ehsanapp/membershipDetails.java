package com.example.ishzark.ehsanapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class membershipDetails extends AppCompatActivity {
    private EditText ModifyType;
    private EditText ModifyFeature;

    private Button UpdateBtn;
    private Button DeleteBtn;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    //Memberships  membership = new Memberships();

    String key, type, feature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.membership_edit);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Memberships");

        key = getIntent().getStringExtra("key");
        type = getIntent().getStringExtra("type");
        feature = getIntent().getStringExtra("feature");


        ModifyType = (EditText) findViewById(R.id.edType);
        ModifyFeature = (EditText) findViewById(R.id.edFeature);

        ModifyType.setText(type);
        ModifyFeature.setText(feature);


        UpdateBtn = (Button) findViewById(R.id.UpdateBtn);
        DeleteBtn = (Button) findViewById(R.id.DeletBtn);


        UpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Memberships  membership = new Memberships();
                membership.setType(ModifyType.getText().toString());
                membership.setFeature(ModifyFeature.getText().toString());


                Update(key, membership); }

            public void Update(String key, Memberships membership) {
                databaseReference.child(key).setValue(membership);

                Toast.makeText(membershipDetails.this, " العضويه تم تحديثها", Toast.LENGTH_LONG).show();

            }






        });

        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(key);}


            public void Delete(String key) {
                //databaseReference.child(key).setValue(null); for change dont use it aseel
                databaseReference.child(key).removeValue();

                Toast.makeText(membershipDetails.this, "العضويه تم حذفها", Toast.LENGTH_LONG).show();
                finish();


            }

        });


    }
}

