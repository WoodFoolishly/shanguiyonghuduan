package com.example.shangui.shangui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.baidu.mapapi.SDKInitializer;
import com.example.shangui.shangui.R;
import com.example.shangui.shangui.utils.KeyboardUtils;
import com.yanzhenjie.sofia.Sofia;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/21.
 *
 */

public abstract class BaseActivity<T> extends AppCompatActivity {

    public T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        setStatusBar(this);
        initView();
        initData();
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected void setStatusBar(Activity activity){
        Sofia.with(activity).statusBarBackground(getResources().getDrawable(R.drawable.status_bar));
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                View view = getCurrentFocus();
                KeyboardUtils.hideKeyboard(ev, view, BaseActivity.this);//调用方法判断是否需要隐藏键盘
                break;

            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
