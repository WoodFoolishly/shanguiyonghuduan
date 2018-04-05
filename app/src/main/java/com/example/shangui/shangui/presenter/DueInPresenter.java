package com.example.shangui.shangui.presenter;

import com.example.shangui.shangui.contract.DueInContract;

/**
 * Created by Administrator on 2018/4/3.
 *
 */

public class DueInPresenter implements DueInContract.Presenter {

//    private Context context;
    private DueInContract.View view;

    public DueInPresenter(DueInContract.View view) {
        this.view = view;
//        this.context = (DueInActivity)view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void receive() {
        view.receiveSucceed();
    }

    @Override
    public void reject() {
        view.rejectSucceed();
    }
}
