package com.example.taskmanager;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class infoelements {

    private String title;
    private int id;

    public infoelements(String title, int id) {
        this.title = title;
        this.id = id;
    }

    public final static ArrayList<infoelements> info = new ArrayList<infoelements>(Arrays.asList( new infoelements("1. You can delete/ add/ edit tasks in Task Manager", R.drawable.imc),
            new infoelements("2. You get a choice to receive notification for your each task", R.drawable.imb),
            new infoelements("3. Each task turns red in color once the mentioned reminder time has passed", R.drawable.ima)));

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public static ArrayList<infoelements> getInfo() {
        return info;
    }
}
