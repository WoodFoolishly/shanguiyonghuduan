package com.example.shangui.shangui.activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;
import com.example.shangui.shangui.bean.PersonalBean;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.personal_head)
    ImageView personalHead;
    @BindView(R.id.personal_name)
    TextView personalName;
    @BindView(R.id.personal_id_num)
    TextView personalIdNum;
    @BindView(R.id.personal_balance)
    TextView personalBalance;

    private PersonalBean personalBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbarTitle.setText("个人中心");
    }

    @Override
    protected void initData() {
        personalBean = new PersonalBean("小黄人","400410199009091234","33");
        String balance = "￥"+personalBean.getBalance();
        personalBalance.setText(balance);
        personalHead.setImageResource(R.drawable.head10);
        personalIdNum.setText(personalBean.getId());
        personalName.setText(personalBean.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.personal_pwd, R.id.personal_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_pwd:
                break;
            case R.id.personal_head:
                break;
        }
    }
}
