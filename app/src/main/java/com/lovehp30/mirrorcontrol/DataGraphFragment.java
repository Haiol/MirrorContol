package com.lovehp30.mirrorcontrol;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DataGraphFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DataGraphFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String title;
    private String[] time;
    private String[] data;

    public DataGraphFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param time Parameter 1.
     * @param data Parameter 2.
     * @return A new instance of fragment DataGraphFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DataGraphFragment newInstance(String[] time, String[] data,String title) {
        DataGraphFragment fragment = new DataGraphFragment();
        Bundle args = new Bundle();
        args.putStringArray(ARG_PARAM1, time);
        args.putStringArray(ARG_PARAM2, data);
        args.putString(ARG_PARAM3, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            time = getArguments().getStringArray(ARG_PARAM1);
            data = getArguments().getStringArray(ARG_PARAM2);
            title = getArguments().getString(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_data_graph, container, false);
        // Inflate the layout for this fragment

        LineChart chart = v.findViewById(R.id.chart);
        chart.invalidate();//초기화
        chart.clear();
        //data
        


        //data
        ArrayList<Entry> values = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            float val = (float) (Math.random() * 10);
            values.add(new Entry(i, val));
        }//Test data
        LineDataSet lineDataset = new LineDataSet(values, title);
        lineDataset.setColor(ContextCompat.getColor(getContext(), R.color.primary)); //LineChart에서 Line Color 설정
        lineDataset.setCircleColor(ContextCompat.getColor(getContext(), R.color.primary)); // LineChart에서 Line Circle Color 설정
        lineDataset.setCircleColorHole(ContextCompat.getColor(getContext(), R.color.primary)); // LineChart에서 Line Hole Circle Color 설정

        LineData lineData = new LineData();
        lineData.addDataSet(lineDataset);

        lineData.setValueTextColor(R.color.buttonColor);
        lineData.setValueTextSize(9);

        // x축 설정
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
//        xAxis.setValueFormatter(new Chart);
        // y출 설정
        YAxis yAxisLeft = chart.getAxisLeft();

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setDrawLabels(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawGridLines(false);
        //y축의 활성화 제거
//
////        lineChart.setVisibleXRangeMinimum(60 * 60 * 24 * 1000 * 5); //라인차트에서 최대로 보여질 X축의 데이터 설정
////        lineChart.setDescription(null); //차트에서 Description 설정 저는 따로 안했습니다.
//
//
//        Legend legend = lineChart.getLegend(); //레전드 설정 (차트 밑에 색과 라벨을 나타내는 설정)
//        legend.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);//하단 왼쪽에 설정
//        legend.setTextColor(ContextCompat.getColor(getContext(), R.color.textColor)); // 레전드 컬러 설정


        chart.setData(lineData);

//        LineDataSet set1;
//        set1 = new LineDataSet(values, "DataSet 1");
//
//        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(set1); // add the data sets
//
//        // create a data object with the data sets
//        LineData data = new LineData(dataSets);
//
//        // black lines and points
//        set1.setColor(Color.BLACK);
//        set1.setCircleColor(Color.BLACK);
//
//        // set data
//        chart.setData(data);
        return v;
    }
}