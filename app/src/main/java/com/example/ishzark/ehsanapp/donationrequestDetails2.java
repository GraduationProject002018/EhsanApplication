package com.example.ishzark.ehsanapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class donationrequestDetails2 extends AppCompatActivity {

    private TextView DonorName;
    private TextView Phone;
    private TextView donationamount;
    private  TextView bank;
    private  TextView date;
    private  TextView invoicenum;
    private  TextView program;
    private FirebaseAuth auth;


    private Button Accept;
    private Button Decline;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
String TAG= "donationrequestDetails2";
    String Username = "mariamedu";
    String Password = "ehsan2019";
    String Sender = "Ehsan";

    String key, donor_name,donationAmount,phone, bank_, date_, Invoicenum, program_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prepaidinvoice_view);
        DonorName = (TextView) findViewById(R.id.donorName);
        Phone = (TextView) findViewById(R.id.itemphone);
        donationamount= (TextView) findViewById(R.id.donationamount2);
        invoicenum= (TextView) findViewById(R.id.InvoiceNm2);
        date= (TextView) findViewById(R.id.date);
        bank= (TextView) findViewById(R.id.bank);
        program= (TextView) findViewById(R.id.bank2);



        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Invoices");

        key = getIntent().getStringExtra("key");
        //Log.d("donationrequestDetails","key:" + key);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {


                                                             @Override
                                                             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                                 boolean found=false;
                                                                 for (DataSnapshot child : dataSnapshot.getChildren()){
                                                                     String k = child.getKey().toString();
                                                                     if (k.equals(key)) {

                                                                         found=true;

                                                                         donor_name = child.child("donorName").getValue().toString();
                                                                         donationAmount = child.child("donationAmount").getValue().toString();
                                                                         date_ = child.child("date").getValue().toString();
                                                                         phone = child.child("phone").getValue().toString();
                                                                         Invoicenum = child.child("invoiceNum").getValue().toString();
                                                                         program_ = child.child("program").getValue().toString();
                                                                         bank_ = child.child("bankName").getValue().toString();










                                                                     }



                                                                 }

                                                                 if(found){

                                                                     DonorName.setText(donor_name);
                                                                     Phone.setText(phone);
                                                                     donationamount.setText(donationAmount);
                                                                     invoicenum.setText(Invoicenum);
                                                                     program.setText(program_);
                                                                     bank.setText(bank_);
                                                                     date.setText(date_);


                                                                 }

                                                             }

                                                             @Override
                                                             public void onCancelled(@NonNull DatabaseError databaseError) {

                                                             }

                                                         }


        );


        Accept = (Button) findViewById(R.id.accept2);
        Decline = (Button) findViewById(R.id.decline2);


        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrepaidInvoice  pre = new PrepaidInvoice();
                Update(key, pre);
                sendSMS(phone,donor_name,"تم التأكد من وصول الإيصال جزاك الله خيراً على حسن عملك");
                finish();






            }


            public void Update(final String key, PrepaidInvoice pre ) {
                databaseReference.child(key).child("request_status").setValue("مقبول");
                Toast.makeText(donationrequestDetails2.this, "تم قبول الإيصال بنجاح.", Toast.LENGTH_LONG).show();


                final DatabaseReference refe = FirebaseDatabase.getInstance().getReference().child("Donors");

                refe.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean found = false;
                        auth = FirebaseAuth.getInstance();
                        FirebaseUser user = auth.getCurrentUser();
                        //String userID = user.getUid();



                        String mobile = Phone.getText().toString();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                           String Firebasephone = child.child("phone").getValue().toString();
                            Log.d(TAG, "Fphone found " + Firebasephone);
if(Firebasephone.equals(mobile)) {
    Log.d(TAG, "phone found " + Firebasephone);
    String DonorLevel = child.child("donorlevel").getValue().toString();
    Log.d(TAG, "level found " + DonorLevel);
    String levelvalue = child.child("donationvalue").getValue().toString();

int newvalue=Integer.parseInt(donationamount.getText().toString());
    int value = Integer.parseInt(levelvalue)+newvalue;
    Log.d(TAG, "value found " + value);
    found = true;


    //برونزي
    if (value >= 10000 && value <= 99999) {
        DonorLevel = "2";
    }

    //فضي
    else if (value >= 100000 && value <= 249999) {
        DonorLevel = "3";
    }

    //ذهبي
    else if (value >= 250000 && value <= 499999) {
        DonorLevel = "4";
    }

    //بلاتيني
    else if (value >= 500000 && value <= 10000000) {
        DonorLevel = "5";
    }

    String k=child.getKey();
    Log.d(TAG, "k found " + k);
    refe.child(k).child("donorlevel").setValue(DonorLevel);
    refe.child(k).child("donationvalue").setValue(value);

}

                            }} //end for


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        Decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrepaidInvoice  pre = new PrepaidInvoice();
                Update(key, pre);
                finish();}


            public void Update(String key, PrepaidInvoice pre ) {
               databaseReference.child(key).child("request_status").setValue("مرفوض");
                //items.setItemValue("رفض");
                Toast.makeText(donationrequestDetails2.this, "تم رفض طلب التبرع بنجاح.", Toast.LENGTH_LONG).show();
                sendSMS(phone,donor_name,"تم التأكد واتضح ان الإيصال لا وجود له في بياناتنا، يرجى مراجعة الفرع للتأكد");

            }


        } );
    }
});}



    private void sendSMS(String mobile, String name, String msg) {

        final RequestParams params = new RequestParams();

        params.add("mobile", Username);
        params.add("password", Password);
        params.add("returnJson", "1");
        params.add("sender", Sender);
        params.add("msg", Utils.convertUnicode(name + "" + msg));
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



