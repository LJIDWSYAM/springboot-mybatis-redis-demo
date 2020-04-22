package com.wjl.springbootmybatis.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liujun
 * \* Date: 2019/6/24
 * \* Time: 16:50
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Order implements Serializable {
    private String order_no;
    private int miaoshagoods_id;
    private String user_account;
    private Date create_time;
    private int buy_count;
    private int state;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getMiaoshagoods_id() {
        return miaoshagoods_id;
    }

    public void setMiaoshagoods_id(int miaoshagoods_id) {
        this.miaoshagoods_id = miaoshagoods_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public int getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(int buy_count) {
        this.buy_count = buy_count;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_no='" + order_no + '\'' +
                ", miaoshagoods_id=" + miaoshagoods_id +
                ", user_account='" + user_account + '\'' +
                ", create_time=" + create_time +
                ", buy_count=" + buy_count +
                ", state=" + state +
                '}';
    }
}