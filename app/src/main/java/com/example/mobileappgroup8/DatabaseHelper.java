package com.example.mobileappgroup8;

/**
 * created by Kerttuli 5.3.2020
 * To create a database
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Date;


public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "user_info";
    private static final int DATABASE_VERSION = 1;

    //database table

    public static final String DB_TABLE = "points_date";

    //columns

    public static final String KEY_ID = "id";
    public static final String KEY_POINTS = "points";
    public static final String KEY_DATE = "date";
    public static final String KEY_RESULT = "result";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_POINTS + " TEXT," +
                KEY_DATE + " DATE NOT NULL, " +
                KEY_RESULT + " TEXT,''" +  ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
    }

    //add content

    public boolean insertData(String points, String date, String result) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_POINTS, points);
        contentValues.put(KEY_DATE, date);
        contentValues.put(KEY_RESULT, result);

        long result_insert = db.insert(DB_TABLE, null, contentValues);

        if (result_insert == -1) {
            return false;
        }
        return true;
    }

    //get content

    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DB_TABLE, null);
        return cursor;
    }

    //delete content

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + DB_TABLE);
        db.close();
    }

    public int deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(DB_TABLE, "ID = ?", new String[]{String.valueOf(id)});
    }

    public SQLiteDatabase getDb() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db;
    }
}


