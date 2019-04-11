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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText emailET;
    EditText passwordET;
    Button signInBT;
    TextView signUpTv;
    ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference;
    FirebaseAuth userAuth = FirebaseAuth.getInstance();
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

            if (user != null) {
                userAuth.getCurrentUser();
            }


        else{
            setContentView(R.layout.activity_main);

            emailET = findViewById(R.id.emailEditText);
            passwordET = findViewById(R.id.passwordEditText);
            signInBT = findViewById(R.id.signInButton);
            progressBar = findViewById(R.id.progress_bar);
            progressBar.setVisibility(View.GONE);
            user = userAuth.getCurrentUser();

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
                    signinUser();
                }
            });

        }

    }


    private void signinUser(){
        progressBar.setVisibility(View.VISIBLE);



        String email = emailET.getText().toString().trim().toLowerCase();
        String password = passwordET.getText().toString().trim();

        if (!TextUtils.isEmpty(password) && password.length()>= 6 && Patterns.EMAIL_ADDRESS.matcher(email).matches() && !TextUtils.isEmpty(email)) {

            userAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        progressBar.setVisibility(View.GONE);

                        onAuthSuccess(task.getResult().getUser());

                    } else {
                        progressBar.setVisibility(View.GONE);

                        Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        finish();

                        Intent registerNewUser = new Intent(MainActivity.this, SignUpActivity.class);
                        registerNewUser.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(registerNewUser);


                    }
                }
            });
        }else{

            passwordET.setError("Password should contain 6 or more characters");
            emailET.setError("Enter a Valid Email");
        }



    }

    private void onAuthSuccess(FirebaseUser user) {


        if (user !=null){

            databaseReference = firebaseDatabase.getReference("users").child(user.getUid());
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    NewUser newUser = dataSnapshot.getValue(NewUser.class);
                    String userType = newUser.getUserType();




                    if (userType.equalsIgnoreCase("student")){
                        startActivity(new Intent(getApplicationContext(), ViewHostelsActivity.class));

                        Toast.makeText(MainActivity.this, "Logged in As Student", Toast.LENGTH_SHORT).show();

                    }else if (userType.equalsIgnoreCase("owner")){

                            startActivity(new Intent(getApplicationContext(), UploadedOwnerActivity.class));

                            Toast.makeText(MainActivity.this, "Logged in As Owner", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });


        }
    }


}
