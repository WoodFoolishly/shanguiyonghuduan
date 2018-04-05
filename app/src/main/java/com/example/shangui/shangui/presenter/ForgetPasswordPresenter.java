package com.example.shangui.shangui.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.shangui.shangui.activity.ForgetPasswordActivity;
import com.example.shangui.shangui.activity.LoginActivity;
import com.example.shangui.shangui.contract.ForgetPasswordContract;
import com.example.shangui.shangui.utils.ToastUtils;

/**
 * Created by Administrator on 2018/4/3.
 *
 */

public class ForgetPasswordPresenter implements ForgetPasswordContract.Presenter {

    private Context context;
    private ForgetPasswordContract.View view;

    public ForgetPasswordPresenter(ForgetPasswordContract.View view) {
        this.view = view;
        this.context = (ForgetPasswordActivity)view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getCode() {
        ToastUtils.shortTime(context,"已发送");
    }

    @Override
    public void verification(String phone, String code) {
        if(phone.equals("")){
            view.checkNull("手机号码不能为空");
            return;
        }else if(code.equals("")){
            view.checkNull("验证码不能为空");
            return;
        }
        view.showLoad();
        view.next();
        view.hideLoad();
        view.verificationSucceed("验证成功");
    }

    @Override
    public void alter(String password, String rePassword) {
        if(password.equals("")){
            view.checkNull("密码不能为空");
            return;
        }else if(rePassword.equals("")){
            view.checkNull("确认密码不能为空");
            return;
        }else if(password.equals(rePassword)){
            view.checkNull("密码与确认密码不一致");
            return;
        }
        view.showLoad();
        view.hideLoad();
        view.alterSucceed("修改成功");
        context.startActivity(new Intent(context, LoginActivity.class));
        ((ForgetPasswordActivity)context).finish();
    }
}
