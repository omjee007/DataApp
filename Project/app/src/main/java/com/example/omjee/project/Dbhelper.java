package com.example.omjee.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by omjee on 7/2/2017.
 */

public class Dbhelper extends SQLiteOpenHelper {
    public final static String database_name = "user.db";
    public static final String table_name = "account_table";
    public static final String Col_1 = "ID";
    public static final String Col_2 = "NAME";
    public static final String Col_3 = "EMAIL";
    public static final String Col_4 = "PHONE";
    public static final String Col_5 = "PASSWORD";

    public Dbhelper(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,PHONE TEXT,PASSWORD TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + table_name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertdata(String name, String email, String phone, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_2, name);
        contentValues.put(Col_3, email);
        contentValues.put(Col_4, phone);
        contentValues.put(Col_5, password);
        long result = sqLiteDatabase.insert(table_name, null, contentValues);
        if (result == -1) {
            return false;
        }
        return true;
    }


    public Cursor searchKeyString(String user, String password) {
        String rtn = "";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + table_name + " where EMAIL='" + user + "' and PASSWORD='" + password + "'", null);
        return res;
    }

    public Cursor getdata() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select*from " + table_name, null);
        return cursor;
    }

    public boolean updatedata(String id, String name, String email, String phone, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_1, id);
        contentValues.put(Col_2, name);
        contentValues.put(Col_3, email);
        contentValues.put(Col_4, phone);
        contentValues.put(Col_5, password);
        sqLiteDatabase.update(table_name, contentValues, "ID = ?", new String[] {id});
        return true;
    }
}
