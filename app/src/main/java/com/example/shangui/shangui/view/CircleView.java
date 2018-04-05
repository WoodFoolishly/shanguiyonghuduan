package com.example.shangui.shangui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.shangui.shangui.R;

/**
 * Created by Administrator on 2018/3/27.
 */

public class CircleView extends View {

    int insideColor = getResources().getColor(R.color.colorTitleRightBlue);
    int outsideColor = getResources().getColor(R.color.colorThinBlue);
    private Paint insidePaint;
    private Paint outsidePaint;


    public CircleView(Context context,int inside,int outside) {
        super(context);
        insideColor = getResources().getColor(inside);
        outsideColor = getResources().getColor(outside);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        insidePaint = new Paint();
        insidePaint.setColor(insideColor);
        insidePaint.setAntiAlias(true);//抗锯齿
        insidePaint.setStyle(Paint.Style.FILL);
        outsidePaint = new Paint();
        outsidePaint.setColor(outsideColor);
        outsidePaint.setAntiAlias(true);//抗锯齿
        outsidePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width,height)/2;
        Log.e("onDraw",paddingLeft+"+"+paddingTop+"+"+paddingRight+"+"+paddingBottom+"+"+width+"+"+height);
        canvas.drawCircle(radius,radius,radius,outsidePaint);
        canvas.drawCircle(radius,radius,radius*4/5,insidePaint);
    }

}
