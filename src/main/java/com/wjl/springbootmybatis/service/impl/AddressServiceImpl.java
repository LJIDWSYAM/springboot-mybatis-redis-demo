package com.wjl.springbootmybatis.service.impl;

import com.wjl.springbootmybatis.Utils.MD5Utils;
import com.wjl.springbootmybatis.dao.AddressDao;
import com.wjl.springbootmybatis.dao.UserDao;
import com.wjl.springbootmybatis.entity.Address;
import com.wjl.springbootmybatis.entity.UserInfo;
import com.wjl.springbootmybatis.service.AddressService;
import com.wjl.springbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liujun
 * \* Date: 2019/6/18
 * \* Time: 15:47
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    AddressDao addressDao;
    @Override
    public Address selectAddressInfoByAddress_id(String address_id) {
        Address address=addressDao.selectAddressInfoByAddress_id(address_id);
        return address;
    }

    @Override
    public void insertAddress(Address address) {
        addressDao.insertAddress(address);
    }
}