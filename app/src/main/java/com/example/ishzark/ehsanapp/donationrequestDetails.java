package com.example.ishzark.ehsanapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class donationrequestDetails extends AppCompatActivity {

    private TextView DonorName;
    private TextView Phone;
    private TextView Itemtype;
    private TextView itemstatus;
    private TextView Quantity;
    //private EditText pic;
    //private EditText location;


    private Button Accept;
    private Button Decline;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    //Memberships  membership = new Memberships();

    String key, donor_name, item_status,item_type, location,phone,quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminrequestview);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Items");

        key = getIntent().getStringExtra("key");
        donor_name = getIntent().getStringExtra("donor_name");
        item_type = getIntent().getStringExtra("item_type");
        item_status = getIntent().getStringExtra("item_status");
        location = getIntent().getStringExtra("location");
        phone = getIntent().getStringExtra("phone");
        quantity = getIntent().getStringExtra("quantity");




        DonorName = (TextView) findViewById(R.id.donorname);
        Phone = (TextView) findViewById(R.id.phone);
        Itemtype= (TextView) findViewById(R.id.itemtype);
        itemstatus= (TextView) findViewById(R.id.itemstatus);
        Quantity= (TextView) findViewById(R.id.quantity);
        // location= (TextView) findViewById(R.id.location);
        // pic= (TextView) findViewById(R.id.pic);

        DonorName.setText(donor_name);
        Phone.setText(phone);
        Itemtype.setText(item_type);
        itemstatus.setText(item_status);
        Quantity.setText(quantity);




        Accept = (Button) findViewById(R.id.accept);
        Decline = (Button) findViewById(R.id.decline);


       /*Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonateItems  items = new DonateItems();
                membership.setType(ModifyType.getText().toString());
                membership.setFeature(ModifyFeature.getText().toString());


                Update(key, membership); }

            public void Update(String key, Memberships membership) {
                databaseReference.child(key).setValue(membership);

                Toast.makeText(membershipDetails.this, "The membership  been Updated", Toast.LENGTH_LONG).show();

            }






        } );*/

       /* DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(key);}


            public void Delete(String key) {
                //databaseReference.child(key).setValue(null); for change dont use it aseel
                databaseReference.child(key).removeValue();

                Toast.makeText(donationrequestDetails.this, "The membership  been Deleted", Toast.LENGTH_LONG).show();
                finish();


            }

        });*/


    }
}

