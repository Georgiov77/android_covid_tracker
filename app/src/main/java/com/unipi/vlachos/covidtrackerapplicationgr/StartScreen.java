package com.unipi.vlachos.covidtrackerapplicationgr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class StartScreen extends AppCompatActivity {

    EditText mEditText1,mEditText2;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        mEditText1 = findViewById(R.id.editTextTextPersonName);
        mEditText2 = findViewById(R.id.editTextTextPersonName2);
        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        if (mEditText1.getText().toString().matches("")  && mEditText2.getText().toString().matches("")){

            showMessage("Error","Please enter email and password");

        }else {
            mAuth.signInWithEmailAndPassword(mEditText1.getText().toString(),mEditText2.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                String email =mEditText1.getText().toString();
                                Intent intent = new Intent(StartScreen.this,MainActivity.class);
                                intent.putExtra("email",email);
                                startActivity(intent);
                                showMessage("Login success", "Συγχαρητήρια μπορείς να χρησιμοποιήσεις την εφαρμογή.");

                            }else {
                                showMessage("Login Failed", task.getException().getMessage());
                            }
                        }
                    });
        }
    }
    public void register(View view){
        if (mEditText1.getText().toString().matches("")  && mEditText2.getText().toString().matches("")){

            showMessage("Error","Please enter email and password");

        }else {
            mAuth.createUserWithEmailAndPassword(mEditText1.getText().toString(),mEditText2.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                showMessage("Sign up success", mAuth.getCurrentUser().getUid());

                            }else {
                                showMessage("Sign Up Failed", task.getException().getMessage());
                            }
                        }
                    });
        }
    }
    public void showMessage(String title, String message){
        new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .show();
    }
}