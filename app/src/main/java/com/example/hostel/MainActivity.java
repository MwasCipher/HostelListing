package com.example.hostel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        if (!TextUtils.isEmpty(password)){
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

        userAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    Intent openHostelList = new Intent(getApplicationContext(), UploadHostel.class);
                    startActivity(openHostelList);
                    Toast.makeText(getApplicationContext(), "User Signed In Successfully", Toast.LENGTH_LONG).show();


                }

            }
        });



    }


}
