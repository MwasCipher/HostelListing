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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewHostelsActivity extends AppCompatActivity {

    RecyclerView recyclerView, bookedRecyclerView;
    ArrayList<Hostel> hostelList;
    ArrayList<BookedSuccessful> bookedSuccessfulArrayList;
    RecyclerView.Adapter adapter;

    RecyclerView.Adapter bookedAdapter;
    DatabaseReference hostelReference, bookedHostelReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser firebaseUser;
    Hostel hostel;
    TextView goToBookingsTV;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hostels);
        hostelList = new ArrayList<>();
        BookedSuccessful bookedSuccessful = new BookedSuccessful();
        bookedSuccessfulArrayList = new ArrayList<>();

        adapter = new HostelListAdapter(hostelList, ViewHostelsActivity.this);
        bookedAdapter = new BookedSuccessfulAdapter(getApplicationContext(), bookedSuccessfulArrayList);

        recyclerView = findViewById(R.id.view_hostel_list_rv);
        bookedRecyclerView = findViewById(R.id.view_successful_booked_rv);
        recyclerView.setHasFixedSize(true);
        bookedRecyclerView.setHasFixedSize(true);

        bookedRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        hostelReference = firebaseDatabase.getReference("Hostels");
        bookedHostelReference = firebaseDatabase.getReference("Booked");


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

                for (DataSnapshot hostelSnapShot: dataSnapshot.getChildren()){



                    hostel = hostelSnapShot.getValue(Hostel.class);
                    hostelList.add(hostel);


                }
                String listSize = String.valueOf(hostelList.size());
                recyclerView.setAdapter(adapter);
                Toast.makeText(ViewHostelsActivity.this, listSize, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewHostelsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        bookedHostelReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                bookedSuccessfulArrayList.clear();

                for (DataSnapshot bookedDataSnapshot: dataSnapshot.getChildren()){
                    BookedSuccessful bookedSuccessful = bookedDataSnapshot.getValue(BookedSuccessful.class);
                    bookedSuccessfulArrayList.add(bookedSuccessful);
                }
                bookedRecyclerView.setAdapter(bookedAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ViewHostelsActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }
}
