package com.hmall.trade.listener;

import com.hmall.trade.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 异步通讯组件-RabbitMQ 实现： 支付状态监听器
 */

@Component
@RequiredArgsConstructor
public class PayStatusListener {

    private final IOrderService orderService;

    /*
    监听 支付成功队列消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "trade.pay.success.queue", durable = "true"),
            exchange = @Exchange(value = "pay.direct", type = ExchangeTypes.DIRECT),
            key = {"pay.success"}
    ))
    public void listenPaySuccess(Long orderId) {
        System.out.println("czm ---- 收到 MQ 组件消息 = " + orderId);
        orderService.markOrderPaySuccess(orderId);
    }
}
