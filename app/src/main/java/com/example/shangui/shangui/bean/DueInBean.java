package com.example.shangui.shangui.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/26.
 */

public class DueInBean implements Serializable {

    private String id;//箱子的编号
    private String name;//箱子发送人的姓名
    private String phone;//箱子发送人的电话
    private Double consume;//已消费金额
    private Double advance;//预付款金额
    private String location;//位置
    private String message;//留言
    private boolean status;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DueInBean() {
    }

    public DueInBean(String id, String name, String phone, Double consume, Double advance, String location, String message) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.consume = consume;
        this.advance = advance;
        this.location = location;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getConsume() {
        return consume;
    }

    public void setConsume(Double consume) {
        this.consume = consume;
    }

    public Double getAdvance() {
        return advance;
    }

    public void setAdvance(Double advance) {
        this.advance = advance;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
