package com.wjl.springbootmybatis.entity;

import java.io.Serializable;

public class MiaoShaMessage implements Serializable {
    private String user_account;
    private String miaoshagoods_id;

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getMiaoshagoods_id() {
        return miaoshagoods_id;
    }

    public void setMiaoshagoods_id(String miaoshagoods_id) {
        this.miaoshagoods_id = miaoshagoods_id;
    }
}
