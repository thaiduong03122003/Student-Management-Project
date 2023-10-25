package com.nhom02.studentmanagement.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "stumanager";
    private static final int DB_VERSION = 1;
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String classesSql = "CREATE TABLE classes(id integer primary key autoincrement, " + " name text not null)";
        String studenstSql = "CREATE TABLE students(id text primary key, " +
                " name text not null, classid integer, dob text, " +
                " FOREIGN KEY (classid) REFERENCES classes(id))";
        db.execSQL(classesSql);
        db.execSQL(studenstSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String classesSql = "DROP TABLE IF EXISTS classes";
        String studentsSql = "DROP TABLE IF EXISTS students";

        db.execSQL(studentsSql);
        db.execSQL(classesSql);

        onCreate(db);
    }
}
