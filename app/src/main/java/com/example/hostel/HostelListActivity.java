package com.example.hostel;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HostelListActivity extends AppCompatActivity {

    ListView hostelList;
    DatabaseReference hostelReference;
    ArrayList<Hostel> hostelArrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hostel_list);

        hostelList = findViewById(R.id.hostelListView);
        hostelReference = FirebaseDatabase.getInstance().getReference("Hostels");



    }

    @Override
    protected void onStart() {
        super.onStart();

        hostelReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                hostelArrayList = new ArrayList<>();

                hostelArrayList.clear();

                for (DataSnapshot hostelSnapshot: dataSnapshot.getChildren() ){

                    Hostel hostel = hostelSnapshot.getValue(Hostel.class);
                    hostelArrayList.add(hostel);
                }

                HostelAdapter hostelAdapter = new HostelAdapter(getApplicationContext(), 0, hostelArrayList);
                hostelList.setAdapter(hostelAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
