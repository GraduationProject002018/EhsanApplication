package com.example.ishzark.ehsanapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
    private ProgressBar prgressBar;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    String Username = "mariamedu";
    String Password="ehsan2019";
    String Sender= "Ehsan";
    String Message ="لقد تم استلام طلب تأكيد الإيصال وسيتم التواصل معك خلال ٢-٣ايام عمل";

    //a list to store all the invoice from firebase database
    List<PrepaidInvoice> invoices;

    //our database reference object
    DatabaseReference Ehsan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(prepaidinvoice);

        prgressBar=findViewById(R.id.progressBar4);
        /* getting the reference of invoices node */
        Ehsan = FirebaseDatabase.getInstance().getReference("Invoices");

        //getting views
        donationamount2 = findViewById(R.id.donationamount2);
        InvoiceNm2 = findViewById(R.id.InvoiceNm2);
        Program2 = findViewById(R.id.Program2);
        //donorname = findViewById(R.id.donorName);
        //Phone = findViewById(R.id.phone);
      //  date_ = findViewById(R.id.date);
        bankName = findViewById(R.id.bank);

        donatebtn = findViewById(R.id.donatebtn);

///////////////////////////////////Date Picker ///////////////////////////


        mDisplayDate =  findViewById(R.id.date);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance(new Locale("@calendar=islamic-umalqura"));
                //IslamicCalendar cal=IslamicCalendar.getInstance(locale);
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                // System.currentTimeMillis();
                DatePickerDialog dialog = new DatePickerDialog(
                        PrepaidInvoice_main.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };


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
        prgressBar.setVisibility(View.VISIBLE);
        //getting the values to save

        final String donationamount = donationamount2.getText().toString().trim();
        final String Invoicenum = InvoiceNm2.getText().toString().trim();
        final String date = mDisplayDate.getText().toString().trim();
        final String bank = bankName.getText().toString().trim();
        final String program = Program2.getSelectedItem().toString();


        //checking if the value is provided

        if (TextUtils.isEmpty(donationamount)) {
            donationamount2.setError("الرجاءإدخال قيمة التبرع");
            donationamount2.requestFocus();

            prgressBar.setVisibility(View.GONE);

        }
        else if (TextUtils.isEmpty(Invoicenum)) {
            InvoiceNm2.setError("الرجاءإدخال رقم الايصال");
            InvoiceNm2.requestFocus();
            prgressBar.setVisibility(View.GONE);

        }
       else if (TextUtils.isEmpty(date)) {
            mDisplayDate.setError("الرجاءإدخال تاريخ الايصال");
            mDisplayDate.requestFocus();
            prgressBar.setVisibility(View.GONE);


        }
       else  if (TextUtils.isEmpty(bank)) {
            bankName.setError("الرجاءإدخال أسم البنك");
            bankName.requestFocus();
            prgressBar.setVisibility(View.GONE);

        }else {


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
                    }

                    if (found) {

                        //getting a unique id using push().getKey() method
                        //it will create a unique id and we will use it as the Primary Key for PrepaidInvoice
                        String id = Ehsan.push().getKey();

                        //creating an PrepaidInvoice Object
                        PrepaidInvoice invoice = new PrepaidInvoice();
                        invoice.setBankName(bank);
                        invoice.setDate(date);
                        invoice.setDonationAmount(donationamount);
                        invoice.setInvoiceNum(Invoicenum);
                        invoice.setDonorName(donorname);
                        invoice.setPhone(mobile);
                        invoice.setProgram(program);
                        invoice.setRequest_status("معلق");

                        //Saving the obj
                        Ehsan.child(id).setValue(invoice);


                    }

                    prgressBar.setVisibility(View.GONE);
                    sendSMS(mobile,donorname);

                    startActivity(new Intent(PrepaidInvoice_main.this, GifActivity.class));
                    finish();

                }


                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


    private void sendSMS(String mobile, String name) {

        final RequestParams params = new RequestParams();

        params.add("mobile", Username);
        params.add("password", Password);
        params.add("returnJson", "1");
        params.add("sender", Sender);
        params.add("msg", Utils.convertUnicode(name + "" + Message));
        params.add("numbers", mobile.replace("+", ""));
        params.add("applicationType", "68");
        params.add("dateSend", "03/12/2019");
        params.add("timeSend", "3:30:00");
        MyConnectionType.get("https://mobily.ws/api/msgSend.php", params, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, org.json.JSONObject response) {
                        //Log.d(TAG, "objapi"+response.toString());
                        //Toast.makeText(PrepaidInvoice.this, "sms sent", Toast.LENGTH_SHORT).show();

                    }
                }


        );

    }


}
