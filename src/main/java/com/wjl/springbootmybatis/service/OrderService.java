package com.wjl.springbootmybatis.service;

import com.wjl.springbootmybatis.dao.OrderDao;
import com.wjl.springbootmybatis.entity.Address;
import com.wjl.springbootmybatis.entity.OrderInfo;
import com.wjl.springbootmybatis.entity.OrderInfoVo;

import java.util.List;

/**
 * @author : liujun
 * @date : ${DATA}
 */
public interface OrderService {
    List<Address> selectAddressByUserAccount(String user_account);
    void reduceMiaoshaGoodsNum(String miaoshagoodsId);
    void insertOrderInfo(OrderInfo orderInfo);
    OrderInfo insertOnderInfoANDreduceMiaoshaGoodsNum(String miaoshagoodsId,String user_account,String address_id);
    OrderInfoVo selectAllInfoByOrderNo(String order_no);
    void updateOrder(OrderInfoVo orderInfoVo);
}
