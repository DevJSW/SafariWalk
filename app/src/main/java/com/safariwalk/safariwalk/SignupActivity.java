package com.safariwalk.safariwalk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;

    private Button mSignupBtn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEmailField = (EditText) findViewById(R.id.emailField);
        mPasswordField = (EditText)findViewById(R.id.passwordField);

        mSignupBtn = (Button) findViewById(R.id.signupBtn);
        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signupUser();

            }
        });
    }

    private void signupUser() {

        String email = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();

       if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

          // mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener()

       }else {

       }

    }
}
