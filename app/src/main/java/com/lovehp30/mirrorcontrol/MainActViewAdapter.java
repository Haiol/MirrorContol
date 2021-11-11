package com.lovehp30.mirrorcontrol;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import static com.lovehp30.mirrorcontrol.DataAct.DataActivity.ip;

public class MainActViewAdapter extends FragmentStateAdapter {
    public int mCount;

    public MainActViewAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0)return new MirrorControlFragment();
        else return new ListMQFragment().newInstance("lovehp12.duckdns.org");
    }


    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
    }

    @Override
    public int getItemCount() {
        return 2;
    }


}
