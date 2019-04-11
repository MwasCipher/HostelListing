package com.example.hostel;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UploadedOwnerActivity extends AppCompatActivity {

    RecyclerView recyclerView, bookedStudentRecyclerView;
    List<Hostel> hostelList;
    ArrayList<BookedSuccessful> bookedSuccessfulArrayList;

    MyHostelAdapter hostelAdapter;
    RecyclerView.Adapter bookedStudentAdapter;

    DatabaseReference hostelReference, bookedStudentReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    Hostel hostel;
    String hostelOwnerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded_owner);

        recyclerView = findViewById(R.id.uploaded_owner_activity_rv);
        bookedStudentRecyclerView = findViewById(R.id.booked_students_rv);
        bookedStudentRecyclerView.setHasFixedSize(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookedStudentRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        hostelList = new ArrayList<>();
        bookedSuccessfulArrayList = new ArrayList<>();

        hostelOwnerId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        hostelReference = firebaseDatabase.getReference("Hostels");
//        bookedStudentReference = firebaseDatabase.getReference("Booked").child(hostelOwnerId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.log_out_menu){

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onStart() {
        super.onStart();

        hostelReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                hostelList.clear();

                for (DataSnapshot hostelSnapshot: dataSnapshot.getChildren() ){

                    Hostel hostel = hostelSnapshot.getValue(Hostel.class);
                    if ((hostel.getHostelOwnerId()).equalsIgnoreCase(hostelOwnerId)) {
                        hostelList.add(hostel);
                    }
                }

                hostelAdapter = new MyHostelAdapter(hostelList, getApplicationContext());
                recyclerView.setAdapter(hostelAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UploadedOwnerActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();




            }
        });
    }
}
