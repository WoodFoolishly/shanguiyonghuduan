package com.example.shangui.shangui.contract;

import com.example.shangui.shangui.base.BasePresenter;
import com.example.shangui.shangui.base.BaseView;

/**
 * Created by Administrator on 2018/4/3.
 *
 */

public interface RegisterContract {

    interface Presenter extends BasePresenter{
        void register(String name,String id,String phone);
        void setPassword(String code,String password,String rePassword);
    }

    interface View extends BaseView<Presenter>{
        void verificationSucceed(String string);
        void verificationFailure();
        void registerSucceed(String string);
        void registerFailure();
        void next();
        void back();
        void checkNull(String string);
    }

}
