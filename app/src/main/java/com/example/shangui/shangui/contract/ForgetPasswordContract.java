package com.example.shangui.shangui.contract;

import com.example.shangui.shangui.base.BasePresenter;
import com.example.shangui.shangui.base.BaseView;

/**
 * Created by Administrator on 2018/4/3.
 *
 */

public interface ForgetPasswordContract {

    interface Presenter extends BasePresenter{
        void getCode();
        void verification(String phone,String code);
        void alter(String password,String rePassword);
    }

    interface View extends BaseView<Presenter>{
        void verificationSucceed(String string);
        void verificationFailure();
        void alterSucceed(String string);
        void alterFailure();
        void next();
        void back();
        void checkNull(String string);
    }

}
