package com.wjl.springbootmybatis.service;

import com.wjl.springbootmybatis.entity.UserInfo;

/**
 * @author : liujun
 * @date : ${DATA}
 */
public interface UserService {
    boolean checkUserName(String user_account);
    void regist(UserInfo userInfo);
    UserInfo checkLogin(UserInfo userInfo);
}
