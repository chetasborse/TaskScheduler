package com.example.taskmanager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Customadapter extends ArrayAdapter<myproduct> {

    public Customadapter(@NonNull Context context, ArrayList<myproduct> arrL) {
        super(context, R.layout.individualist, arrL);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View Customview = myInflater.inflate(R.layout.individualist, parent, false);

        String what = getItem(position).get_pend();


        RelativeLayout rl = (RelativeLayout) Customview.findViewById(R.id.r1);
        TextView title = (TextView) Customview.findViewById(R.id.checkbox);
        TextView description = (TextView) Customview.findViewById(R.id.descriptext);
        TextView date = (TextView) Customview.findViewById(R.id.date);
        TextView time = (TextView) Customview.findViewById(R.id.time);
        TextView descrip = (TextView) Customview.findViewById(R.id.description);

        if(what.equals("0")) {
            rl.setBackgroundColor(Color.parseColor("#FFFCB9B7"));
            title.setTextColor(Color.parseColor("#FFFF0400"));
            description.setTextColor(Color.parseColor("#FFFF0400"));
            date.setTextColor(Color.parseColor("#FFFF0400"));
            time.setTextColor(Color.parseColor("#FFFF0400"));
            descrip.setTextColor(Color.parseColor("#FFFF0400"));
        }
        else
            rl.setBackgroundColor(Color.parseColor("#FFE1FCEA"));



        title.setText(getItem(position).get_title());
        description.setText(getItem(position).get_description());
        date.setText(getItem(position).get_date());
        time.setText(getItem(position).get_time());

        return Customview;

    }
}
