package com.example.shangui.shangui.adapter;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21.
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private List<String> titles;//标题集合
   
    private List<Fragment> fragments;//三个页面的集合

    public MainFragmentAdapter(FragmentManager fm,List<String> titles,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
  
    }

}
