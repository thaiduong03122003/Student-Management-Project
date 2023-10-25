package com.nhom02.studentmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nhom02.studentmanagement.R;
import com.nhom02.studentmanagement.adapter.ClassesAdapter;
import com.nhom02.studentmanagement.helper.DateTimeHelper;
import com.nhom02.studentmanagement.model.Classes;
import com.nhom02.studentmanagement.model.Student;
import com.nhom02.studentmanagement.sqlite.ClassesDao;
import com.nhom02.studentmanagement.sqlite.StudentDao;

import java.util.List;

public class ManageStudentsActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etStudentId, etName, etDob;
    private Spinner spClasses;
    private List<Classes> classesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);
        etStudentId = findViewById(R.id.etStuId);
        etName = findViewById(R.id.etStuName);
        etDob = findViewById(R.id.etStuDob);
        spClasses = findViewById(R.id.spClasses);

        fillClassesToSpinner();

        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
    }
    private void fillClassesToSpinner(){
        ClassesDao dao = new ClassesDao(this);
        classesList = dao.getAll();
        ClassesAdapter classesAdapter = new ClassesAdapter(this, classesList);
        spClasses.setAdapter(classesAdapter);
    }

    @Override
    public void onClick(View v) {
        StudentDao dao = new StudentDao(this);
        if (v.getId() == R.id.btnSave) {
            try {
                Student std = new Student();
                std.setId(etStudentId.getText().toString());
                std.setName(etName.getText().toString());
                std.setDob(DateTimeHelper.toDate(etDob.getText().toString()));

                Classes cls = (Classes) spClasses.getSelectedItem();
                std.setClassId(cls.getId());
                String msg;

                dao.insert(std);

                Toast.makeText(this, "Đã lưu sinh viên ID: " + std.getId(), Toast.LENGTH_LONG).show();

                etStudentId.setText("");
                etName.setText("");
                etDob.setText("");
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.btnDelete) {

        }

    }
}