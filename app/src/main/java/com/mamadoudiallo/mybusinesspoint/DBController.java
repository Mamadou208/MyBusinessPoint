package com.mamadoudiallo.mybusinesspoint;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mamadou on 14/01/2016.
 */
public class DBController extends SQLiteOpenHelper {
    private static final String tablename = "business_points";  // tablename
    private static final String id = "ID";  // auto generated ID column
    private static final String subject = "subject"; // column name
    private static final String detail = "detail"; // column name
    private static final String teacher = "teacher"; // column name
    private static final String grade = "grade"; // column name
    //private static final String createdAt = "createdAt"; // column name

    private static final String databasename = "businessPoint"; // Dtabasename
    private static final int versioncode = 1; //versioncode of the database

    public DBController(Context context) {
        super(context, databasename, null, versioncode);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE IF NOT EXISTS " + tablename + "(" + id + " integer primary key, " + subject + " TEXT, " + detail + " TEXT, "+ teacher + " TEXT, " + grade + " INTEGER)";
        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old,
                          int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS " + tablename;
        database.execSQL(query);
        onCreate(database);
    }

    //To get the data from the database I am using ArrayList
    // which will fetch all the data present in the table.
    public ArrayList<HashMap<String, String>> getAllPlace() {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();
        String selectQuery = "SELECT  * FROM " + tablename;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("id", cursor.getString(0));
                map.put("subject", cursor.getString(1));
                map.put("detail", cursor.getString(2));
                map.put("teacher", cursor.getString(3));
                map.put("grade", cursor.getString(4));
               // map.put("createdAt", cursor.getString(5));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        // return contact list
        return wordList;
    }
}