package com.example.shangui.shangui.bean;

/**
 * Created by Administrator on 2018/3/30.
 */

public class RechargeRecordBean {

    private String money;
    private String time;
    private boolean status;

    public RechargeRecordBean() {
    }

    public RechargeRecordBean(String money, String time, boolean status) {
        this.money = money;
        this.time = time;
        this.status = status;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
