package com.lovehp30.mirrorcontrol.main.control;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.slider.Slider;
import com.lovehp30.mirrorcontrol.R;
import com.lovehp30.mirrorcontrol.main.topics.TopicsFragment;

public class ControlFragment extends Fragment {
    String ip = "";
    String key = "";


    private ControlViewModel mViewModel;

    public static ControlFragment setting(String ip,String key) {
        ControlFragment fragment = new ControlFragment();
        Bundle args = new Bundle();
        args.putString("address", ip);
        args.putString("key", key);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ip = getArguments().getString("address");
            key = getArguments().getString("key");

        }
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.control_fragment, container, false);
        Button monitorOn = v.findViewById(R.id.monitor_on);
        monitorOn.setOnClickListener(vi->{
            ControlVolleyRequest.onlyGetRequest(v.getContext(),ip,key,"monitor/on");

        });
        Button monitorOff = v.findViewById(R.id.monitor_off);
        monitorOff.setOnClickListener(vi->{
            ControlVolleyRequest.onlyGetRequest(v.getContext(),ip,key,"monitor/off");
        });

        Slider slider = v.findViewById(R.id.brightness);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ControlViewModel.class);
        // TODO: Use the ViewModel
    }

}