package com.wjl.springbootmybatis.controller;

import com.wjl.springbootmybatis.Utils.RedisUtil;
import com.wjl.springbootmybatis.entity.Address;
import com.wjl.springbootmybatis.entity.OrderInfo;
import com.wjl.springbootmybatis.entity.UserInfo;
import com.wjl.springbootmybatis.entity.miaoshaGoods;
import com.wjl.springbootmybatis.service.AddressService;
import com.wjl.springbootmybatis.service.GoodsService;
import com.wjl.springbootmybatis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: liujun
 * \* Date: 2019/6/24
 * \* Time: 8:47
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    RedisUtil redisUtil;
    @GetMapping("/buy/{miaoshagoods_id}")
    public ModelAndView buy(@PathVariable("miaoshagoods_id")String miaoshagoods_id,ModelAndView modelAndView,
                            HttpSession session){
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        String user_account = userInfo.getUser_account();
        List<Address> address=orderService.selectAddressByUserAccount(user_account);
        miaoshaGoods miaoshaGoods=goodsService.selectMiaoshaGoods(miaoshagoods_id);
        modelAndView.addObject("miaoshaGoods",miaoshaGoods);
        modelAndView.addObject("Address",address);
        modelAndView.setViewName("checkout");
        return modelAndView;
    }


    @RequestMapping("/payment")
    public ModelAndView doOrder(ModelAndView modelAndView, HttpSession session,
                                String miaoshaGoodsId,String address_id) {
        //判断登录
        UserInfo userInfo = (UserInfo) session.getAttribute("UserInfo");
        if (userInfo == null) {
            modelAndView.setViewName("sendredirect:/");
            return modelAndView;
        }
        // 支付环节需要，地址id，账号，秒杀商品id。
        String user_account = userInfo.getUser_account();//miaoshaGoodsId,address_id已有
        //下订单   (判断库存，减少库存，存入订单表)
        //判断库存  select miaosha_stock from miaoshaGoods where miaoshagoods_id = miaoshaGoodsId
        int num = goodsService.selectMiaoshaGoods(miaoshaGoodsId).getMiaosha_stock();
        if (num > 0) {
            String key="goods"+miaoshaGoodsId;
            Object o = redisUtil.lPop(key);
            if(o != null){
                //说明有库存
                //减少库存   update miaosha_goods set miaosha_stock = miaosha_stock -1 where miaoshagoods_id = 3
                //插入订单 insert into orderinfo(order_no,address_id,miaoshagoods_id,user_id,create_time,buy_count) VALUES()
                //        OrderInfo orderInfo = orderService.doOrderInfo(miaoshaGoodsId,userInfo);
                OrderInfo orderInfo=orderService.insertOnderInfoANDreduceMiaoshaGoodsNum(miaoshaGoodsId,user_account,address_id);
                String order_no=orderInfo.getOrder_no();
                //假设现在是立即支付  引入支付宝
                modelAndView.setViewName("redirect:/alipay/pay?order_no="+order_no);
                return modelAndView;
            }

        }
        modelAndView.setViewName("");
        return modelAndView;
    }
}