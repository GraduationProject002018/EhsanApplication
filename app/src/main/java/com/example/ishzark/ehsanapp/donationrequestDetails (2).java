package com.example.ishzark.ehsanapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class donationrequestDetails extends AppCompatActivity {

    private TextView DonorName;
    private TextView Phone;
    private TextView Itemtype;
    private TextView itemstatus;
    private TextView Quantity;
    private TextView itemdetails;
    private ImageView pic;
    //private EditText location;
    FirebaseStorage firebaseStorage;
    StorageReference mainRef;
    private Button locationMap;
    private Button Accept;
    private Button Decline;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    String key, donor_name, item_status,item_type, location,phone,quantity, itemDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminrequestview);

        DonorName = (TextView) findViewById(R.id.donorname);
        Phone = (TextView) findViewById(R.id.phone);
        Itemtype= (TextView) findViewById(R.id.itemtype);
        itemstatus= (TextView) findViewById(R.id.itemstatus);
        Quantity= (TextView) findViewById(R.id.quantity);
        itemdetails= (TextView) findViewById(R.id.quantity2);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Items");


        key = getIntent().getStringExtra("key");
        //Log.d("donationrequestDetails","key:" + key);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean found=false;
                for (DataSnapshot child : dataSnapshot.getChildren()){
                    String k = child.getKey().toString();
                    if (k.equals(key)) {
                        //Log.d("donationrequestDetails","K:" + k);
                        found=true;
                        donor_name = child.child("donor_name").getValue().toString();
                        phone = child.child("phone").getValue().toString();
                        quantity= child.child("quantity").getValue().toString();
                        item_type= child.child("item_type").getValue().toString();
                        item_status= child.child("item_status").getValue().toString();
                        itemDetails= child.child("itemDetails").getValue().toString();
                        location= child.child("location").getValue().toString();







                    }



                    }

                    if(found){

                        DonorName.setText(donor_name);
                        Phone.setText(phone);
                        Itemtype.setText(item_type);
                        itemstatus.setText(item_status);
                        Quantity.setText(quantity);
                        itemdetails.setText(itemDetails);




                    }

                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        }


     );








        // location= (TextView) findViewById(R.id.locationbtnadminreq);
        // pic= (TextView) findViewById(R.id.pic);











        locationMap= (Button) findViewById(R.id.locationbtnadminreq);
        Accept = (Button) findViewById(R.id.accept);
        Decline = (Button) findViewById(R.id.decline);


        locationMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + location);

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);


            }

        } ); //end locationbtn



        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonateItems  items = new DonateItems();
                Update(key, items); }



            public void Update(String key, DonateItems items) {
                databaseReference.child(key).child("request_status").setValue("قبول");
                Toast.makeText(donationrequestDetails.this, "تم قبول طلب التبرع بنجاح.", Toast.LENGTH_LONG).show();

            }

        } );

        Decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonateItems  items = new DonateItems();
                Update(key, items); }



            public void Update(String key, DonateItems items) {
                databaseReference.child(key).child("request_status").setValue("رفض");
                Toast.makeText(donationrequestDetails.this, "تم رفض طلب التبرع بنجاح.", Toast.LENGTH_LONG).show();

            }

        } );




    }
}

