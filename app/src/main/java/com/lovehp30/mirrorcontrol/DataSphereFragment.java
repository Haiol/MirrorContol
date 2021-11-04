package com.lovehp30.mirrorcontrol;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextPaint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicgoop.tagsphere.TagSphereView;
import com.magicgoop.tagsphere.item.TextTagItem;

import java.util.ArrayList;
import java.util.List;


public class DataSphereFragment extends Fragment {
    TagSphereView tagSphereView;
    List<SensorDataFormat> list;
    RequestData requestData;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String code;
    private String topic;

    public DataSphereFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DataSphereFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataSphereFragment putID(String param1, String param2) {
        DataSphereFragment fragment = new DataSphereFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            code = getArguments().getString(ARG_PARAM1);
            topic = getArguments().getString(ARG_PARAM2);
        }
        requestData = new RequestData(code,topic,getContext());
        new Thread(new Runnable() {
            @Override
            public void run() {
                requestData.getJsonNowData();
            }
        }).start();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_data_sphere, container, false);
        tagSphereView = v.findViewById(R.id.tagView);
        TextPaint paint = new TextPaint();
        paint.setAntiAlias(true);
        paint.setTextSize(40f);
        paint.setColor(Color.BLACK);
        List<TextTagItem> item = new ArrayList<>();
        for(int i=0;i<50;i++){
            item.add(new TextTagItem("1"));
        }
        tagSphereView.setTextPaint(paint);
        tagSphereView.addTagList(item);
        tagSphereView.setRadius(2.5f);

        // Inflate the layout for this fragment
        return v;
    }
}