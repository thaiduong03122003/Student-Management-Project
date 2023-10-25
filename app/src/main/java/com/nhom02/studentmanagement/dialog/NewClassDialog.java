package com.nhom02.studentmanagement.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.nhom02.studentmanagement.R;
import com.nhom02.studentmanagement.model.Classes;
import com.nhom02.studentmanagement.sqlite.ClassesDao;

public class NewClassDialog extends Dialog implements View.OnClickListener {
    private EditText etClassId, etName;
    private Context context;
    public NewClassDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_new_class);

        etClassId = findViewById(R.id.etClassId);
        etName = findViewById(R.id.etName);

        findViewById(R.id.btnSave).setOnClickListener(this);
        findViewById(R.id.btnClose).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSave) {
            Classes cls = new Classes();
            cls.setName(etName.getText().toString());
            ClassesDao dao = new ClassesDao(context);
            dao.insert(cls);
            Toast.makeText(context, "Đã lưu lớp: " + cls.getName(), Toast.LENGTH_SHORT).show();
            dismiss();
        }
        if (v.getId() == R.id.btnClose) {
            dismiss();
        }

    }
}
