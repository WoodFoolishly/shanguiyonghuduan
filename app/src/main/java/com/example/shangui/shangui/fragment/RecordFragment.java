package com.example.shangui.shangui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.adapter.RecordAdapter;
import com.example.shangui.shangui.base.BaseFragment;
import com.example.shangui.shangui.bean.RecordBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RecordFragment extends BaseFragment {


    @BindView(R.id.record_recycler)
    RecyclerView recordRecycler;

    private List<RecordBean> list;
    private RecordAdapter adapter;

    public RecordFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RecordFragment newInstance() {
//        RecordFragment fragment = new RecordFragment();
        return new RecordFragment();
    }

    @Override
    protected void initData() {
        RecordBean recordBean = new RecordBean("8125AA1234","广东","广西","03.08 14:00","03.08 14:19");
        list.add(recordBean);
        list.add(recordBean);
        list.add(recordBean);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recordRecycler.setLayoutManager(manager);
        adapter = new RecordAdapter(getActivity(),list);
        recordRecycler.setAdapter(adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_record;
    }

}
