package com.wjl.springbootmybatis.entity;

import java.io.Serializable;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liujun
 * \* Date: 2019/6/24
 * \* Time: 8:50
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Address implements Serializable {
    private int address_id;
    private String reciver_name;
    private String reciver_phone;
    private String reciver_address;
    private int post_no;
    private String user_account;

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getReciver_name() {
        return reciver_name;
    }

    public void setReciver_name(String reciver_name) {
        this.reciver_name = reciver_name;
    }

    public String getReciver_phone() {
        return reciver_phone;
    }

    public void setReciver_phone(String reciver_phone) {
        this.reciver_phone = reciver_phone;
    }

    public String getReciver_address() {
        return reciver_address;
    }

    public void setReciver_address(String reciver_address) {
        this.reciver_address = reciver_address;
    }

    public int getPost_no() {
        return post_no;
    }

    public void setPost_no(int post_no) {
        this.post_no = post_no;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }
}