package com.example.shangui.shangui.activity;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;
import com.example.shangui.shangui.bean.BoxLocationBean;

import butterknife.BindView;
import butterknife.OnClick;

public class BoxLocationActivity extends BaseActivity {

    @BindView(R.id.box_location_toolbar)
    Toolbar boxLocationToolbar;
    @BindView(R.id.box_location_title)
    TextView boxLocationTitle;
    @BindView(R.id.box_location_text)
    TextView boxLocationText;
    @BindView(R.id.box_location_tip)
    TextView boxLocationTip;

    private BoxLocationBean locationBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_box_location;
    }

    @Override
    protected void initView() {
        setSupportActionBar(boxLocationToolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_arrow_left);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getIntent().getStringExtra("id"));
        }
        boxLocationTitle.setText(getIntent().getStringExtra("location"));
    }

    @Override
    protected void initData() {
        locationBean = new BoxLocationBean("苏州市规划1路和规划12路交叉处","万达广场进门左边右转，右边左转麦当劳KFC交叉处");
        String location = "地址："+locationBean.getLocation();
        String tip = "（"+locationBean.getTip()+"）";
        boxLocationText.setText(location);
        boxLocationTip.setText(tip);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.box_location_btn)
    public void onViewClicked() {
        Intent intent = new Intent(BoxLocationActivity.this,BoxLocationMapActivity.class);
        startActivity(intent);
    }
}
