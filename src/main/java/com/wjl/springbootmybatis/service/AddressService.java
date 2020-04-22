package com.wjl.springbootmybatis.service;

import com.wjl.springbootmybatis.entity.Address;

public interface AddressService {

    Address selectAddressInfoByAddress_id(String address_id);
    void insertAddress(Address address);
}
