package com.example.shangui.shangui.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.shangui.shangui.activity.LoginActivity;
import com.example.shangui.shangui.activity.MainActivity;
import com.example.shangui.shangui.contract.LoginContract;

/**
 * Created by Administrator on 2018/4/2.
 *
 */

public class LoginPresenter implements LoginContract.Presenter {

    private Context context;
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view) {
        this.context = (LoginActivity)view;
        this.view = view;
        view.setPresenter(this);//绑定
    }

    @Override
    public void start() {

    }

    //登录操作
    @Override
    public void login(String name, String password) {
        view.showLoad();
        view.hideLoad();
        view.showSucceed("登录成功");
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        ((LoginActivity)context).finish();
    }
}
