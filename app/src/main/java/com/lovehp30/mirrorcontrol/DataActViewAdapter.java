package com.lovehp30.mirrorcontrol;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class DataActViewAdapter extends FragmentStateAdapter {
    String client_code;
    String topics;
    public DataActViewAdapter(String client_code, String topics, @NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.client_code = client_code;
        this.topics = topics;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0)
            return new DataSphereFragment().putID(client_code,topics);
        else
            return new DataGraphFragment();
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
