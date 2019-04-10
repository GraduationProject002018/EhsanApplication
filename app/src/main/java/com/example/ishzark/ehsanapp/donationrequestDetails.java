package com.example.ishzark.ehsanapp;

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

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import static android.support.constraint.Constraints.TAG;

public class donationrequestDetails extends AppCompatActivity {

    private TextView DonorName;
    private TextView Phone;
    private TextView Itemtype;
    private TextView itemstatus;
    private TextView Quantity;
    private TextView itemdetails;

    FirebaseStorage firebaseStorage;
    StorageReference mainRef;
    private Button showBtn;
    private ImageView locationMap;
    private Button Accept;
    private Button Decline;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    String TAG = "donationrequestDetails2 ";
    private TextView url;
    private ImageView img;
    private TextView address;
    private TextView date;
    private String Date;
    String valueurl;
    String key, donor_name, item_status, item_type, location, phone, quantity, itemDetails;
    String Username = "mariamedu";
    String Password = "ehsan2019";
    String Sender = "Ehsan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminrequestview);

        DonorName = (TextView) findViewById(R.id.donorname);
        Phone = (TextView) findViewById(R.id.itemphone);
        Itemtype = (TextView) findViewById(R.id.itemtype);
        itemstatus = (TextView) findViewById(R.id.itemstatus);
        Quantity = (TextView) findViewById(R.id.quantity);
        itemdetails = (TextView) findViewById(R.id.quantity2);
        url = findViewById(R.id.imageurl);
        img = findViewById(R.id.imageView2);
        address = findViewById(R.id.textView17);
        date = findViewById(R.id.datetext);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Items");


        key = getIntent().getStringExtra("key");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                             @Override
                                                             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                                                 boolean found = false;
                                                                 for (DataSnapshot child : dataSnapshot.getChildren()) {
                                                                     String k = child.getKey().toString();
                                                                     if (k.equals(key)) {
                                                                         //Log.d("donationrequestDetails","K:" + k);
                                                                         found = true;
                                                                         donor_name = child.child("donor_name").getValue().toString();
                                                                         phone = child.child("phone").getValue().toString();
                                                                         quantity = child.child("quantity").getValue().toString();
                                                                         item_type = child.child("item_type").getValue().toString();
                                                                         item_status = child.child("item_status").getValue().toString();
                                                                         itemDetails = child.child("itemDetails").getValue().toString();
                                                                         location = child.child("location").getValue().toString();
                                                                         valueurl = child.child("url").getValue().toString();
                                                                         Date = child.child("date").getValue().toString();


                                                                     }


                                                                 }

                                                                 if (found) {

                                                                     DonorName.setText(donor_name);
                                                                     Phone.setText(phone);
                                                                     Itemtype.setText(item_type);
                                                                     itemstatus.setText(item_status);
                                                                     Quantity.setText(quantity);
                                                                     itemdetails.setText(itemDetails);
                                                                     address.setText(location);
                                                                     date.setText(Date);

                                                                     url.setText(valueurl);
                                                                     Picasso.with(img.getContext())
                                                                             .load(String.valueOf(valueurl))
                                                                             .placeholder(R.mipmap.ic_launcher)
                                                                             .into(img);

                                                                 }

                                                             }

                                                             @Override
                                                             public void onCancelled(@NonNull DatabaseError databaseError) {

                                                             }

                                                         }


        );


        locationMap = findViewById(R.id.location);
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

        }); //end locationbtn


        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DonateItems items = new DonateItems();
                UpdateAcc(key, items);
                finish();
            }


            public void UpdateAcc(String key, DonateItems items) {


                final DatabaseReference refe = FirebaseDatabase.getInstance().getReference().child("Donors");

                refe.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean found = false;


                        String mobile = Phone.getText().toString();
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            String Firebasephone = child.child("phone").getValue().toString();
                            Log.d(TAG, "Fphone found " + Firebasephone);
                            if (Firebasephone.equals(mobile.replace("+966", ""))) {
                                Log.d(TAG, "phone found " + Firebasephone);
                                String DonorLevel = child.child("donorlevel").getValue().toString();
                                Log.d(TAG, "level found " + DonorLevel);
                                String levelvalue = child.child("donationvalue").getValue().toString();

                                int quan = Integer.parseInt(quantity);
                                int amount = returnvalue(item_type, item_status, quan);


                                int value = Integer.parseInt(levelvalue) + amount;
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

                                String k = child.getKey();
                                Log.d(TAG, "k found " + k);
                                refe.child(k).child("donorlevel").setValue(DonorLevel);
                                refe.child(k).child("donationvalue").setValue(value);

                            }

                        }
                    } //end for


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                databaseReference.child(key).child("requestStatus").setValue("مقبول");
                Toast.makeText(donationrequestDetails.this, "تم قبول طلب التبرع بنجاح.", Toast.LENGTH_LONG).show();
                sendSMS(phone,donor_name,"لقد تم قبول طلبك للتبرع وسيتواصل معك السائق اليوم لاستلام التبرعات،شاكرين لك حسن عملك");

            }

        });

        Decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DonateItems items = new DonateItems();
                Update(key, items);
                finish();
            }


            public void Update(String key, DonateItems items) {
                databaseReference.child(key).child("requestStatus").setValue("مرفوض");
                Toast.makeText(donationrequestDetails.this, "تم رفض طلب التبرع بنجاح.", Toast.LENGTH_LONG).show();
                sendSMS(phone,donor_name,"لقد تم رفض طلبك لعدم توافقه مع معايير الجمعية ولايمكن التبرع به");
            }

        });
    }


    public int returnvalue(String type, String stat, int quan) {
        int amount = 0;
        //Furniture

        if (type.equals("أثاث") && stat.equals("جديد")) {
            amount = 500 * quan;
        } else if (type.equals("أثاث") && stat.equals("مستخدم")) {
            amount = 250 * quan;
        } else if (type.equals("أثاث") && stat.equals("يحتاج تصليح")) {
            amount = 100 * quan;
        } else if (type.equals("ملابس") && stat.equals("جديد")) {
            amount = 50 * quan;
        } else if (type.equals("ملابس") && stat.equals("مستخدم")) {
            amount = 25 * quan;
        } else if (type.equals("ملابس") && stat.equals("يحتاج تصليح")) {
            amount = 10 * quan;
        } else if (type.equals("جهاز كهربائي") && stat.equals("جديد")) {
            amount = 500 * quan;
        } else if (type.equals("جهاز كهربائي") && stat.equals("مستخدم")) {
            amount = 250 * quan;
        } else if (type.equals("جهاز كهربائي") && stat.equals("يحتاج تصليح")) {
            amount = 75 * quan;
        } else if (type.equals("طعام جاف") && stat.equals("جديد")) {
            amount = 30 * quan;

        }


        return amount;
    }



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


                    }
                }


        );

    }
}