package com.example.ishzark.ehsanapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ItemDonationActivity extends AppCompatActivity {
private Button choosebtn;
private ImageView imageView;
private Uri filePath;
private int PICK_IMAGE_REQUEST=71;
private Button donatebtn;
private  String donorname;
private EditText number;
private EditText detials;
public String chosendonationtype;
public String chosenitemstatus;
    private FirebaseAuth auth;
FirebaseStorage storage;
StorageReference storageReference;
    private static final String TAG = "ItemDonaationActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemdonation);
        number=findViewById(R.id.numberofitems);
        detials=findViewById(R.id.detailstext);

///////////////////////////////Donation Type Spinner/////////////////////////////////
        Spinner spinner = findViewById(R.id.donationtypespinner);

        String[] types=getResources().getStringArray(R.array.donationtype_array);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.citycpinnerlayout,R.id.listtext, types);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosendonationtype=String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                chosendonationtype=String.valueOf(parent.getItemAtPosition(0));

            }
        });
///////////////////////////////Item Status Spinner/////////////////////////////////
        Spinner spinner2 = findViewById(R.id.itemstatusspinner);

        String[] status=getResources().getStringArray(R.array.item_status_array);

        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,R.layout.citycpinnerlayout,R.id.listtext, status);

        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                chosenitemstatus=String.valueOf(parent.getItemAtPosition(position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                chosenitemstatus=String.valueOf(parent.getItemAtPosition(0));

            }
        });

        choosebtn= findViewById(R.id.upload);
        donatebtn=findViewById(R.id.donatebtn);
        imageView= findViewById(R.id.uploadedimage);
        storage=FirebaseStorage.getInstance();
        storageReference=storage.getReference();

        choosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        donatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadimage();
                uploadRequest();
            }
        });



    }

    private void uploadimage() {
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String userID = user.getUid();
        //Upload Image

        if(filePath!=null){


            StorageReference ref= storageReference.child("RequestImages"+ userID.toString()+"/");
            ref.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                }

        }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }}

        public void uploadRequest(){
        //Upload rest of request





        DatabaseReference refe = FirebaseDatabase.getInstance().getReference().child("Donors");

        refe.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean found=false;
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                //String userID = user.getUid();

                 String mobile =  user.getPhoneNumber();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String phone = child.child("phone").getValue().toString();
                    Log.d(TAG,"phone found"+mobile.replace("+966",""));
                    if (phone.equals(mobile.replace("+966",""))) {
                           donorname = child.child("name").getValue().toString();
                        Log.d(TAG,"name found"+donorname);
                            found=true;

                    }

                    if(found){
                        //Upload the rest of request

                        Intent intent = getIntent();
                        String location=intent.getStringExtra("chosencity");
                        String numberofitemes = number.getText().toString().trim();
                        String detailofitems = detials.getText().toString();

                        String date=new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

                        if (numberofitemes.isEmpty()) {
                            number.setError(getString(R.string.input_error_name));
                            number.requestFocus();
                            return;
                        }
                        DonateItems Newrequest=new DonateItems();

                        Newrequest.setPhone(mobile);
                        Newrequest.setItem_type(chosendonationtype);
                        Newrequest.setItem_status(chosenitemstatus);
                        Newrequest.setItemDetails(detailofitems);
                        Newrequest.setDonor_name(donorname.toString());
                        Newrequest.setQuantity(numberofitemes);
                        Newrequest.setLocation(location);
                        Newrequest.setDate(date.toString());
                        Log.d(TAG,"Founded details"+mobile+chosendonationtype+chosenitemstatus+detailofitems);

                          final ProgressDialog progressDialog=new ProgressDialog(ItemDonationActivity.this);
                         progressDialog.setTitle(R.string.uploading);
                        progressDialog.show();

        Task<Void> requests = FirebaseDatabase.getInstance().getReference("Items").push()
                .setValue( Newrequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(ItemDonationActivity.this,R.string.doneuploading,Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(ItemDonationActivity.this,R.string.notuploading, Toast.LENGTH_SHORT).show();
                        }
                    }
                });



                    }

                }} @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

            }});







    }



            private void chooseImage() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            startActivityForResult(intent.createChooser(intent,"select Picture"),PICK_IMAGE_REQUEST);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK
                && data !=null && data.getData() != null)
        {
            filePath=data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
             e.printStackTrace();
            }
        }
    }
}