package com.lovehp30.mirrorcontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Test2ListAdapter extends BaseAdapter {

    ArrayList<ListViewItem> datas;
    Context context;

    Test2ListAdapter(ArrayList<ListViewItem> datas, Context context){
        this.datas = datas;
        this.context = context;

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =LayoutInflater.from(context);
        if(convertView == null)
            convertView=inflater.inflate(R.layout.custom_list_item,parent,false);
        ListViewItem data = datas.get(position);
       TextView view1 = convertView.findViewById(R.id.text_title);
        TextView view2 = convertView.findViewById(R.id.text_sub_title);

        view1.setText(data.title);
        view2.setText(data.subtitle);

        Toast.makeText(context,datas.get(position).toString(),Toast.LENGTH_SHORT);
        return convertView;
    }

}
