package com.example.taskmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    private static final String DATABASE_PRODUCT = "myproduct";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_PEND = "pend";
//    private static final String COLUMN_ORITIME = "oritime";

    public MyDBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + DATABASE_PRODUCT + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_DATE + " TEXT, " + COLUMN_TIME + " TEXT, " + COLUMN_PEND + " TEXT " /*+ COLUMN_ORITIME + " TEXT "*/ + " ); ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_PRODUCT);
        onCreate(db);
    }

    public void addProduct(myproduct products) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, products.get_title());
        values.put(COLUMN_DESCRIPTION, products.get_description());
        values.put(COLUMN_DATE, products.get_date());
        values.put(COLUMN_TIME, products.get_time());
        values.put(COLUMN_PEND, products.get_pend());
//        values.put(COLUMN_ORITIME, products.get_oritime());
//        values.put(COLUMN_ORIDATE, products.get_oridate());


        SQLiteDatabase db = getWritableDatabase();
        db.insert(DATABASE_PRODUCT, null, values);
        db.close();
    }

    public void deleteProduct(String productname, String productname2) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(" DELETE FROM " + DATABASE_PRODUCT + " WHERE " + " (" + COLUMN_TITLE + "=\"" + productname + "\") " + " AND " + " (" + COLUMN_DESCRIPTION + "=\"" + productname2 + "\") " + ";");


//        Cursor c = db.rawQuery(query, null);
//        c.moveToFirst();
//        while (!c.isAfterLast()) {
//            if(c.getString(c.getColumnIndex("title")) == productname && c.getString(c.getColumnIndex("description")) == productname2) {
//                int i = c.getInt(c.getColumnIndex("_id"));
//                db.close();
//                return i;
//            }
//            c.moveToNext();
//        }
//        return 0;
    }


    public ArrayList<myproduct> getlist() {

        ArrayList<myproduct> arraylist = new ArrayList<myproduct>();
        SQLiteDatabase db = getWritableDatabase();

        String query = " SELECT * FROM " + DATABASE_PRODUCT + " WHERE 1 ";

        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("title")) != null || c.getString(c.getColumnIndex("description")) != null || c.getString(c.getColumnIndex("date")) != null || c.getString(c.getColumnIndex("time")) != null /*|| c.getString(c.getColumnIndex("oritime")) != null || c.getString(c.getColumnIndex("oridate")) != null*/) {
                myproduct product = new myproduct(c.getString(c.getColumnIndex("title")), c.getString(c.getColumnIndex("description")), c.getString(c.getColumnIndex("date")), c.getString(c.getColumnIndex("time")), c.getString(c.getColumnIndex("pend"))/*, c.getString(c.getColumnIndex("oritime"))*/);
                long penda = Long.parseLong(c.getString(c.getColumnIndex("pend")));
                if(System.currentTimeMillis() >= penda) {
                    db.execSQL("UPDATE " + DATABASE_PRODUCT + " SET pend = " + "'" + "0" + "' " + "WHERE _id = " + "'" + c.getString(c.getColumnIndex("_id")) + "'");
                }
                arraylist.add(product);
            }
            c.moveToNext();
        }

        db.close();
        return arraylist;
    }


}
