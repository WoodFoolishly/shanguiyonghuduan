package com.example.shangui.shangui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.adapter.RechargeRecordAdapter;
import com.example.shangui.shangui.base.BaseActivity;
import com.example.shangui.shangui.bean.RechargeRecordBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RechargeRecordActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recharge_record_recycler)
    RecyclerView rechargeRecordRecycler;

    private List<RechargeRecordBean> list;
    private RechargeRecordAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge_record;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbarTitle.setText("充值记录");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        RechargeRecordBean rechargeRecordBean = new RechargeRecordBean("1000","2018-03-30",true);
        list = new ArrayList<>();
        list.add(rechargeRecordBean);
        list.add(rechargeRecordBean);
        list.add(rechargeRecordBean);
        adapter = new RechargeRecordAdapter(list,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rechargeRecordRecycler.setAdapter(adapter);
        rechargeRecordRecycler.setLayoutManager(manager);
    }

}
