package com.lovehp30.mirrorcontrol.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataActViewAdapter extends FragmentStateAdapter {
    private String client_code;
    private String topics;
    private String ip;
    private List<SensorDataFormat> list;
    int cnt = 0;
    public DataActViewAdapter(String ip,String client_code, String topics, List<SensorDataFormat> list, @NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.ip = ip;
        this.client_code = client_code;
        this.topics = topics;
        this.list = list;
        cnt = list.get(0).getMapKeyList().length;
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        ArrayList<Fragment> fragments = createFrameList();
        if(position<cnt)
            return fragments.get(position);
        else
            return fragments.get(cnt);
    }
    ArrayList<Fragment> createFrameList(){
        ArrayList<Fragment> frag = new ArrayList<>();
        String[][] data = new String[cnt][list.size()];
        String[] time = new String[list.size()];
        String[] label = list.get(0).getMapKeyList();
        Log.e("DataAdapt",cnt+" "+list.size());
        for(int i=0;i<list.size();i++){
            for(int j=0;j<cnt;j++)
                data[j][i] = list.get(i).getVal(j);
            time[i] = list.get(i).getTime();
        }
        frag.add(new DataSphereFragment().putID(ip,client_code,topics));
        for(int i=0;i<cnt;i++)
            frag.add(new DataGraphFragment().newInstance(time,data[i],label[i]));
        return frag;
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
