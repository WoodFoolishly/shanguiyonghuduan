package com.example.shangui.shangui.bean;

/**
 * Created by Administrator on 2018/3/27.
 */

public class TimeAndLocationBean {

    private String time;
    private String location;

    public TimeAndLocationBean() {
    }

    public TimeAndLocationBean(String time, String location) {
        this.time = time;
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
