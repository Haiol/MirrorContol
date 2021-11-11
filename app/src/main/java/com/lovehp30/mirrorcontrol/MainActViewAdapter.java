package com.lovehp30.mirrorcontrol;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.lovehp30.mirrorcontrol.topics.ListTopicItem;
import com.lovehp30.mirrorcontrol.topics.TopicsFragment;

public class MainActViewAdapter extends FragmentStateAdapter {
    public int mCount;
    TopicsFragment list;
    public MainActViewAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
//        if(position == 0)return MainActivity.isVerifySkyMoon?new ControlFragment():new ErrorFragment();
        if(position == 0)return new ControlFragment();
        else {
            list = new TopicsFragment().setting("lovehp12.duckdns.org");
            return MainActivity.isVerifySunLite?list:new ErrorFragment();

        }
    }


    @Override
    public boolean containsItem(long itemId) {
        return super.containsItem(itemId);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
    public void addListData(ListTopicItem item){
        list.addData(item);
    }


    public void refreshList(){
        list.restart();
    }


}
