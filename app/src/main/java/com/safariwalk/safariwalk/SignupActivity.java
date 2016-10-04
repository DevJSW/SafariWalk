package com.safariwalk.safariwalk;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mNameField;
    private EditText mPasswordField;

    private Button mSignupBtn;

    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEmailField = (EditText) findViewById(R.id.emailField);
        mPasswordField = (EditText)findViewById(R.id.passwordField);
        mNameField = (EditText) findViewById(R.id.nameField);

        mProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        mSignupBtn = (Button) findViewById(R.id.signupBtn);
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signupUser();

            }
        });
    }

    private void signupUser() {

        final String name = mNameField.getText().toString().trim();
        final String email = mEmailField.getText().toString().trim();
        final String password = mPasswordField.getText().toString().trim();

       if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

           mProgress.setMessage("Signing Up...");
           mProgress.show();

          mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {

                  if (task.isSuccessful()) {

                      String User_id = mAuth.getCurrentUser().getUid();
                      DatabaseReference current_userID = mDatabase.child("User_id");
                      current_userID.child("name").setValue(name);
                      current_userID.child("image").setValue("default");

                      mProgress.dismiss();

                      Intent mainIntent = new Intent(SignupActivity.this, MainActivity.class);
                      mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                      startActivity(mainIntent);
                  }

              }
          });

       }


    }
}
