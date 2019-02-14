package com.example.ishzark.ehsanapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static com.example.ishzark.ehsanapp.R.layout.prepaidinvoice;



public class PrepaidInvoice_main extends AppCompatActivity {
    //view objects
    EditText bankName;
    //EditText Phone;
    EditText date_;
    String donorname;
    EditText donationamount2;
    EditText InvoiceNm2;
    Spinner Program2;
    Button donatebtn;
    private FirebaseAuth auth;

    //a list to store all the invoice from firebase database
    List<PrepaidInvoice> invoices;

    //our database reference object
    DatabaseReference Ehsan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(prepaidinvoice);

        /* getting the reference of invoices node */
        Ehsan = FirebaseDatabase.getInstance().getReference("Invoices");

        //getting views
        donationamount2 = findViewById(R.id.donationamount2);
        InvoiceNm2 =  findViewById(R.id.InvoiceNm2);
        Program2 =  findViewById(R.id.Program2);
        //donorname = findViewById(R.id.donorName);
        //Phone = findViewById(R.id.phone);
        date_ = findViewById(R.id.date);
        bankName =findViewById(R.id.bank);

        donatebtn = findViewById(R.id.donatebtn);

        //list to store invoices
        invoices = new ArrayList<>();


        //adding an onclicklistener to button
        donatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //this method is actually performing the write operation
                addInvoice();
            }
        });
    }

    /*
     * This method is saving a new invoice to the
     * Firebase Realtime Database
     * */
    private void addInvoice() {
        //getting the values to save

        String donationamount = donationamount2.getText().toString().trim();
        String Invoicenum = InvoiceNm2.getText().toString().trim();
        String date = date_.getText().toString().trim();
        String bank = bankName.getText().toString().trim();
        String program = Program2.getSelectedItem().toString();


        //checking if the value is provided

        if (TextUtils.isEmpty(donationamount)) {
            Toast.makeText(this, "الرجاءإدخال قيمة التبرع.", LENGTH_LONG).show();

        }
        if (TextUtils.isEmpty(Invoicenum)) {
            Toast.makeText(this, "الرجاءإدخال رقم الايصال.", LENGTH_LONG).show();

        }
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(this, "الرجاءإدخال تاريخ الايصال.", LENGTH_LONG).show();

        }
        if (TextUtils.isEmpty(bank)) {
            Toast.makeText(this, "الرجاءإدخال أسم البنك.", LENGTH_LONG).show();}

          else {  /* displaying a success toast */
            Toast.makeText(this, "تم إضافة سند التبرع بنجاح.", Toast.LENGTH_LONG).show();}


        DatabaseReference refe = FirebaseDatabase.getInstance().getReference().child("Donors");

        refe.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean found = false;
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                //String userID = user.getUid();

                String mobile = user.getPhoneNumber();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    String phone = child.child("phone").getValue().toString();
                    // Log.d(TAG,"phone found"+mobile.replace("+966",""));
                    if (phone.equals(mobile.replace("+966", ""))) {
                        donorname = child.child("name").getValue().toString();
                        // Log.d(TAG,"name found"+donorname);
                        found = true;

                    }

                    if (found) {


                        // String donorName = donorname.getText().toString().trim();
                        // String phone = Phone.getText().toString().trim();


                        //getting a unique id using push().getKey() method
                        //it will create a unique id and we will use it as the Primary Key for PrepaidInvoice
                        String id = Ehsan.push().getKey();

                        //creating an PrepaidInvoice Object
                        PrepaidInvoice invoice = new PrepaidInvoice();

                        //Saving the Artist
                        Ehsan.child(id).setValue(invoice);

                        //setting Edittext to blank again

                        donationamount2.setText("");
                        InvoiceNm2.setText("");
                        date_.setText("");
                        bankName.setText("");


                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}