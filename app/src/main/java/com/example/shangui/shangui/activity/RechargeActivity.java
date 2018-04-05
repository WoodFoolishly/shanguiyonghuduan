package com.example.shangui.shangui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recharge_edit_money)
    EditText rechargeEditMoney;
    @BindView(R.id.recharge_alipay)
    RadioButton rechargeAlipay;
    @BindView(R.id.recharge_wechat)
    RadioButton rechargeWechat;

    private int payStatus;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbarTitle.setText("充值");
    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick({R.id.recharge_record, R.id.recharge_alipay, R.id.recharge_wechat, R.id.recharger_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.recharge_alipay:
                rechargeWechat.setChecked(false);
                payStatus = 0;
                break;
            case R.id.recharge_wechat:
                rechargeAlipay.setChecked(false);
                payStatus = 1;
                break;
            case R.id.recharger_btn:
                Toast.makeText(getApplicationContext(), payStatus == 1 ? "微信充值成功" + payStatus : "支付宝充值成功" + payStatus, Toast.LENGTH_SHORT).show();
                break;
            case R.id.recharge_record:
                Intent intent = new Intent(RechargeActivity.this, RechargeRecordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
