package com.example.ishzark.ehsanapp;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.List;

import static com.example.ishzark.ehsanapp.R.layout.prepaidinvoice;



public class PrepaidInvoice_main extends AppCompatActivity {



    //Anwar-Prepaid Invoice Interface

    //view objects
    EditText bankName;
    EditText Phone;
    EditText date_;
    EditText donorname;
    EditText donationamount2;
    EditText InvoiceNm2;
    Spinner Program2;
    Button donatebtn;


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
        donorname = findViewById(R.id.donorName);
        Phone = findViewById(R.id.phone);
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

        String donorName = donorname.getText().toString().trim();
        String phone = Phone.getText().toString().trim();
        String donationamount = donationamount2.getText().toString().trim();
        String Invoicenum = InvoiceNm2.getText().toString().trim();
        String date = date_.getText().toString().trim();
        String bank = bankName.getText().toString().trim();
        String program = Program2.getSelectedItem().toString();


        //checking if the value is provided


        if(TextUtils.isEmpty(donorName) ){
            Toast.makeText(this,"الرجاء إدخال أسم المتبرع.",Toast.LENGTH_LONG).show();
        }
        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"الرجاءإدخال رقم الجوال.",Toast.LENGTH_LONG).show();

        }
        if(TextUtils.isEmpty(donationamount)){
            Toast.makeText(this,"الرجاءإدخال قيمة التبرع.",Toast.LENGTH_LONG).show();

        }
        if(TextUtils.isEmpty(Invoicenum)){
            Toast.makeText(this,"الرجاءإدخال رقم الايصال.",Toast.LENGTH_LONG).show();

        } if(TextUtils.isEmpty(date)){
            Toast.makeText(this,"الرجاءإدخال تاريخ الايصال.",Toast.LENGTH_LONG).show();

        } if(TextUtils.isEmpty(bank)){
            Toast.makeText(this,"الرجاءإدخال أسم البنك.",Toast.LENGTH_LONG).show();

        }

        else {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for PrepaidInvoice
            String id = Ehsan.push().getKey();

            //creating an PrepaidInvoice Object
            PrepaidInvoice invoice = new PrepaidInvoice(donorName,donationamount,Invoicenum,date,bank,phone,program) ;

            //Saving the Artist
            Ehsan.child(id).setValue(invoice);

            //setting Edittext to blank again
            donorname.setText("");
            Phone.setText("");
            donationamount2.setText("");
            InvoiceNm2.setText("");
            date_.setText("");
            bankName.setText("");

            /* displaying a success toast */
            Toast.makeText(this, "تم إضافة سند التبرع بنجاح.", Toast.LENGTH_LONG).show();
        }
    }
}

