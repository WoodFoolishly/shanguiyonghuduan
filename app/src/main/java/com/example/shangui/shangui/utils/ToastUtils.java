package com.example.shangui.shangui.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/4/3.
 *
 */

public class ToastUtils {

    public static void shortTime(Context context,String string){
        Toast.makeText(context,string,Toast.LENGTH_SHORT).show();
    }

    public static void longTime(Context context,String string){
        Toast.makeText(context,string,Toast.LENGTH_LONG).show();
    }

}
