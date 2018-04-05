package com.example.shangui.shangui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class ConveyActivity extends BaseActivity {

    @BindView(R.id.convey_toolbar)
    Toolbar conveyToolbar;
    @BindView(R.id.convey_title)
    TextView conveyTitle;
    @BindView(R.id.convey_destination_num)
    EditText conveyDestinationNum;
    @BindView(R.id.convey_destination_name)
    EditText conveyDestinationName;
    @BindView(R.id.convey_freight)
    TextView conveyFreight;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_convey;
    }

    @Override
    protected void initView() {
        setSupportActionBar(conveyToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
        }
        conveyTitle.setText(getIntent().getStringExtra("id"));
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

    @OnClick({R.id.convey_location, R.id.convey_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.convey_location:
                Intent intent = new Intent(ConveyActivity.this,ConveyAddressActivity.class);
                startActivity(intent);
                break;
            case R.id.convey_btn:
                break;
        }
    }
}
