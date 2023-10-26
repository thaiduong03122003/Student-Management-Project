package com.nhom02.studentmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.nhom02.studentmanagement.R;
import com.nhom02.studentmanagement.adapter.ClassesAdapter;
import com.nhom02.studentmanagement.adapter.StudentsAdapter;
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
    private List<Student> studentList;
    private ListView lvStudents;

    private StudentsAdapter studentsAdapter;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);
        etStudentId = findViewById(R.id.etStuId);
        etName = findViewById(R.id.etStuName);
        etDob = findViewById(R.id.etStuDob);
        spClasses = findViewById(R.id.spClasses);

        lvStudents = findViewById(R.id.lvStudents);

        lvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Student std = studentList.get(i);
                etStudentId.setText(std.getId());
                etName.setText(std.getName());
                etDob.setText(DateTimeHelper.toString(std.getDob()));
                isEdit = true;
            }
        });

        fillClassesToSpinner();

        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnDelete).setOnClickListener(this);
    }
    private void fillClassesToSpinner(){
        ClassesDao dao = new ClassesDao(this);
        classesList = dao.getAll();
        ClassesAdapter classesAdapter = new ClassesAdapter(this, classesList);
        spClasses.setAdapter(classesAdapter);

        spClasses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                fillStudentsToListView();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fillStudentsToListView(){
        StudentDao dao = new StudentDao(this);
        try {
            Classes cls = (Classes) spClasses.getSelectedItem();
            studentList = dao.getAllByClass(cls.getId());

            studentsAdapter = new StudentsAdapter(this, studentList);
            lvStudents.setAdapter(studentsAdapter);


        }
        catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

                if (!isEdit) {
                    dao.insert(std);
                    Toast.makeText(this, "Đã lưu sinh viên ID: " + std.getId(), Toast.LENGTH_LONG).show();
                }
                else {
                    dao.update(std);
                    Toast.makeText(this, "Sinh viên ID " + std.getId() + " đã được cập nhật!", Toast.LENGTH_SHORT).show();
                }
                clearInputField();
                isEdit = false;
                fillStudentsToListView();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Toast.makeText(this, "Error: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if (v.getId() == R.id.btnDelete) {
            if (isEdit && !etStudentId.getText().toString().equals("")) {
                String id = etStudentId.getText().toString();

                dao.delete(id);
                Toast.makeText(this, "Đã xóa sinh viên ID " + id + " thành công!", Toast.LENGTH_SHORT).show();
                clearInputField();
                id = "";
                fillStudentsToListView();
            }
            else {
                Toast.makeText(this, "Bạn phải chọn một sinh viên để xóa!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void clearInputField() {
        etStudentId.setText("");
        etName.setText("");
        etDob.setText("");
    }
}