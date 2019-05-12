package com.example.tickettap.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tickettap.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateNewUserActivity extends AppCompatActivity {


    //Declare
    EditText editTextEmail, editTextPassword;
    Button buttonSignUp;
    String role = "";
    RadioButton radioBtnAdmin, radioBtnVip, radioBtnSecurity, radioBtnAttendant;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);


        //Find Views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        radioBtnAdmin = (RadioButton) findViewById(R.id.radioBtnAdmin);
        radioBtnVip = (RadioButton) findViewById(R.id.radioBtnVip);
        radioBtnSecurity = (RadioButton) findViewById(R.id.radioBtnSecurity);
        radioBtnAttendant = (RadioButton) findViewById(R.id.radioBtnAttendant);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        firebaseAuth = firebaseAuth.getInstance();

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (radioBtnAdmin.isChecked()) {
                    role = "Admin";
                }
                if (radioBtnVip.isChecked()) {
                    role = "VIP";
                }
                if (radioBtnSecurity.isChecked()) {
                    role = "Security";
                }
                if (radioBtnAttendant.isChecked()) {
                    role = "Attendant";
                }

                //Check For Empty Field
                if (email.isEmpty()) {
                    editTextEmail.setError("Please Enter an Email");
                    editTextEmail.requestFocus();
                    return;
                }

                //Check for Valid Email
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    editTextEmail.setError("Please Enter a Valid Email");
                    editTextEmail.requestFocus();
                    return;
                }

                //Check For Empty Field
                if (password.isEmpty()) {
                    editTextPassword.setError("Please Enter a Password");
                    editTextPassword.requestFocus();
                    return;
                }

                //Set password length to min 10
                if (password.length() < 10) {
                    editTextPassword.setError("Min Password length of 10 characters");
                    editTextPassword.requestFocus();
                    return;
                }


                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(CreateNewUserActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    User info = new User(
                                            email,
                                            role
                                    );

                                    FirebaseDatabase.getInstance().getReference("User")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Toast.makeText(CreateNewUserActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(), AdminPanelActivity.class));
                                            
                                        }
                                    });

                                } else {

                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(getApplicationContext(), "Email already in use", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });


            }
        });

    }


}
