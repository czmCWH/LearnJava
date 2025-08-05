package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 基于 Java Bean 的方式声明 Fanout 类型的交换机，并创建队列与其绑定。
 * 只要声明好 队列与交换机 绑定关系，Spring 应用一启动就会自动创建队列与交换机。
 */

//@Configuration
//public class FanoutConfig {
//
//    // 声明交换机
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        // 声明交换机有 2 种方式
////        return new FanoutExchange("hmall.fanout");
//        return ExchangeBuilder.fanoutExchange("hmall.fanout").build();
//    }
//
//    // 声明队列
//    @Bean
//    public Queue fanoutQueue1(){
//        // 声明队列 也有 2 种方式
////        return new Queue("fanout.queue1");
//        return QueueBuilder.durable("fanout.queue1").lazy().build();
//    }
//
//    // 声明绑定交换机 fanoutExchange 和 队列 fanoutQueue1
//    @Bean
//    public Binding fanoutQueue1Binding(Queue fanoutQueue1, FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
//    }
//
//    //声明队列
//    @Bean
//    public Queue fanoutQueue2(){
//        return new Queue("fanout.queue2");
//    }
//
//    // 声明绑定交换机 fanoutExchange 和 队列 fanoutQueue2
//    @Bean
//    public Binding fanoutQueue2Binding(Queue fanoutQueue2, FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
//    }
//}
