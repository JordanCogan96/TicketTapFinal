package com.example.tickettap.Attendant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tickettap.Admin.AdminPanelActivity;
import com.example.tickettap.R;
import com.example.tickettap.ResetPasswordActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AttendantLoginActivity extends AppCompatActivity implements View.OnClickListener{

    FirebaseAuth mAuth;
    EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendant_login);

        mAuth = FirebaseAuth.getInstance();
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        findViewById(R.id.buttonLogin).setOnClickListener(this);
        findViewById(R.id.attendantForgotPasswordBtn).setOnClickListener(this);

    }

    private void userLogin(){

        final String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        //Check For Empty Field
        if (email.isEmpty()){
            editTextEmail.setError("Please Enter an Email");
            editTextEmail.requestFocus();
            return;
        }

        //Check for Valid Email
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please Enter a Valid Email");
            editTextEmail.requestFocus();
            return;
        }

        if (email.contains("security") || email.contains("admin")|| email.contains("vip")){
            editTextEmail.setError("Please Enter a Valid Account");
            editTextEmail.requestFocus();
            return;
        }

        //Check For Empty Field
        if (password.isEmpty()){
            editTextPassword.setError("Please Enter a Password");
            editTextPassword.requestFocus();
            return;
        }

        //Set password length to min 10
        if (password.length()<10){
            editTextPassword.setError("Min Password length of 10 characters");
            editTextPassword.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //Advance to Profile
                    Intent intent = new Intent(AttendantLoginActivity.this, AttendantHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                }else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLogin:
                userLogin();
                break;
            case R.id.attendantForgotPasswordBtn:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;
        }
    }
}
