package com.example.taskmanager;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("Title");
        String description = intent.getStringExtra("Description");

        Intent mainintent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, new Random().nextInt(), mainintent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "com.example.todoapp");
        builder.setSmallIcon(R.drawable.clock)
                .setContentTitle("Completed the task!!")
                .setContentText(title)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine(title)
                        .addLine(description))
                .setColor(Color.parseColor("#FF0B8B42"))
                .setColorized(true)
                .setContentIntent(contentIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        // Toast.makeText(context.getApplicationContext(), title, Toast.LENGTH_SHORT).show();

        NotificationManagerCompat notificationmanager = NotificationManagerCompat.from(context);

        notificationmanager.notify(new Random().nextInt(), builder.build());
    }
}
