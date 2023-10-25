package com.nhom02.studentmanagement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhom02.studentmanagement.R;
import com.nhom02.studentmanagement.model.Classes;

import java.util.List;

public class ClassesAdapter extends BaseAdapter {
    private Context context;
    List<Classes> list;

    public ClassesAdapter(Context context, List<Classes> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_classes_item, null);
        }

        TextView tvId = view.findViewById(R.id.tvClassId);
        TextView tvName = view.findViewById(R.id.tvName);

        Classes cls = list.get(i);
        tvId.setText("" + cls.getId());
        tvName.setText("" + cls.getName());
        return view;
    }
}
