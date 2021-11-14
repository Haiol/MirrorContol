package com.lovehp30.mirrorcontrol.main.control;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.lovehp30.mirrorcontrol.R;
import com.lovehp30.mirrorcontrol.main.topics.TopicsFragment;

public class ControlFragment extends Fragment {
    String ip = "";

    private ControlViewModel mViewModel;

    public static ControlFragment setting(String ip) {
        ControlFragment fragment = new ControlFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.control_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ControlViewModel.class);
        // TODO: Use the ViewModel
    }

}