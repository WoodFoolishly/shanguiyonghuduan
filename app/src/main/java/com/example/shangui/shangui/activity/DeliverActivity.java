package com.example.shangui.shangui.activity;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class DeliverActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.deliver_recipients)
    EditText deliverRecipients;
    @BindView(R.id.deliver_phone)
    EditText deliverPhone;
    @BindView(R.id.deliver_edit_advance)
    EditText deliverEditAdvance;
    @BindView(R.id.deliver_consume)
    TextView deliverConsume;
    @BindView(R.id.deliver_advance)
    TextView deliverAdvance;
    @BindView(R.id.deliver_message)
    EditText deliverMessage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_deliver;
    }

    @Override
    protected void initView() {
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        toolbarTitle.setText(R.string.text_deliver_info);
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

    @OnClick({R.id.deliver_friend, R.id.deliver_address_list, R.id.deliver_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.deliver_friend:
                break;
            case R.id.deliver_address_list:
                break;
            case R.id.deliver_btn:
                break;
        }
    }
}
