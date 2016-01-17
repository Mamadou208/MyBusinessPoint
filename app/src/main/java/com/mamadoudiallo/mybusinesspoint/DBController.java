package com.mamadoudiallo.mybusinesspoint;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mamadoudiallo.mybusinesspoint.model.BusinessPoint;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mamadou on 14/01/2016.
 */
public class DBController extends SQLiteOpenHelper {


    public static final String id = "ID";  // auto generated ID column
    public static final String subject = "subject"; // column name
    public static final String detail = "detail"; // column name
    public static final String teacher = "teacher"; // column name
    public static final String grade = "grade"; // column name
    public static final String status = "status"; // column name
    private static final String[] COLUMNS = {id, subject, detail, teacher, grade, status};

    private static final String tablename = "business_points";  // tablename
    private static final String databasename = "businessPoint"; // Dtabasename
    private static final int versioncode = 2; //versioncode of the database

    public DBController(Context context) {
        super(context, databasename, null, versioncode);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE IF NOT EXISTS " + tablename + "(" + id + " integer primary key, " + subject + " TEXT, " + detail + " TEXT, " + teacher + " TEXT, " + grade + " INTEGER, " + status + " INTEGER )";
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
                map.put("status", cursor.getString(5));
                wordList.add(map);
            } while (cursor.moveToNext());
        }
        // return contact list
        return wordList;
    }


    public BusinessPoint getBusinessPointById(int id) {

        // 1. get reference to readable DB
        SQLiteDatabase db = this.getReadableDatabase();
        BusinessPoint businessPoint = new BusinessPoint();
        // 2. build query
        Cursor cursor =
                db.query(tablename, // a. table
                        COLUMNS, // b. column names
                        " id = ?", // c. selections
                        new String[]{String.valueOf(id)}, // d. selections args
                        null, // e. group by
                        null, // f. having
                        null, // g. order by
                        null); // h. limit

        // 3. if we got results get the first one
        if (cursor != null && cursor.moveToFirst())
        {
            // 4. build businessPoint object
            businessPoint.setId(Integer.parseInt(cursor.getString(0)));
            businessPoint.setSubject(cursor.getString(1));
            businessPoint.setDetail(cursor.getString(2));
            businessPoint.setTeacher(cursor.getString(3));
            businessPoint.setGrade(cursor.getString(4));
            businessPoint.setStatus(cursor.getString(5));

            //log
            Log.d("getBusinessPointById(" + id + ")", businessPoint.toString());

        }

        // 5. return businessPoint
        return businessPoint;

    }
}