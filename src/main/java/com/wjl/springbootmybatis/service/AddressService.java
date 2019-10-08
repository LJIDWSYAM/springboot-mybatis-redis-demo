package com.wjl.springbootmybatis.service;

import com.wjl.springbootmybatis.Utils.Page;
import com.wjl.springbootmybatis.entity.Address;
import com.wjl.springbootmybatis.entity.Goods;
import com.wjl.springbootmybatis.entity.miaoshaGoods;

import java.util.List;

public interface AddressService {

    Address selectAddressInfoByAddress_id(String address_id);
    void insertAddress(Address address);
}
