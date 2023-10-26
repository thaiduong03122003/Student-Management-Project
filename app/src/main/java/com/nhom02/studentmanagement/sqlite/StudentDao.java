package com.nhom02.studentmanagement.sqlite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nhom02.studentmanagement.helper.DateTimeHelper;
import com.nhom02.studentmanagement.model.Classes;
import com.nhom02.studentmanagement.model.Student;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
    @SuppressLint("Range")
    public List<Student> get(String sql, String ... selectArgs) throws ParseException {
        List<Student> list = new ArrayList<>();

        Cursor cursor = db.rawQuery(sql, selectArgs);

        while (cursor.moveToNext()) {
            Student std = new Student();
            std.setId(cursor.getString(cursor.getColumnIndex("id")));
            std.setName(cursor.getString(cursor.getColumnIndex("name")));
            std.setDob(DateTimeHelper.toDate(cursor.getString(cursor.getColumnIndex("dob"))));
            std.setClassId(cursor.getInt(cursor.getColumnIndex("classid")));
            list.add(std);
        }
        return list;
    }

    public List<Student> getAllByClass(Integer classId) throws ParseException {
        String sql = "SELECT * FROM students WHERE classid = ?";

        return get(sql, "" + classId);
    }
    public int delete (String id) {
        return db.delete("classes", "id=?", new String[]{id});
    }
}
