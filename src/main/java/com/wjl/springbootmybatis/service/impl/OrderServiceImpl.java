package com.wjl.springbootmybatis.service.impl;

import com.wjl.springbootmybatis.Utils.RedisUtil;
import com.wjl.springbootmybatis.dao.GoodsDao;
import com.wjl.springbootmybatis.dao.OrderDao;
import com.wjl.springbootmybatis.entity.*;
import com.wjl.springbootmybatis.service.MqSendService;
import com.wjl.springbootmybatis.service.OrderService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    MqSendService mqSendService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public List<Address> selectAddressByUserAccount(String user_account) {
        List<Address> address=orderDao.selectAddressByUserAccount(user_account);
        return address;
    }

    @Transactional
    @Override
    public OrderDetailInfo insertOrderdetailInfo(String miaoshagoodsId, String user_account, String address_id) {
        OrderDetailInfo orderdetailInfo =new OrderDetailInfo();
        orderdetailInfo.setUser_account(user_account);
        orderdetailInfo.setCreate_time(new Date());
        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyyMMdd" );
        Date d1= new Date();
        String str1 = sdf1.format(d1);
        orderdetailInfo.setOrder_no(str1+miaoshagoodsId+user_account);
        orderdetailInfo.setOrder_no(new Date().getTime()+miaoshagoodsId);
        orderdetailInfo.setMiaoshagoods_id(Integer.valueOf(miaoshagoodsId));
        orderdetailInfo.setBuy_count(1);
        orderdetailInfo.setAddress_id(Integer.parseInt(address_id));
        insertOrderInfo(orderdetailInfo);
        return orderdetailInfo;
    }

    @Override
    public void updateOrderState(MiaoShaMessage miaoShaMessage) {
        orderDao.updateOrderState(miaoShaMessage);
    }



    @Override
    public OrderDetailInfoVo selectAllInfoByOrderNo(String order_no) {
        OrderDetailInfoVo orderInfoVo=orderDao.selectAllInfoByOrderNo(order_no);
        return orderInfoVo;
    }

    @Override
    public void reduceMiaoshaGoodsNum(String miaoshagoodsId) {
        orderDao.reduceMiaoshaGoodsNum(miaoshagoodsId);
    }

    @Override
    public void insertOrderInfo(OrderDetailInfo orderdetailInfo) {
        orderDao.insertOrderInfo(orderdetailInfo);
    }
    @Override
    public void insertOrder(Order order) {
        orderDao.insertOrder(order);
    }

    @Override
    public void updateOrder(OrderDetailInfoVo orderInfoVo) {
        orderDao.updateOrder(orderInfoVo);
    }
    @Transactional
    @Override
    public void insertOrderANDreduceMiaoshaGoodsNum(String miaoshagoodsId, String user_account) {
        //1.减库存
        reduceMiaoshaGoodsNum(miaoshagoodsId);
        //2.生成订单
        Order order =new Order();
        order.setUser_account(user_account);
        order.setCreate_time(new Date());
        SimpleDateFormat sdf1 =new SimpleDateFormat("yyyyMMdd" );
        Date d1= new Date();
        String str1 = sdf1.format(d1);
        order.setOrder_no(str1+miaoshagoodsId+user_account);
        order.setMiaoshagoods_id(Integer.valueOf(miaoshagoodsId));
        order.setBuy_count(1);
        order.setState(0);//0才创建，1已支付，2超时未支付
        insertOrder(order);
        //向死信队列发送消息
        rabbitTemplate.convertAndSend("Dead_DelayExchange", "Dead_DelayRouting", order);
    }

    @Override
    public Order isRepeatOrder(MiaoShaMessage miaoShaMessage) {
        return orderDao.isRepeatOrder(miaoShaMessage);
    }

}