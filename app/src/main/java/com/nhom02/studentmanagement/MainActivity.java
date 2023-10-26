package com.nhom02.studentmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.nhom02.studentmanagement.activity.ListClassesActivity;
import com.nhom02.studentmanagement.activity.LoginActivity;
import com.nhom02.studentmanagement.activity.ManageStudentsActivity;
import com.nhom02.studentmanagement.dialog.NewClassDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnNewClass).setOnClickListener(this);
        findViewById(R.id.btnListClasses).setOnClickListener(this);
        findViewById(R.id.btnManageStudents).setOnClickListener(this);
        findViewById(R.id.btnLogout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnNewClass) {
            NewClassDialog dialog = new NewClassDialog(this);
            dialog.show();
        }
        if (view.getId() == R.id.btnListClasses) {
            Intent intent = new Intent(this, ListClassesActivity.class);
            startActivity(intent);

        }
        if (view.getId() == R.id.btnManageStudents) {
            Intent mngintent = new Intent(this, ManageStudentsActivity.class);
            startActivity(mngintent);
        }
        if (view.getId() == R.id.btnLogout) {
            clearAutoLogin();
            Intent intentLogin = new Intent(this, LoginActivity.class);
            startActivity(intentLogin);
            finish();
        }
    }
    private void clearAutoLogin() {
        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.clear();

        editor.commit();
    }
}