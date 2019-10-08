package com.wjl.springbootmybatis.entity;

import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liujun
 * \* Date: 2019/6/24
 * \* Time: 19:55
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class OrderInfoVo {
    private int order_id;
    private String order_no;
    private int address_id;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    private int miaoshagoods_id;
    private String user_account;
    private Date create_time;
    private Date pay_time;
    private int buy_count;
    private String order_pay_no;

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
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

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public int getBuy_count() {
        return buy_count;
    }

    public void setBuy_count(int buy_count) {
        this.buy_count = buy_count;
    }

    public String getOrder_pay_no() {
        return order_pay_no;
    }

    public void setOrder_pay_no(String order_pay_no) {
        this.order_pay_no = order_pay_no;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public com.wjl.springbootmybatis.entity.miaoshaGoods getMiaoshaGoods() {
        return miaoshaGoods;
    }

    public void setMiaoshaGoods(com.wjl.springbootmybatis.entity.miaoshaGoods miaoshaGoods) {
        this.miaoshaGoods = miaoshaGoods;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    private Address address;
    private miaoshaGoods miaoshaGoods;
    private Goods goods;
}