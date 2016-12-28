package com.example.liuhaifeng.ease_demo.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.liuhaifeng.ease_demo.R;
import com.example.liuhaifeng.ease_demo.user;

import java.util.List;

/**
 * Created by liuhaifeng on 2016/12/19.
 */

public class NoticeAdapter extends BaseAdapter {
    private List<user> list;
    Context context;
    LayoutInflater layoutInflater;

    public NoticeAdapter(List<user> list, Context context) {
        this.list = list;
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view =layoutInflater.inflate(R.layout.notice,null);
        TextView name_notice = (TextView) view.findViewById(R.id.name_notice);
        name_notice.setText(list.get(i).getName() + "请求加为好友");
        return view;
    }
}


