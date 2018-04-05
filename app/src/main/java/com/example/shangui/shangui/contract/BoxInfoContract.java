package com.example.shangui.shangui.contract;

import com.example.shangui.shangui.base.BasePresenter;
import com.example.shangui.shangui.base.BaseView;

/**
 * Created by Administrator on 2018/3/27.
 */

public interface BoxInfoContract {

    interface View extends BaseView<Presenter>{
        void showLoading();//展示加载框
        void dismissLoading();//取消加载框展示
    }

    interface Presenter extends BasePresenter{

    }

}
