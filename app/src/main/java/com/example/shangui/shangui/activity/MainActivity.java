package com.example.shangui.shangui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.shangui.shangui.R;
import com.example.shangui.shangui.adapter.MainFragmentAdapter;
import com.example.shangui.shangui.base.BaseActivity;
import com.example.shangui.shangui.fragment.BoxFragment;
import com.example.shangui.shangui.fragment.ChatScreenFragment;
import com.example.shangui.shangui.fragment.MainLocationFragment;
import com.example.shangui.shangui.view.MainViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {


    @BindView(R.id.main_tab)
    TabLayout mainTab;
    @BindView(R.id.main_pager)
    MainViewPager mainPager;
    @BindView(R.id.main_bottom)
    BottomNavigationBar mainBottom;
    private List<String> titles;
    private List<Fragment> fragments;
    private MainFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        setSupportActionBar(toolbar);
//        if(getSupportActionBar()!=null){
//            getSupportActionBar().setDisplayShowTitleEnabled(false);
//        }
//        toolbarTitle.setText(R.string.title_box);
        mainBottom.setTabSelectedListener(this);
    }

    @Override
    protected void initData() {
        fragments = getFragments();
        titles = new ArrayList<>();
        adapter = new MainFragmentAdapter(getSupportFragmentManager(), titles, fragments);
        titles.add("主页");
        titles.add("箱子");
        titles.add("聊天");
        fragments = new ArrayList<>();
        fragments.add(new Fragment());
        fragments.add(new BoxFragment());
        fragments.add(new ChatScreenFragment());
        mainPager.setOffscreenPageLimit(3);
        adapter = new MainFragmentAdapter(getSupportFragmentManager(), titles, fragments);
        mainPager.setAdapter(adapter);
        mainTab.setupWithViewPager(mainPager);
        mainPager.addOnPageChangeListener(this);
        mainBottom.setTabSelectedListener(this);
        mainBottom.addItem(new BottomNavigationItem(R.drawable.index_1, R.string.text_main).setInactiveIconResource(R.drawable.index).setActiveColor(R.color.colorTitleRightBlue).setInActiveColor(R.color.colorEditGray))
                .addItem(new BottomNavigationItem(R.drawable.box_1, R.string.title_box).setInactiveIconResource(R.drawable.box).setActiveColor(R.color.colorTitleRightBlue).setInActiveColor(R.color.colorEditGray))
                .addItem(new BottomNavigationItem(R.drawable.chat_1, R.string.text_chat).setInactiveIconResource(R.drawable.chat).setActiveColor(R.color.colorTitleRightBlue).setInActiveColor(R.color.colorEditGray))
                .setFirstSelectedPosition(0)
                .initialise();
    }

    private List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(MainLocationFragment.newInstance());
        list.add(BoxFragment.newInstance());
        list.add(new Fragment());
        return list;
    }

    @Override
    public void onTabSelected(int position) {
        mainPager.setCurrentItem(position);
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        if (fragments.get(position).isAdded()) {
//            fragmentTransaction.show(fragments.get(position));
//        } else {
//            fragmentTransaction.add(R.id.main_content, fragments.get(position));
//        }
//        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onTabUnselected(int position) {
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.hide(fragments.get(position));
//        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mainBottom.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
