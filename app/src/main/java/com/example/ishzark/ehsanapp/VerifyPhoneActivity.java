package com.example.ishzark.ehsanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyPhoneActivity extends AppCompatActivity {

    private String mVerificationId;
    private EditText editTextCode;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authenticatenumber);

        mAuth = FirebaseAuth.getInstance();
        editTextCode = findViewById(R.id.numberinput2);
        Intent intent = getIntent();
        String mobile = intent.getStringExtra("mobile");
        sendVerificationCode(mobile);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

    }


        private void sendVerificationCode(String mobile) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    "+966" + mobile,
                    60,
                    TimeUnit.SECONDS,
                    TaskExecutors.MAIN_THREAD,
                    mCallbacks);

        findViewById(R.id.verifynumbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextCode.getText().toString().trim();
                if (code.isEmpty() || code.length() < 6) {
                    editTextCode.setError("الرجاء إدخال ٦ ارقام على الأقل");
                    editTextCode.requestFocus();
                    return;
                }


                //startActivity(intent);

                //verifying the code entered manually
                verifyVerificationCode(code);
            }
        });

    }

        private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                //Getting the code sent by SMS
                String code = phoneAuthCredential.getSmsCode();

                //sometime the code is not detected automatically
                //in this case the code will be null
                //so user has to manually enter the code
                if (code != null) {
                    editTextCode.setText(code);
                    //verifying the code
                    verifyVerificationCode(code);
                }
            }
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(VerifyPhoneActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                //storing the verification id that is sent to the user
                mVerificationId = s;
            }
        };


    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);

        //signing the user

        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyPhoneActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = task.getResult().getUser();
                            //verification successful we will start the profile activity
                            Intent intent = getIntent();
                            String mobile = intent.getStringExtra("mobile");
                            Intent i = new Intent(VerifyPhoneActivity.this, RegisterActivity.class);
                            i.putExtra("mobile", mobile);
                           i.putExtra("user", user);
                            Toast.makeText(VerifyPhoneActivity.this, getString(R.string.Phoneregistration_success), Toast.LENGTH_LONG).show();
                            startActivity(i);

                        } else {

                            Toast.makeText(VerifyPhoneActivity.this, getString(R.string.Phoneregistration_failed), Toast.LENGTH_LONG).show();


                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(VerifyPhoneActivity.this, getString(R.string.Phoneregistration_wrongcode), Toast.LENGTH_LONG).show();
                            }


                        }
                    }
                });
}}
