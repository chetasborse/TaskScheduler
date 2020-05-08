package com.example.taskmanager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    public ArrayList<myproduct> arrL = new ArrayList<myproduct>();

    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHandler = new MyDBHandler(this, null, null, 1);

        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);


        final String title = intent.getStringExtra("Title");
        String description = intent.getStringExtra("Description");
        String date = intent.getStringExtra("Date");
        String time = intent.getStringExtra("Time");
        String pend = intent.getStringExtra("pend");
//        int ii = intent.getIntExtra("id", 0);


        myproduct news = new myproduct(title, description, date, time, pend/*, oritime*/);
//        news.set_id(ii);
        dbHandler.addProduct(news);
        arrL.addAll(dbHandler.getlist());
        final Customadapter myadapter = new Customadapter(this, arrL);

        //    final SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.listview);
        final ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(myadapter);



        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {


                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Task Completed!!")
                        .setMessage("Do you want to delete the task")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TextView chec = view.findViewById(R.id.checkbox);
                                TextView chec1 = view.findViewById(R.id.descriptext);
                                dbHandler.deleteProduct(chec.getText().toString(), chec1.getText().toString());
//                                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                                Intent myIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
//                                PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                                        getApplicationContext(), i, myIntent, PendingIntent.FLAG_CANCEL_CURRENT);
//                                alarmManager.cancel(pendingIntent);
                                arrL.remove(position);
                                myadapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .setNeutralButton("  Edit Task  ", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(MainActivity.this, nextactivity.class);
                                TextView text1 = view.findViewById(R.id.checkbox);
                                TextView text2 = view.findViewById(R.id.descriptext);
                                TextView text3 = view.findViewById(R.id.date);
                                TextView text4 = view.findViewById(R.id.time);


                                intent.putExtra("Title", text1.getText().toString());
                                intent.putExtra("Description", text2.getText().toString());
                                intent.putExtra("Date", text3.getText().toString());
                                intent.putExtra("Time", text4.getText().toString());
                                startActivity(intent);
                                finish();
                                dbHandler.deleteProduct(text1.getText().toString(), text2.getText().toString());
                                arrL.remove(position);
                                myadapter.notifyDataSetChanged();
                            }
                        })
                        .create();
                dialog.show();
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                Button neutralButton = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                positiveButton.setTextColor(Color.parseColor("#FF0B8B42"));
                positiveButton.setBackgroundColor(Color.parseColor("#FFE1FCEA"));

                negativeButton.setTextColor(Color.parseColor("#FFFF0400"));
                negativeButton.setBackgroundColor(Color.parseColor("#FFFCB9B7"));

                neutralButton.setTextColor(Color.parseColor("#FF1B5AAC"));
                neutralButton.setBackgroundColor(Color.parseColor("#FFD9E9FF"));
            }
        };



        listView.setOnItemClickListener(onItemClickListener);

    }


    public void addonclick(View view) {
        Intent intent = new Intent(MainActivity.this, nextactivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }
        else if(id == R.id.action_info) {

            Intent intent = new Intent(MainActivity.this, Info.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_menu) {

            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
