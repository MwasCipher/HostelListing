package com.example.hostel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BookedSuccessfulAdapter extends RecyclerView.Adapter<BookedSuccessfulAdapter.ViewHolder> {

    Context context;
    ArrayList<BookedSuccessful> arrayList;

    public BookedSuccessfulAdapter(Context context, ArrayList<BookedSuccessful> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BookedSuccessfulAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.booked_hostel, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookedSuccessfulAdapter.ViewHolder viewHolder, int i) {

        BookedSuccessful currentSuccessfulBooking = arrayList.get(i);

        viewHolder.bookedHostelNameTV.setText("Hostel Name: " +currentSuccessfulBooking.getBookedHostelName());
        viewHolder.bookingStudentNameTV.setText("Student Name:" + currentSuccessfulBooking.getBookedStudentName());
        viewHolder.bookedHostelAmountTV.setText("Amount: "+ currentSuccessfulBooking.getBookedHostelAmount());
        viewHolder.bookedStudentPhoneNumber.setText("Phone Number: " + currentSuccessfulBooking.getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookedHostelNameTV,bookedHostelAmountTV, bookingStudentNameTV, successMessageTV, bookedStudentPhoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookedHostelNameTV = itemView.findViewById(R.id.hostel_name_tv);
            bookingStudentNameTV = itemView.findViewById(R.id.booking_student_name_tv);
            bookedHostelAmountTV = itemView.findViewById(R.id.amount_paid_tv);
            bookedStudentPhoneNumber = itemView.findViewById(R.id.booked_student_phone_number_tv);
            successMessageTV = itemView.findViewById(R.id.success_status_text_view);
        }
    }
}
