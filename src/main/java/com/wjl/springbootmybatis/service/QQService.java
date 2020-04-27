package com.wjl.springbootmybatis.service;

import com.wjl.springbootmybatis.entity.QQUserInfo;

public interface QQService {
    void insertintoQquserInfo(QQUserInfo qqUserInfo);
    QQUserInfo selectQuserInfoByOpenid(String Openid);
}
