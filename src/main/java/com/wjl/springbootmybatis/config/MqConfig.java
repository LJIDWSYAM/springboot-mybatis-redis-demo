package com.wjl.springbootmybatis.config;

import com.wjl.springbootmybatis.entity.MiaoShaMessage;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MqConfig {
    public static final String MIAOSHA_QUEUE_NAME="miaosha.queue";

    @Bean
    public Queue dead_DirectQueue() {
        return new Queue(MIAOSHA_QUEUE_NAME,true);
    }

    @Bean
    public Queue dead_DelayQueue() {
        Map<String, Object> params = new HashMap<>();
        params.put("x-dead-letter-exchange", "DeadExchange");
        params.put("x-dead-letter-routing-key", "DeadRouting");
        // 如果设置，则队列中所有消息的过期时间都是 5 秒，如果希望在每个消息中进行单独设置，则不能设置
        params.put("x-message-ttl", 1800000);
        return new Queue("Dead_DelayQueue",true, false, false, params);
    }

    @Bean
    DirectExchange dead_DelayExchange() {
        return new DirectExchange("Dead_DelayExchange");
    }

    @Bean
    Binding bindingDead_DelayQueue() {
        return BindingBuilder.bind(dead_DelayQueue()).to(dead_DelayExchange()).with("Dead_DelayRouting");
    }

    @Bean
    public Queue deadQueue() {
        return new Queue("DeadQueue",true);
    }

    @Bean
    DirectExchange deadExchange() {
        return new DirectExchange("DeadExchange");
    }

    @Bean
    Binding bindingDeadQueue() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("DeadRouting");
    }


}
