package com.example.shangui.shangui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class MenuActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbarTitle.setText("更多");
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.menu_personal, R.id.menu_upgrade, R.id.menu_recharge, R.id.menu_help, R.id.menu_btn})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.menu_personal://个人中心
                intent = new Intent(MenuActivity.this, PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_upgrade:
//                intent = new Intent(MenuActivity.this, RechargeActivity.class);
//                startActivity(intent);
                break;
            case R.id.menu_recharge://账户充值
                intent = new Intent(MenuActivity.this, RechargeActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_help://使用帮助
                intent = new Intent(MenuActivity.this, HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_btn:
                break;
        }
    }
}
