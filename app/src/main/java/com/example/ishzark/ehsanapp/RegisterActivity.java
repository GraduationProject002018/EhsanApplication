
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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ishzark.ehsanapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;


public class RegisterActivity   extends AppCompatActivity  {
    //Get date variables
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private static final String TAG = "RegisterActivity";
private Spinner spinner;


    private EditText InputName, InputPhonenum, InputPass, InputBd, InputCity;
    private RadioGroup GenderGroup;
    private ProgressBar progressBar;
    private Button SignupButton;
    private RadioButton ChosenGender;
    public String chosencity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        InputName =  findViewById(R.id.nameinput);


        SignupButton = findViewById(R.id.signupbtn);
        GenderGroup = findViewById(R.id.Gendergrp);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        Spinner spinner = findViewById(R.id.cityspinner);

        String[] cities=getResources().getStringArray(R.array.city_arrays);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.citycpinnerlayout,R.id.listtext, cities);

        spinner.setAdapter(adapter);
spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        chosencity=String.valueOf(parent.getItemAtPosition(position));

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        chosencity=String.valueOf(parent.getItemAtPosition(0));

    }
});






///////////////////////////////////Start of Date Picker Code///////////////////////////


        mDisplayDate =  findViewById(R.id.bdinput);

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
                        RegisterActivity.this,
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
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

///////////////////////////////////End of Date Picker Code///////////////////////////




        SignupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FirebaseApp.initializeApp(RegisterActivity.this);
                registerUser();
            }
        });
    }



///////////////////////////////////End of City Picker Code///////////////////////////


///////////////////////////////////Start of Register into firebase Code///////////////////////////


        private void registerUser() {
           //String city= spinner.getSelectedItem().toString();

            Intent intent = getIntent();
            String mobile = intent.getStringExtra("mobile");


            final String name = InputName.getText().toString().trim();
            final String Bd = mDisplayDate.getText().toString().trim();


            if (name.isEmpty()) {
                InputName.setError(getString(R.string.input_error_name));
                InputName.requestFocus();
                return;
            }


            progressBar.setVisibility(View.VISIBLE);

            int selectedId = GenderGroup.getCheckedRadioButtonId();
            ChosenGender =  findViewById(selectedId);

    Users user = new Users();

    user.setName(InputName.getText().toString());
    user.setPhone(mobile);
    user.setGen(ChosenGender.getText().toString());
    user.setBd(Bd);
    user.setCity(chosencity);
    user.setdlevel("1");
    user.setdvalue(0);
    final  Intent in = new Intent(this, DonorHomeActivity.class);
            in.putExtra("mobile",mobile);


            Task<Void> donors = FirebaseDatabase.getInstance().getReference("Donors")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, getString(R.string.registration_success), Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);

                    } else {
                        Toast.makeText(RegisterActivity.this, getString(R.string.registration_failed), Toast.LENGTH_LONG).show();
                    }
                }
            });

            startActivity(in);
            finish();




        }}





///////////////////////////////////End of Register into firebase Code///////////////////////////







