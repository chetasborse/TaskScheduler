package com.example.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Custom2adapter extends ArrayAdapter<infoelements> {

    public Custom2adapter(@NonNull Context context, ArrayList<infoelements> info) {
        super(context, R.layout.infolay, info);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View Customview = myInflater.inflate(R.layout.infolay, parent, false);

        TextView textView = (TextView) Customview.findViewById(R.id.tek1);
        ImageView imageView = (ImageView) Customview.findViewById(R.id.ima);

        textView.setText(getItem(position).getTitle());
        imageView.setImageResource(getItem(position).getId());

        return Customview;
    }
}
