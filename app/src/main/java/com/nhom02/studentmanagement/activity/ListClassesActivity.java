package com.nhom02.studentmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.nhom02.studentmanagement.R;
import com.nhom02.studentmanagement.adapter.ClassesAdapter;
import com.nhom02.studentmanagement.model.Classes;
import com.nhom02.studentmanagement.sqlite.ClassesDao;
import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class ListClassesActivity extends AppCompatActivity {
    private ListView lvClasses;
    private List<Classes> list;
    private ClassesAdapter clsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_classes);

        lvClasses = findViewById(R.id.lvClasses);

        fillClassesListView();
        lvClasses.setOnItemLongClickListener((parent, view, position, id) -> {
            final ClassesDao dao = new ClassesDao(ListClassesActivity.this);
            final Classes cls = list.get(position);

            AlertDialog.Builder builder = new AlertDialog.Builder(ListClassesActivity.this);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc muốn xóa lớp " + cls.getName() + "?");

            builder.setPositiveButton("Hủy", (dialog, which) -> dialog.dismiss());

            builder.setNegativeButton("Xóa", (dialog, which) -> {
                Toast.makeText(this, "Đã xóa lớp " + cls.getName() + " thành công!", Toast.LENGTH_SHORT).show();
                dao.delete("" + cls.getId());
                fillClassesListView();
            });

            builder.show();
            return true;
        });

    }

    private void fillClassesListView() {
        ClassesDao dao = new ClassesDao(this);
        list = dao.getAll();

        clsAdapter = new ClassesAdapter(this, list);
        lvClasses.setAdapter(clsAdapter);
    }
}