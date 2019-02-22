package com.example.hostel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    EditText emailET;
    EditText passwordET;
    Button signInBT;
    TextView signUpTv;

   FirebaseAuth userAuth = FirebaseAuth.getInstance();
    DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        emailET = findViewById(R.id.emailEditText);
        passwordET = findViewById(R.id.passwordEditText);
        signInBT = findViewById(R.id.signInButton);

        signUpTv = findViewById(R.id.signUpTextView);

        signUpTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSignUpActivity = new Intent(getApplicationContext(), SignUpActivity.class);
                openSignUpActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openSignUpActivity);
            }
        });



        signInBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });




    }


    private void registerUser(){

        String email = emailET.getText().toString().trim().toLowerCase();
        String password = passwordET.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {
            passwordET.setError("Password Required!!!");
            passwordET.requestFocus();
        }

        if (password.length()< 6){
            passwordET.setError("Password Should Contain 6 or More Characters !!!");
            passwordET.requestFocus();

        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(email)){

            emailET.setError("Please Enter a Valid Email!!!");
            emailET.requestFocus();

        }

//        userAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                if (task.isSuccessful()){
//
//
//                    Toast.makeText(getApplicationContext(), "User Signed In Successfully", Toast.LENGTH_LONG).show();
//
//                    Intent openHostelList = new Intent(MainActivity.this, HostelListActivity.class);
//                    startActivity(openHostelList);
//
//                }else{
//
//                    Toast.makeText(MainActivity.this, "Try Again Later", Toast.LENGTH_LONG).show();
//                }
//
//            }
//        });

        userAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    Toast.makeText(MainActivity.this, "Signed In Successfully", Toast.LENGTH_LONG).show();


                } else {

                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });



    }


}
