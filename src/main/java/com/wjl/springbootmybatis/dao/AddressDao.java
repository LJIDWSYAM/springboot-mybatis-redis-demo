package com.wjl.springbootmybatis.dao;


import com.wjl.springbootmybatis.Utils.Page;
import com.wjl.springbootmybatis.entity.Address;
import com.wjl.springbootmybatis.entity.Goods;
import com.wjl.springbootmybatis.entity.miaoshaGoods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AddressDao {

    Address selectAddressInfoByAddress_id(String address_id);
    void insertAddress(Address address);
}
