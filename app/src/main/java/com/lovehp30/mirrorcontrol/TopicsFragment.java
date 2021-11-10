package com.lovehp30.mirrorcontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toolbar;


import java.util.ArrayList;

public class TopicsFragment extends Fragment {

    private ListView listView;
    private TopicsViewModel mViewModel;
    private TopicsListAdapter adapter;
    Toolbar toolbar;
    String ip="";

    public static TopicsFragment newInstance(String ip) {
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
        View v =  inflater.inflate(R.layout.topics_fragment, container, false);


        mViewModel = ViewModelProviders.of(this).get(TopicsViewModel.class);
        listView = v.findViewById(R.id.listView);
        mViewModel.setDatabaseHelper(v.getContext(),ip);
        ArrayList<ListViewItem> items = mViewModel.getDBData();

        adapter = new TopicsListAdapter(items,getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        return v;
    }



}