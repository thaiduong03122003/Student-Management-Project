package com.nhom02.studentmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhom02.studentmanagement.R;
import com.nhom02.studentmanagement.helper.DateTimeHelper;
import com.nhom02.studentmanagement.model.Student;

import org.w3c.dom.Text;

import java.util.List;

public class StudentsAdapter extends BaseAdapter {
    private Context context;
    private List<Student> list;

    public StudentsAdapter(Context context, List<Student> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_student_item, null);
        }

        TextView tvStudentId = view.findViewById(R.id.tvStudentId);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDob = view.findViewById(R.id.tvDob);

        Student std = list.get(i);
        tvStudentId.setText(std.getId());
        tvName.setText(std.getName());
        tvDob.setText(DateTimeHelper.toString(std.getDob()));

        return view;
    }
}
