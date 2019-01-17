package com.example.hostel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadHostel extends AppCompatActivity {

    EditText hostelNameEt, hostelCapacityEt, hostelDescriptionEt;
    Button uploadHostelBtn;
    DatabaseReference hostelReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_hostel);

        hostelNameEt = findViewById(R.id.hostelNameEditText);
        hostelDescriptionEt = findViewById(R.id.hostelDescriptionEditText);
        hostelCapacityEt = findViewById(R.id.hostelCapacityEditText);

        uploadHostelBtn = findViewById(R.id.uploadHostelButton);

        uploadHostelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadHostelDetails();
            }
        });
    }

    private void uploadHostelDetails() {

        hostelReference = FirebaseDatabase.getInstance().getReference("Hostels");

        String id, hostelName, hostelDescription, hostelCapacity;

        hostelName = hostelNameEt.getText().toString().trim();
        hostelCapacity = hostelCapacityEt.getText().toString().trim();
        hostelDescription = hostelDescriptionEt.getText().toString().trim();

        if (!TextUtils.isEmpty(hostelName) && !TextUtils.isEmpty(hostelCapacity) && !TextUtils.isEmpty(hostelDescription)){

            id = hostelReference.push().getKey();

            Hostel newHostel = new Hostel(id,hostelName, hostelDescription, hostelCapacity);

            assert id != null;
            hostelReference.child(id).setValue(newHostel);


        } else {
            hostelNameEt.setError("Enter Hostel Name Here !!!");
            hostelNameEt.requestFocus();

            hostelCapacityEt.setError("Enter Hostel Capacity Here !!!");
            hostelNameEt.requestFocus();

            hostelDescriptionEt.setError("Enter Hostel Description Here !!!");
            hostelNameEt.requestFocus();
        }

        Intent openHostelList = new Intent(getApplicationContext(), HostelListActivity.class);
        startActivity(openHostelList);

        Toast.makeText(getApplicationContext(), "Successfully Uploaded Details", Toast.LENGTH_LONG).show();







    }
}
