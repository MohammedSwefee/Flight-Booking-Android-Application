package com.example.mohammed.afinal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBnew  extends SQLiteOpenHelper {

    DBnew(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {//نخلق داتابيس
        db.execSQL("CREATE TABLE FL( id INTEGER PRIMARY KEY ,COMPANY TEXT,ORIGIN TEXT,DESTINATION TEXT,PLANE TEXT,FLIGHT_NUM TEXT,FLIGHT_NAME TEXT, LND_DATE TEXT,SEATS TEXT,SERVICE TEXT,COST TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
