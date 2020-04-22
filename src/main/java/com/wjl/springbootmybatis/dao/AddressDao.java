package com.wjl.springbootmybatis.dao;


import com.wjl.springbootmybatis.entity.Address;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AddressDao {

    Address selectAddressInfoByAddress_id(String address_id);
    void insertAddress(Address address);
}
