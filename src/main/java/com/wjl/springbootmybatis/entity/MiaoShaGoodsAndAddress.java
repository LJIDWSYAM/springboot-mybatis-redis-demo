package com.wjl.springbootmybatis.entity;

import java.util.List;

public class MiaoShaGoodsAndAddress {
    private MiaoshaGoods miaoshaGoods;
    private List<Address> address;

    public MiaoshaGoods getMiaoshaGoods() {
        return miaoshaGoods;
    }

    public void setMiaoshaGoods(MiaoshaGoods miaoshaGoods) {
        this.miaoshaGoods = miaoshaGoods;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }
}
