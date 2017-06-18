package com.vcooline.li.videoplayer.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Trace on 2014/8/21.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "video1.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "sketch";
    private static final String CREATE_TABLE_SQL = "CREATE TABLE "+ TABLE_NAME + " (pid INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"id VARCHAR,materialName VARCHAR,materialPath VARCHAR,materialKey VARCHAR,materialType VARCHAR," +
            "materialClasses VARCHAR,materialSubClasses VARCHAR, isMediaImage VARCHAR,thumbnailName VARCHAR," +
            "thumbnailPath VARCHAR,finishedName VARCHAR,finishedPath VARCHAR,finishedHdName VARCHAR," +
            "finishedHdPath VARCHAR,sortId VARCHAR,classSort VARCHAR,uploadedAt VARCHAR," +
            "createAt VARCHAR,updateAt VARCHAR,status VARCHAR)";


    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }
}
