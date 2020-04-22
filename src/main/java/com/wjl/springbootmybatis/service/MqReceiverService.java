package com.wjl.springbootmybatis.service;

import com.wjl.springbootmybatis.config.MqConfig;
import com.wjl.springbootmybatis.entity.MiaoShaMessage;
import com.wjl.springbootmybatis.entity.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqReceiverService {
    @Autowired
    OrderService orderService;
    @RabbitListener(queues = MqConfig.MIAOSHA_QUEUE_NAME)
    public void receiverMiaoshaMessage(MiaoShaMessage miaoShaMessage){
                //1.生成订单AND减少库存
        orderService.insertOrderANDreduceMiaoshaGoodsNum(miaoShaMessage.getMiaoshagoods_id(),
                miaoShaMessage.getUser_account());
    }

    @RabbitListener(queues = "DeadQueue")
    public void receiverUpdateOrderState(Order order){
        MiaoShaMessage miaoShaMessage=new MiaoShaMessage();
        miaoShaMessage.setMiaoshagoods_id(String.valueOf(order.getMiaoshagoods_id()));
        miaoShaMessage.setUser_account(order.getUser_account());
        //1修改订单状态
        orderService.updateOrderState(miaoShaMessage);
    }

}
