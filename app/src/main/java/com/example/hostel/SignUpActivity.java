package com.example.hostel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    String id;

    boolean isOwner = false;

    DatabaseReference nUserDatabaseReference;
    FirebaseAuth newUserAuth;
    FirebaseUser firebaseUser;

    EditText userNameEdit, userEmailEdit, userPassword, userIDNumberEdit, userPhoneEdit, studentRegistrationEdit, studentCourseEdit, ownerHostelEdit;
    TextView loginTV;
    RadioGroup radioGroup;
    RadioButton studentRbutton, ownerRbutton;

    Button ownerSignUpButton, studentSignUpButton;

    String newUserName, newUserEmail, newUserPassword, newUserPhone, newUserIDNumber, newSudentRegistration, newStudentCourse, newOwnerHostel, studentId, ownerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        newUserAuth = FirebaseAuth.getInstance();
        firebaseUser = newUserAuth.getCurrentUser();


        userNameEdit = findViewById(R.id.nUserNameET);
        userEmailEdit = findViewById(R.id.nUserEmailET);
        userPassword = findViewById(R.id.nUserPasswordET);
        userIDNumberEdit = findViewById(R.id.nUserIdNumberET);
        userPhoneEdit = findViewById(R.id.nUserPhoneET);

        studentRegistrationEdit = findViewById(R.id.nStudentRegNumberET);
        studentRegistrationEdit.setEnabled(false);
        studentRegistrationEdit.setVisibility(View.GONE);

        studentCourseEdit = findViewById(R.id.nStudentCourseET);
        studentCourseEdit.setEnabled(false);
        studentCourseEdit.setVisibility(View.GONE);

        ownerHostelEdit = findViewById(R.id.nOwnerHostelET);
        ownerHostelEdit.setEnabled(false);
        ownerHostelEdit.setVisibility(View.GONE);

//        hostelPriceEdit = findViewById(R.id.hostelPriceET);
//        hostelPriceEdit.setEnabled(false);
//        hostelPriceEdit.setVisibility(View.GONE);

        studentRbutton = findViewById(R.id.nStudentRadioButton);
        ownerRbutton = findViewById(R.id.nOwnerRadioButton);

        ownerSignUpButton = findViewById(R.id.ownerSignUpBtn);
        studentSignUpButton = findViewById(R.id.studentSignUpBtn);

        ownerSignUpButton.setEnabled(false);
        ownerSignUpButton.setVisibility(View.GONE);

        studentSignUpButton.setVisibility(View.GONE);
        studentSignUpButton.setEnabled(false);

        loginTV = findViewById(R.id.logInTextView);

        newUserAuth = FirebaseAuth.getInstance();

        loginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLogInActivity = new Intent(getApplicationContext(), MainActivity.class);
                openLogInActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(openLogInActivity);
            }
        });


        radioGroup = findViewById(R.id.radioGroupSt_Ow);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.nStudentRadioButton) {


                    isOwner = false;

                    studentRegistrationEdit.setEnabled(true);
                    studentRegistrationEdit.setVisibility(View.VISIBLE);
                    studentCourseEdit.setEnabled(true);
                    studentCourseEdit.setVisibility(View.VISIBLE);

                    ownerSignUpButton.setEnabled(false);
                    ownerSignUpButton.setVisibility(View.GONE);

                    studentSignUpButton.setVisibility(View.GONE);
                    studentSignUpButton.setEnabled(false);

                    studentRegistrationEdit.requestFocus();
                    studentCourseEdit.requestFocus();

                    ownerHostelEdit.setEnabled(false);
//                    hostelPriceEdit.setEnabled(false);

//                    hostelPriceEdit.getText().clear();
//                    ownerHostelEdit.getText().clear();


                } else {

                    isOwner = true;

                    studentRegistrationEdit.setEnabled(false);
                    studentRegistrationEdit.setVisibility(View.GONE);

                    studentCourseEdit.setEnabled(false);
                    studentCourseEdit.setVisibility(View.GONE);

                    newSudentRegistration = null;
                    newStudentCourse = null;

                    studentRegistrationEdit.getText().clear();
                    studentCourseEdit.getText().clear();

                    ownerSignUpButton.setVisibility(View.VISIBLE);
                    ownerSignUpButton.setEnabled(true);


                }


                if (checkedId == R.id.nOwnerRadioButton) {

                    isOwner = true;

                    ownerHostelEdit.setEnabled(true);
                    ownerHostelEdit.setVisibility(View.VISIBLE);

//                    hostelPriceEdit.setEnabled(true);
//                    hostelPriceEdit.setVisibility(View.VISIBLE);

                    studentSignUpButton.setVisibility(View.GONE);
                    studentSignUpButton.setEnabled(false);

                    studentRegistrationEdit.getText().clear();
                    studentCourseEdit.getText().clear();


                } else {
                    isOwner = false;
                    ownerHostelEdit.setEnabled(false);
                    ownerHostelEdit.setVisibility(View.GONE);
//                    hostelPriceEdit.setEnabled(false);
//                    hostelPriceEdit.setVisibility(View.GONE);

                    studentSignUpButton.setEnabled(true);
                    studentSignUpButton.setVisibility(View.VISIBLE);

//                    hostelPriceEdit.getText().clear();
//                    ownerHostelEdit.getText().clear();
                }


            }
        });


        ownerSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                uploadNewUser();
            }
        });

        studentSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadNewUser();
            }
        });

    }

//    private void uploadNewStudent() {
//
//        newUserName = userNameEdit.getText().toString().trim();
//        newUserEmail = userEmailEdit.getText().toString().trim();
//        newUserPhone = userPhoneEdit.getText().toString().trim();
//        newUserIDNumber = userIDNumberEdit.getText().toString().trim();
//
//        newUserPassword = userPassword.getText().toString().trim();
//
//        newSudentRegistration = studentRegistrationEdit.getText().toString().trim();
//        newStudentCourse = studentCourseEdit.getText().toString().trim();
//
//
//    }

    private void uploadNewUser() {

        newUserName = userNameEdit.getText().toString().trim();
        newUserEmail = userEmailEdit.getText().toString().trim();
        newUserPhone = userPhoneEdit.getText().toString().trim();
        newUserIDNumber = userIDNumberEdit.getText().toString().trim();
        newUserPassword = userPassword.getText().toString().trim();


        newOwnerHostel = ownerHostelEdit.getText().toString().trim();

        nUserDatabaseReference = FirebaseDatabase.getInstance().getReference("users");


        newUserAuth.createUserWithEmailAndPassword(newUserEmail, newUserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    id = newUserAuth.getCurrentUser().getUid();

                    if (isOwner) {

                        NewUser newOwnerUser = new NewUser(id, "owner", newUserName, newUserIDNumber,
                                newUserEmail, newUserPhone,  null, null, newOwnerHostel);


                        nUserDatabaseReference.child(id).setValue(newOwnerUser);

                        Intent openUploadPage = new Intent(getApplicationContext(), UploadHostel.class);
                        openUploadPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(openUploadPage);


                    } else {
                        NewUser newUser = new NewUser(id,"student", newUserName, newUserEmail, newUserIDNumber,
                                newUserPhone, newSudentRegistration, newStudentCourse,null);

                        assert id != null;
                        nUserDatabaseReference.child(id).setValue(newUser);

                        Intent openHostelList = new Intent(getApplicationContext(), ViewHostelsActivity.class);
                        openHostelList.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(openHostelList);
                    }

                    Toast.makeText(SignUpActivity.this, newUserName + " Successfully Registered", Toast.LENGTH_LONG).show();
                    finish();

                } else {

                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}
