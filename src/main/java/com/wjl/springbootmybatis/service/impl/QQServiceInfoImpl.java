package com.wjl.springbootmybatis.service.impl;

import com.wjl.springbootmybatis.dao.QQDao;
import com.wjl.springbootmybatis.entity.QQUserInfo;
import com.wjl.springbootmybatis.service.QQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QQServiceInfoImpl implements QQService {
    @Autowired
    QQDao qqDao;
    @Override
    public void insertintoQquserInfo(QQUserInfo qqUserInfo) {
        qqDao.insertintoQquserInfo(qqUserInfo);
    }

    @Override
    public QQUserInfo selectQuserInfoByOpenid(String openid) {
        QQUserInfo qqUserInfo=qqDao.selectQuserInfoByOpenid(openid);
        return qqUserInfo;
    }
}
