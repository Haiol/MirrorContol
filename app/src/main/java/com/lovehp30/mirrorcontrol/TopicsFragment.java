package com.lovehp30.mirrorcontrol;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toolbar;


import com.lovehp30.mirrorcontrol.data.DataActivity;
import com.lovehp30.mirrorcontrol.sqllite.MQDbOpenHelper;

import java.util.ArrayList;

public class TopicsFragment extends Fragment {

    private ListView listView;
    private TopicsViewModel mViewModel;
    private TopicsListAdapter adapter;
    String ip = "";
    ArrayList<ListViewItem> items;
    public static TopicsFragment setting(String ip) {
        TopicsFragment fragment = new TopicsFragment();
        Bundle args = new Bundle();
        args.putString("address", ip);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ip = getArguments().getString("address");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.topics_fragment, container, false);


        mViewModel = ViewModelProviders.of(this).get(TopicsViewModel.class);
        listView = v.findViewById(R.id.listView);
        mViewModel.setDatabaseHelper(v.getContext(), ip);
        items = mViewModel.getDBData();

        adapter = new TopicsListAdapter(items, getContext());
        listView.setAdapter(adapter);


        return v;
    }

    public void restart() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
    public void addData(ListViewItem item) {
        items.add(item);
        adapter.notifyDataSetChanged();
    }


}