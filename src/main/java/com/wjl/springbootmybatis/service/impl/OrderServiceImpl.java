package com.wjl.springbootmybatis.service.impl;

import com.wjl.springbootmybatis.dao.OrderDao;
import com.wjl.springbootmybatis.entity.Address;
import com.wjl.springbootmybatis.entity.OrderInfo;
import com.wjl.springbootmybatis.entity.OrderInfoVo;
import com.wjl.springbootmybatis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liujun
 * \* Date: 2019/6/24
 * \* Time: 8:48
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;
    @Override
    public List<Address> selectAddressByUserAccount(String user_account) {
        List<Address> address=orderDao.selectAddressByUserAccount(user_account);
        return address;
    }

    @Transactional
    @Override
    public OrderInfo insertOnderInfoANDreduceMiaoshaGoodsNum(String miaoshagoodsId, String user_account,String address_id) {
        reduceMiaoshaGoodsNum(miaoshagoodsId);
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setUser_account(user_account);
        orderInfo.setCreate_time(new Date());
        orderInfo.setAddress_id(Integer.valueOf(address_id));
        orderInfo.setOrder_no(new Date().getTime()+miaoshagoodsId);
        orderInfo.setMiaoshagoods_id(Integer.valueOf(miaoshagoodsId));
        orderInfo.setBuy_count(1);
        insertOrderInfo( orderInfo);
        return orderInfo;
    }

    @Override
    public OrderInfoVo selectAllInfoByOrderNo(String order_no) {
        OrderInfoVo orderInfoVo=orderDao.selectAllInfoByOrderNo(order_no);
        return orderInfoVo;
    }

    @Override
    public void reduceMiaoshaGoodsNum(String miaoshagoodsId) {
        orderDao.reduceMiaoshaGoodsNum(miaoshagoodsId);
    }

    @Override
    public void insertOrderInfo(OrderInfo orderInfo) {
        orderDao.insertOrderInfo(orderInfo);
    }

}