package com.example.shangui.shangui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.shangui.shangui.R;
import com.example.shangui.shangui.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
        finish();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

}
