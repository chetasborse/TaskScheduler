package com.example.taskmanager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Random;

public class nextactivity extends AppCompatActivity {

    public EditText title;
    public EditText time;
    public EditText date1;
    public EditText description;
    public Calendar c = Calendar.getInstance();


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nextactivity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        description = (EditText) findViewById(R.id.editText);
        time = (EditText) findViewById(R.id.editText3);
        title = (EditText) findViewById(R.id.editText4);
        date1 = (EditText) findViewById(R.id.editText2);

        Intent intent = getIntent();
        final String title11 = intent.getStringExtra("Title");
        String description11 = intent.getStringExtra("Description");
        String date11 = intent.getStringExtra("Date");
        String time11 = intent.getStringExtra("Time");

        final CalendarView calenderview = findViewById(R.id.calendarView);
        final TimePicker timePicker = findViewById(R.id.timepicker);
        createChannels();
        if(title11 != null || description11 != null || date11 != null || time11!= null) {
            description.setText(description11);
            title.setText(title11);
            time.setText(time11);
            date1.setText(date11);
            String[] parts = date11.split("/");
            String[] parts2 = time11.split(":");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]) - 1;
            int year = 2000 + Integer.parseInt(parts[2]);
            int hr = Integer.parseInt(parts2[0]);
            int min = Integer.parseInt(parts2[1]);


            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);

            Calendar calen = Calendar.getInstance();
            calen.set(Calendar.HOUR_OF_DAY, hr);
            calen.set(Calendar.MINUTE, min);

            long milli = calendar.getTimeInMillis();
            calenderview.setDate(milli, true, true);

            timePicker.setHour(hr);
            timePicker.setMinute(min);

        }

        calenderview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int date) {
                int yea = year % 100;
                String string = String.format("%02d/%02d/%02d",date, month + 1, yea);
                date1.setText(string);
                c.set(Calendar.YEAR, year);
                c.set(Calendar.DAY_OF_MONTH, date);
                c.set(Calendar.MONTH, month);

            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hour, int min) {
                String string = String.format("%02d:%02d", hour, min);
                time.setText(string);
                c.set(Calendar.MINUTE, min);
                c.set(Calendar.HOUR_OF_DAY, hour);
                c.set(Calendar.SECOND, 0);


            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void oncheck(View view) {

        Intent intent1 = new Intent(nextactivity.this, AlarmReceiver.class);
        intent1.putExtra("Title", title.getText().toString());
        intent1.putExtra("Description", description.getText().toString());

        PendingIntent alarmintent = PendingIntent.getBroadcast(nextactivity.this, new Random().nextInt(), intent1, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarm.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), alarmintent);
    }

    public void setdate(View view) {

        description = (EditText) findViewById(R.id.editText);
        time = (EditText) findViewById(R.id.editText3);
        title = (EditText) findViewById(R.id.editText4);
        date1 = (EditText) findViewById(R.id.editText2);


        Intent intent = new Intent(nextactivity.this, MainActivity.class);
        intent.putExtra("Title", title.getText().toString());
        intent.putExtra("Description", description.getText().toString());
        intent.putExtra("Date", date1.getText().toString());
        intent.putExtra("pend", Long.toString(c.getTimeInMillis()));
        intent.putExtra("Time", time.getText().toString());

        startActivity(intent);
        finish();

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createChannels() {

        CharSequence name = "Chetas Channel";
        String description = "Channel for Chetas";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel("com.example.todoapp", name, importance);
        channel.setDescription(description);

        NotificationManager notificationManager =getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }



}
