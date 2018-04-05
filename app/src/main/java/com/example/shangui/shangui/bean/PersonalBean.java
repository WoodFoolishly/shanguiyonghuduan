package com.example.shangui.shangui.bean;

/**
 * Created by Administrator on 2018/4/2.
 */

public class PersonalBean {

    private String name;
    private String id;
    private String balance;
    private String head;

    public PersonalBean() {
    }

    public PersonalBean(String name, String id, String balance){
        this.name = name;
        this.id = id;
        this.balance = balance;
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

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
