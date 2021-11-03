package com.lovehp30.mirrorcontrol;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class Test2Fragment extends Fragment {

    ListView listView;
    Test2ListAdapter adapter;
    ArrayList<ListViewItem> datas;

    public static Test2Fragment newInstance(String param1, String param2) {
        Test2Fragment fragment = new Test2Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        datas = new ArrayList<ListViewItem>();
        datas.add(new ListViewItem("TestWemos01","Sensor"));
        datas.add(new ListViewItem("Test01","Test01"));
        datas.add(new ListViewItem("Test02","Test02"));
        datas.add(new ListViewItem("Test03","Test03"));
        datas.add(new ListViewItem("Test04","Test04"));
        datas.add(new ListViewItem("Test05","Test05"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_test2, container, false);

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
}