package com.example.shangui.shangui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2018/3/21.
 */

public class MainViewPager extends ViewPager {

    private boolean isPagingEnabled = false;//禁止滑动

    public MainViewPager(Context context) {
        super(context);
    }

    public MainViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return this.isPagingEnabled && super.onTouchEvent(ev);
    }

//    public void setPagingEnabled(boolean pagingEnabled) {
//        this.isPagingEnabled = pagingEnabled;
//    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}
