package com.wjl.springbootmybatis.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liujun
 * \* Date: 2019/6/21
 * \* Time: 9:41
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class miaoshaGoods {
    private int miaoshagoods_id;
    private int goods_id;
    private BigDecimal miaosha_price;
    private int miaosha_stock;
    private Date begin_time;

    public int getMiaoshagoods_id() {
        return miaoshagoods_id;
    }

    public void setMiaoshagoods_id(int miaoshagoods_id) {
        this.miaoshagoods_id = miaoshagoods_id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public BigDecimal getMiaosha_price() {
        return miaosha_price;
    }

    public void setMiaosha_price(BigDecimal miaosha_price) {
        this.miaosha_price = miaosha_price;
    }

    public int getMiaosha_stock() {
        return miaosha_stock;
    }

    public void setMiaosha_stock(int miaosha_stock) {
        this.miaosha_stock = miaosha_stock;
    }

    public Date getBegin_time() {
        return begin_time;
    }

    public void setBegin_time(Date begin_time) {
        this.begin_time = begin_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    private Date end_time;
    private Goods goods;
}