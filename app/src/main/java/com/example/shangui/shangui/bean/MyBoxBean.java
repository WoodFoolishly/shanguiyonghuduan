package com.example.shangui.shangui.bean;

/**
 * Created by Administrator on 2018/3/26.
 */

public class MyBoxBean {

    private String id;
    private int status;//箱子的状态

    public static final int UNDISPOSED = 0;//无处理
    public static final int DELIVER = 1;//转交状态
    public static final int CONVEY = 2;//运送状态
    public static final int DELIVER_AND_CONVEY = 3;//转交和运送状态

    public MyBoxBean() {
    }

    public MyBoxBean(String id, int status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
