package com.nhom02.studentmanagement.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.nhom02.studentmanagement.helper.DateTimeHelper;
import com.nhom02.studentmanagement.model.Classes;
import com.nhom02.studentmanagement.model.Student;

public class StudentDao {
    private SQLiteDatabase db;

    public StudentDao(Context context) {
        DBHelper helper = new DBHelper(context);

        this.db = helper.getWritableDatabase();
    }

    public long insert(Student emp) {
        ContentValues values = new ContentValues();
        values.put("id", emp.getId());
        values.put("name", emp.getName());
        values.put("dob", DateTimeHelper.toString(emp.getDob()));
        values.put("classid", emp.getClassId());

        return db.insert("students", null, values);
    }
}
