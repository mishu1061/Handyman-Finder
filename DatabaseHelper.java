package com.rbl.prateek.habdyman_rbl;

/**
 * Created by PRATEEK on 5/1/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Handyman.db";
    public static final String TABLE_NAME = "all_info";
    public static final String COL_1 = "rowid";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Contact";
    public static final String COL_4 = "Address";
    public static final String COL_5 = "Skill";
    public static final String COL_6 = "Min_charge";
    public static final String COL_7 = "Pincode";
    public static final String COL_8 = "City";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +"(rowid INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, Contact INTEGER, Address TEXT, Skill Text, Min_Charge INTEGER, Pincode INTEGER, City TEXT) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String contact,String address,String skill,String min_charge,String pincode,String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,contact);
        contentValues.put(COL_4,address);
        contentValues.put(COL_5,skill);
        contentValues.put(COL_6,min_charge);
        contentValues.put(COL_7,pincode);
        contentValues.put(COL_8,city);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public Cursor getAllData(String pin)  {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("SELECT * from "+TABLE_NAME+" WHERE "+COL_7+"='"+pin+"'",null);
        return res;
    }
}
