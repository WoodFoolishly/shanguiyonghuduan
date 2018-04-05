package com.example.shangui.shangui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/28.
 */

public class BoxLocationBean {

    private String location;
    private String tip;
    private List<String> list;

    public BoxLocationBean() {
    }

    public BoxLocationBean(String location, String tip) {
        this.location = location;
        this.tip = tip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
