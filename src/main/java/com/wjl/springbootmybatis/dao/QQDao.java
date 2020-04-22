package com.wjl.springbootmybatis.dao;

import com.wjl.springbootmybatis.entity.QQUserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface QQDao {
     void insertintoQquserInfo(QQUserInfo qqUserInfo);
}
