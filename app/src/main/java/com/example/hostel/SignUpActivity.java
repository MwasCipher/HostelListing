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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    DatabaseReference nOwnerDatabaseReference, nStudentDatabaseReference;
    FirebaseAuth newUserAuth;

    EditText userNameEdit, userEmailEdit, userPassword, userIDNumberEdit, userPhoneEdit, studentRegistrationEdit, studentCourseEdit, ownerHostelEdit;
    RadioGroup radioGroup;
    RadioButton studentRbutton, ownerRbutton;

    Button userSignUpButton;

    String newUserName, newUserEmail, newUserPassword, newUserPhone, newUserIDNumber, newSudentRegistration, newStudentCourse, newOwnerHostel, studentId, ownerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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


        radioGroup = findViewById(R.id.radioGroupSt_Ow);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.nStudentRadioButton) {

                    studentRegistrationEdit.setEnabled(true);
                    studentRegistrationEdit.setVisibility(View.VISIBLE);
                    studentCourseEdit.setEnabled(true);
                    studentCourseEdit.setVisibility(View.VISIBLE);

                    studentRegistrationEdit.requestFocus();
                    studentCourseEdit.requestFocus();

                    ownerHostelEdit.setEnabled(false);


                } else {

                    studentRegistrationEdit.setEnabled(false);
                    studentRegistrationEdit.setVisibility(View.GONE);

                    studentCourseEdit.setEnabled(false);
                    studentCourseEdit.setVisibility(View.GONE);

                    newSudentRegistration = null;
                    newStudentCourse = null;

                    studentRegistrationEdit.getText().clear();
                    studentCourseEdit.getText().clear();


                }


                if (checkedId == R.id.nOwnerRadioButton) {

                    ownerHostelEdit.setEnabled(true);
                    ownerHostelEdit.setVisibility(View.VISIBLE);

                    studentRegistrationEdit.getText().clear();
                    studentCourseEdit.getText().clear();


                } else {

                    ownerHostelEdit.setEnabled(false);
                    ownerHostelEdit.setVisibility(View.GONE);
                }


            }
        });


        studentRbutton = findViewById(R.id.nStudentRadioButton);
        ownerRbutton = findViewById(R.id.nOwnerRadioButton);


        userSignUpButton = findViewById(R.id.signUpBtn);

        userSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (studentRbutton.isChecked()) {
                    uploadNewStudent();

                }

                if (ownerRbutton.isChecked()) {

                    uploadNewOwner();
                }


            }
        });

    }

    private void uploadNewStudent() {

        newUserName = userNameEdit.getText().toString().trim();
        newUserEmail = userEmailEdit.getText().toString().trim();
        newUserPhone = userPhoneEdit.getText().toString().trim();
        newUserIDNumber = userIDNumberEdit.getText().toString().trim();

        newUserPassword = userPassword.getText().toString().trim();

        newSudentRegistration = studentRegistrationEdit.getText().toString().trim();
        newStudentCourse = studentCourseEdit.getText().toString().trim();

        nStudentDatabaseReference = FirebaseDatabase.getInstance().getReference("student");

        studentId = nStudentDatabaseReference.push().getKey();

        NewUser newUser = new NewUser(studentId, newUserName, newUserEmail, newUserIDNumber, newUserPhone, newSudentRegistration, newStudentCourse);
        assert studentId != null;
        nStudentDatabaseReference.child(studentId).setValue(newUser);

        Intent openHostelList = new Intent(getApplicationContext(), HostelListActivity.class);
        openHostelList.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(openHostelList);

        newUserAuth.createUserWithEmailAndPassword(newUserEmail, newUserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(SignUpActivity.this, newUserName + " Successfully Registered", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void uploadNewOwner() {

        newUserName = userNameEdit.getText().toString().trim();
        newUserEmail = userEmailEdit.getText().toString().trim();
        newUserPhone = userPhoneEdit.getText().toString().trim();
        newUserIDNumber = userIDNumberEdit.getText().toString().trim();

        newUserPassword = userPassword.getText().toString().trim();

        newOwnerHostel = ownerHostelEdit.getText().toString().trim();

        nOwnerDatabaseReference = FirebaseDatabase.getInstance().getReference("owner");
        ownerId = nOwnerDatabaseReference.push().getKey();

        NewUser newOwnerUser = new NewUser(ownerId, newUserName, newUserIDNumber, newUserEmail, newUserPhone, newOwnerHostel);

        assert ownerId != null;
        nOwnerDatabaseReference.child(ownerId).setValue(newOwnerUser);

        Intent openUploadPage = new Intent(getApplicationContext(), UploadHostel.class);
        openUploadPage.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(openUploadPage);


        newUserAuth.createUserWithEmailAndPassword(newUserEmail, newUserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast.makeText(SignUpActivity.this, newUserName + " Successfully Registered", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }




}
