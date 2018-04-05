package com.example.shangui.shangui.contract;

import com.example.shangui.shangui.base.BasePresenter;
import com.example.shangui.shangui.base.BaseView;

/**
 * Created by Administrator on 2018/4/2.
 *
 */

public interface LoginContract {

    interface Presenter extends BasePresenter{
        void login(String name,String password);
    }

    interface View extends BaseView<Presenter>{
        void showSucceed(String string);
        void showFailure();
    }

}
