package com.example.shangui.shangui.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public class BoxInfoBean {

    private List<TimeAndLocationBean> TLlist;
    private String id;
    private String location;
    private int status;
    private String name;
    private String startName;
    private String endName;

    public BoxInfoBean() {
    }

    public BoxInfoBean(List<TimeAndLocationBean> TLlist, String id, String location, String name, int status) {
        this.TLlist = TLlist;
        this.id = id;
        this.location = location;
        this.status = status;
        this.name = name;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public List<TimeAndLocationBean> getTLlist() {
        return TLlist;
    }

    public void setTLlist(List<TimeAndLocationBean> TLlist) {
        this.TLlist = TLlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
