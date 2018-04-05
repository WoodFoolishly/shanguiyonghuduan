package com.example.shangui.shangui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.example.shangui.shangui.R;

/**
 * Created by Administrator on 2018/4/2.
 */

public class SearchPopupWindow extends PopupWindow {

    private Context context;
    private View view;

    public SearchPopupWindow(Context context){
        super(context);
        this.context = context;
        init();
    }

    private void init(){
        view = LayoutInflater.from(context).inflate(R.layout.popup_address,null);
        setContentView(view);
    }

}
