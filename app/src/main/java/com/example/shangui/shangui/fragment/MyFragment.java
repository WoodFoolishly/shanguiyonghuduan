package com.example.shangui.shangui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.adapter.MyAdapter;
import com.example.shangui.shangui.base.BaseFragment;
import com.example.shangui.shangui.bean.MyBoxBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyFragment extends BaseFragment {

    @BindView(R.id.my_recycler)
    RecyclerView myRecycler;

    private List<MyBoxBean> list;
    private MyAdapter adapter;

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
//        MyFragment fragment = new MyFragment();
        return new MyFragment();
    }

    @Override
    protected void initData() {
        MyBoxBean myBoxBean = new MyBoxBean("8125AA1234",0);
        list.add(myBoxBean);
        myBoxBean = new MyBoxBean("8125AA1234",1);
        list.add(myBoxBean);
        myBoxBean = new MyBoxBean("8125AA1234",2);
        list.add(myBoxBean);
        myBoxBean = new MyBoxBean("8125AA1234",3);
        list.add(myBoxBean);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        myRecycler.setLayoutManager(manager);
        adapter = new MyAdapter(getActivity(),list);
        myRecycler.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }
}
