package com.example.hostel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class HostelAdapter extends ArrayAdapter<Hostel> {

    public HostelAdapter(Context context, int resource, ArrayList<Hostel> hostelList) {
        super(context, 0, hostelList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View listItemView = convertView;

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.hostel_item, parent, false);
        }

        Hostel currentHostel = getItem(position);

        TextView HnameHolder = listItemView.findViewById(R.id.hostelNameTextView);
        assert currentHostel != null;
        HnameHolder.setText(currentHostel.getHostelName());

        TextView HDescriptionHolder = listItemView.findViewById(R.id.hostelDescriptionTextView);
        HDescriptionHolder.setText(currentHostel.getHostelDescription());

        TextView HCapacityHolder = listItemView.findViewById(R.id.hostelCapacityTextView);
        HCapacityHolder.setText(currentHostel.getHostelCapacity());


        return listItemView;
    }
}
