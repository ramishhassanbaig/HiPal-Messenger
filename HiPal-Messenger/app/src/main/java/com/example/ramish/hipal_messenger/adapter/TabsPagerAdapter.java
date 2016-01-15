package com.example.ramish.hipal_messenger.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ramish.hipal_messenger.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ramish on 1/15/2016.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList=new ArrayList<>();

    public TabsPagerAdapter(FragmentManager manager){
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragmentToPager(Fragment fragment){
        fragmentList.add(fragment);
    }
}
