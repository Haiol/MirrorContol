package com.lovehp30.mirrorcontrol;

import android.content.Intent;
import android.database.Cursor;
import android.hardware.Sensor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lovehp30.mirrorcontrol.DataAct.DataActivity;

import java.util.ArrayList;


public class ListMQFragment extends Fragment {

    ListView listView;
    Test2ListAdapter adapter;
    ArrayList<ListViewItem> datas;
    String ip="";
    MQDbOpenHelper databaseHelper;
    public static ListMQFragment newInstance(String ip) {
        ListMQFragment fragment = new ListMQFragment();
        Bundle args = new Bundle();
        args.putString("address", ip);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ip = getArguments().getString("address");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mq, container, false);
        String db_ip = ip.replaceAll("[.]","");
        Log.e("List_ip",db_ip);

        databaseHelper = new MQDbOpenHelper(v.getContext(),db_ip);
        Log.e("DBname",databaseHelper.DATABASE_NAME);
        databaseHelper.open();

        datas = getDBDatas();
        databaseHelper.close();
        Log.e("List_items",datas.toString());
        listView = v.findViewById(R.id.listView);
        adapter = new Test2ListAdapter(datas,v.getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem item = datas.get(position);
                Intent intent = new Intent(v.getContext(), DataActivity.class);
                intent.putExtra("Client_Code",item.title);
                intent.putExtra("Topics",item.subtitle);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment

        return v;
    }

    ArrayList<ListViewItem> getDBDatas(){
        ArrayList<ListViewItem> list = new ArrayList<>();
        Cursor cursor = databaseHelper.getAllColumns();
        Log.e("DB","Count = "+cursor.getCount());
        while (cursor.moveToNext()){
            list.add(new ListViewItem(
                    cursor.getInt(cursor.getColumnIndex("_id")),
                    cursor.getString(cursor.getColumnIndex("code")),
                    cursor.getString(cursor.getColumnIndex("topic"))
            ));
        }
        cursor.close();
        return list;
    }
}