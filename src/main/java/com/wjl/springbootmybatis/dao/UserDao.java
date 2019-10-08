package com.wjl.springbootmybatis.dao;

import com.wjl.springbootmybatis.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.rmi.registry.Registry;

/**
 * @author : liujun
 * @date : ${DATA}
 */
@Repository
@Mapper
public interface UserDao {
     UserInfo selectUserinfoByUserNname(String user_account);
     void regist(UserInfo userInfo);
     UserInfo selectUserinfoByUserNameAndPassword(UserInfo userInfo);
}
