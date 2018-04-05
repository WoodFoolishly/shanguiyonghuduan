package com.example.shangui.shangui.contract;

import com.example.shangui.shangui.base.BasePresenter;
import com.example.shangui.shangui.base.BaseView;

/**
 * Created by Administrator on 2018/4/3.
 *
 */

public interface DueInContract {

    interface Presenter extends BasePresenter{
        void receive();
        void reject();
    }

    interface View extends BaseView<Presenter>{
        void receiveSucceed();
        void receiveFailure();
        void rejectSucceed();
        void rejectFailure();
    }

}
