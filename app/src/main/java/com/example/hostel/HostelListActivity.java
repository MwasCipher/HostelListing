//package com.example.hostel;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class HostelListActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    ArrayList<Hostel> hostelList;
////    HostelAdapter hostelAdapter;
//    private RecyclerView.Adapter adapter;
////    MyHostelAdapter myHostelAdapter;
//    DatabaseReference hostelReference;
//    FirebaseDatabase firebaseDatabase;
//    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//    Hostel hostel;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_hostel_list);
//        hostelList = new ArrayList<>();
//        adapter = new MyHostelAdapter(hostelList, HostelListActivity.this);
////        myHostelAdapter = new MyHostelAdapter (hostelList, HostelListActivity.this);
//        recyclerView = findViewById(R.id.hostel_list_Recycler_view);
//        recyclerView.setHasFixedSize(true);
////        recyclerView.setAdapter(myHostelAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//
//
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        hostelReference = firebaseDatabase.getReference("Hostels");
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
////        return super.onCreateOptionsMenu(menu);
//
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu, menu);
//        return true;
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if (item.getItemId() == R.id.log_out_menu){
//
//            firebaseAuth.signOut();
//            finish();
//            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//        }
//
//        return super.onOptionsItemSelected(item);
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        hostelReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//                hostelList.clear();
//
//                for (DataSnapshot hostelSnapshot: dataSnapshot.getChildren() ){
//
//                    Hostel hostel = hostelSnapshot.getValue(Hostel.class);
//                    hostelList.add(hostel);
//                }
//
//                recyclerView.setAdapter(adapter);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(HostelListActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//
//
//
//
//            }
//        });
//    }
//}
