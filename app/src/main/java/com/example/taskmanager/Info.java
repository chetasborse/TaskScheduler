package com.example.taskmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Info extends AppCompatActivity {


    public ArrayList<infoelements> info1 = new ArrayList<infoelements>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ListView list = (ListView) findViewById(R.id.listview2);


        final Custom2adapter myadapter = new Custom2adapter(this, infoelements.getInfo());
        list.setAdapter(myadapter);


    }
}

