package com.hmall.trade.listener;
import com.alibaba.nacos.shaded.com.google.protobuf.Message;
import com.hmall.api.clients.PayClient;
import com.hmall.api.dto.PayOrderDTO;
import com.hmall.trade.constants.MQConstants;
import com.hmall.trade.domain.po.Order;
import com.hmall.trade.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 异步通讯组件-RabbitMQ 实现：监听支付超时消息
 */

@Component
@RequiredArgsConstructor
public class OrderDelayMessageListener {

    private final IOrderService orderService;

    // OpenFeign 远程调用
    private final PayClient payClient;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = MQConstants.DELAY_ORDER_QUEUE_NAME),
            exchange = @Exchange(name = MQConstants.DELAY_EXCHANGE_NAME),
            key = MQConstants.DELAY_ORDER_KEY
    ))
    public void listenerOrderDelay(Long orderId) {
        // 1、查询订单
        Order order = orderService.getById(orderId);

        // 2、检查订单状态，判断是否已支付
        if (order == null || order.getStatus() != 1) {
            // 订单不存 or 订单已支付
            return;
        }

        // 3、未支付，OpenFeign 远程调用 支付服务 查询支付流水状态
        PayOrderDTO payOrder = payClient.queryPayOrderByBizOrderNo(orderId);
        if (payOrder != null && payOrder.getStatus() == 3) {
            // 4.1、已支付，标记订单状态为已支付
            orderService.markOrderPaySuccess(orderId);
        } else {
            // 4.1、未支付，取消订单，恢复库存
            orderService.cancelOrder(orderId);
        }
    }
}
