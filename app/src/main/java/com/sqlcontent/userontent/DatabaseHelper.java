package com.sqlcontent.userontent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ContentTest.db";
    public static final String TABLE_NAME = "Ctest_marked";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "content";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT, content INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,Integer content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, content);

        long sucess = db.insert(TABLE_NAME, null, contentValues);
        if(sucess == -1) return false;
        else
            return true;
    }
    public Cursor getAllData(Integer value){


        String query = "select * from "
                +TABLE_NAME +
                " where "+value+"& content = "+value;
       // Log.e("Log", "getAllData: Query -->>"+query  );
       // String query = "select * from Ctest_marked where content=10";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery(query, null);
        return cur;
    }
    public boolean updateData(String id, Integer content){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_1, id);
        values.put(COL_3, content);

        db.update(TABLE_NAME, values, "ID = ?", new String[]{id});
        return true;

    }
}
