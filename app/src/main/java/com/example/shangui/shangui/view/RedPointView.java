package com.example.shangui.shangui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.shangui.shangui.R;

/**
 * Created by Administrator on 2018/4/3.
 */

public class RedPointView extends View {

    private Paint paint;

    public RedPointView(Context context) {
        super(context);
        init();
    }

    public RedPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RedPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.colorPointRed));
        paint.setAntiAlias(true);//抗锯齿
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int radius = getWidth()-getLeft()-getRight();
        canvas.drawCircle(radius,radius,radius,paint);
    }
}
