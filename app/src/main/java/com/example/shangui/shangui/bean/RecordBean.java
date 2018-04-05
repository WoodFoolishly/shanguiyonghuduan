package com.example.shangui.shangui.bean;

/**
 * Created by Administrator on 2018/3/26.
 */

public class RecordBean {

    private String id;
    private String startLocation;
    private String endLocation;
    private String startTime;
    private String endTime;

    public RecordBean() {
    }

    public RecordBean(String id, String startLocation, String endLocation, String startTime, String endTime) {
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
