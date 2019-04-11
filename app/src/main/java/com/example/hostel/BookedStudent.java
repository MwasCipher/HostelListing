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

public class BookedStudent extends AppCompatActivity {

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ArrayList<BookedSuccessful> bookedSuccessfulArrayList;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_student);

        recyclerView = findViewById(R.id.booked_Student_Recycler_view);
        bookedSuccessfulArrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

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
        return  super.onOptionsItemSelected(item);



    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference = firebaseDatabase.getReference("Booked");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                bookedSuccessfulArrayList.clear();

                for (DataSnapshot bookedRoomSnapshot : dataSnapshot.getChildren()){

                    BookedSuccessful bookedSuccessful = bookedRoomSnapshot.getValue(BookedSuccessful.class);
                    bookedSuccessfulArrayList.add(bookedSuccessful);
                }

                adapter = new BookedSuccessfulAdapter(BookedStudent.this,bookedSuccessfulArrayList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(BookedStudent.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
