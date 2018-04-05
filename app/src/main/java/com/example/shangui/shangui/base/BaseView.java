package com.example.shangui.shangui.base;

/**
 * Created by Administrator on 2018/3/27.
 *
 */

public interface BaseView<T> {

    void setPresenter(T presenter);
    void showLoad();
    void hideLoad();

}
