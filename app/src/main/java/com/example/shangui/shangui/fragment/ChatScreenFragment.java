package com.example.shangui.shangui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.activity.NewFriendsActivity;
import com.example.shangui.shangui.adapter.MainFragmentAdapter;
import com.example.shangui.shangui.base.BaseFragment;
import com.example.shangui.shangui.runtimepermissions.PermissionsManager;
import com.example.shangui.shangui.runtimepermissions.PermissionsResultAction;
import com.example.shangui.shangui.view.MainViewPager;
import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatScreenFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.return_im)
    ImageView returnIm;
    @BindView(R.id.addbuddy_im)
    ImageView addbuddyIm;
    @BindView(R.id.frag_tab)
    TabLayout fragTab;
    @BindView(R.id.frag_pager)
    MainViewPager fragPager;
    private List<String> titles;
    private List<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    protected void initData() {
        titles = new ArrayList<>();
        titles.add("好友");
        titles.add("陌生人");
        titles.add("黑名单");
        fragments = new ArrayList<>();
        fragments.add(new FriendsListFragment());
        fragments.add(new StrangerFragment());
        fragments.add(new BlackListFragment());
        fragPager.setOffscreenPageLimit(3);
        fragPager.setAdapter(new MainFragmentAdapter(getChildFragmentManager(), titles, fragments));
        fragTab.setupWithViewPager(fragPager);

    }

    @Override
    protected void initView() {
        addbuddyIm.setOnClickListener(this);
        returnIm.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_chat_screen;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addbuddy_im:
                Intent intent = new Intent(getActivity(), NewFriendsActivity.class);
                startActivity(intent);
                break;
        }

    }
}
