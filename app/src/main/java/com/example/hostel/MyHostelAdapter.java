package com.example.hostel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MyHostelAdapter extends RecyclerView.Adapter<MyHostelAdapter.ViewHolder>{

    private List<Hostel> hostelList;
    private Context context;

    public MyHostelAdapter(List<Hostel> hostelList, Context context) {
        this.hostelList = hostelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View hostelView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hostel_list_item, viewGroup, false);
        return new ViewHolder(hostelView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Hostel currentHostel = hostelList.get(i);

        viewHolder.hostelNameTV.setText(currentHostel.getHostelName());
        viewHolder.hostelDescriptionTV.setText(currentHostel.getHostelDescription());
        viewHolder.hostelCapacityTV.setText(currentHostel.getHostelCapacity());
        viewHolder.hostelPriceTV.setText(currentHostel.getHostelPrice());


    }

    @Override
    public int getItemCount() {
        return hostelList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView hostelNameTV, hostelDescriptionTV, hostelCapacityTV, hostelPriceTV;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            hostelNameTV = itemView.findViewById(R.id.hostel_Name_list_item_Text_View);
            hostelDescriptionTV = itemView.findViewById(R.id.hostel_Description_List_item_Text_view);
            hostelCapacityTV = itemView.findViewById(R.id.hostel_Capacity_List_item_Text_View);
            hostelPriceTV = itemView.findViewById(R.id.hostel_Price_List_item_Text_View);

//            linearLayout = itemView.findViewById(R.id.hostel_list_linear_layout);
        }
    }
}
