package com.example.hostel;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HostelListAdapter extends RecyclerView.Adapter<HostelListAdapter.ViewHolder> {
    private ArrayList<Hostel> hostelArrayList;
    private Context context;

    public HostelListAdapter(ArrayList<Hostel> hostelArrayList, Context context) {
        this.hostelArrayList = hostelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View hostelListView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hostel_item, viewGroup, false);
        return new ViewHolder(hostelListView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Hostel currentHostel = hostelArrayList.get(i);

        viewHolder.hostelNameTV.setText(currentHostel.getHostelName());
        Log.d("Test", "Name: " +currentHostel.getHostelName());
        viewHolder.hostelDescriptionTV.setText(currentHostel.getHostelDescription());
        viewHolder.hostelCapacityTV.setText(currentHostel.getHostelCapacity());
        viewHolder.hostelPriceTV.setText(currentHostel.getHostelPrice());

        final String hostel_name = currentHostel.getHostelName();
        final String hostel_price = currentHostel.getHostelPrice();

        final String userType = String.valueOf(FirebaseDatabase.getInstance().getReference("users")
                .child(currentHostel.getHostelOwnerId()).child("userType"));

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    Intent openPayment = new Intent(context, PaymentActivity.class);
                    openPayment.putExtra("hostelName", hostel_name);
                    openPayment.putExtra("hostelPrice", hostel_price);
                    openPayment.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    openPayment.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(openPayment);

            }
        });

    }

    @Override
    public int getItemCount() {
        return hostelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView hostelNameTV, hostelDescriptionTV, hostelCapacityTV, hostelPriceTV;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hostelNameTV = itemView.findViewById(R.id.hostel_Name_TextView);
            hostelDescriptionTV = itemView.findViewById(R.id.hostel_Description_TextView);
            hostelCapacityTV = itemView.findViewById(R.id.hostel_Capacity_TextView);
            hostelPriceTV = itemView.findViewById(R.id.hostel_Price_TextView);
            linearLayout = itemView.findViewById(R.id.hostel_list_linear_layout);
        }
    }
}
