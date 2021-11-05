package com.lovehp30.mirrorcontrol;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class DataActViewAdapter extends FragmentStateAdapter {
    String client_code;
    String topics;
    List<SensorDataFormat> list;
    int cnt = 0;
    public DataActViewAdapter(String client_code, String topics, List<SensorDataFormat> list, @NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.client_code = client_code;
        this.topics = topics;
        this.list = list;
        cnt = list.get(0).getMapKeyList().length;
        matchData();
    }

    public void matchData(){


    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        String[][] data = new String[cnt][list.size()];
        String[] time = new String[list.size()];
        String[] label = list.get(0).getMapKeyList();

        for(int i=0;i<list.size();i++){
            for(int j=0;j<cnt;j++)
                data[j][i] = list.get(i).getVal(j);
            time[i] = list.get(i).getTime();
        }

        Log.e("DataAdapt",cnt+" "+list.size());

        //쪼개보자!
        if(position==0)
            return new DataSphereFragment().putID(client_code,topics);
        else if(position == 1)
            return  new DataGraphFragment().newInstance(time,data[1],label[1]);
//        for(int i=1;i<cnt-1;i++)
//            if(position==i) {
//                return new DataGraphFragment();
//            }
        else
            return new DataGraphFragment().newInstance(time,data[0],label[0]);
    }


    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
    }

    @Override
    public int getItemCount() {
        return cnt+1;
    }


}
