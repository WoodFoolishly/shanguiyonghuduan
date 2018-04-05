package com.example.shangui.shangui.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.shangui.shangui.activity.LoginActivity;
import com.example.shangui.shangui.activity.RegisterActivity;
import com.example.shangui.shangui.contract.RegisterContract;

/**
 * Created by Administrator on 2018/4/3.
 *
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private Context context;
    private RegisterContract.View view;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        this.context = (RegisterActivity)view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void register(String name, String id, String phone) {
        if(name.equals("")){
            view.checkNull("姓名不能为空");
            return;
        }else if(id.equals("")){
            view.checkNull("身份证号码不能为空");
            return;
        }else if(phone.equals("")){
            view.checkNull("手机号码不能为空");
            return;
        }
        view.showLoad();
        view.verificationSucceed("验证成功");
        view.next();
        view.hideLoad();
    }

    @Override
    public void setPassword(String code, String password, String rePassword) {
        if(code.equals("")){
            view.checkNull("验证码不能为空");
            return;
        }else if(password.equals("")){
            view.checkNull("密码不能为空");
            return;
        }else if(rePassword.equals("")){
            view.checkNull("确认密码不能为空");
            return;
        }else if(!password.equals(rePassword)){
            view.checkNull("密码与确认密码不一致");
            return;
        }
        view.showLoad();
        view.verificationSucceed("注册成功");
        view.hideLoad();
        context.startActivity(new Intent(context, LoginActivity.class));
        ((RegisterActivity) context).finish();
    }
}
