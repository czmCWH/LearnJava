package com.hmall.trade.constants;

/**
 * 定义订单延迟 MQ 收发消息 name
 */

public interface MQConstants {

    /**
     * 交换机 - 交易订单延迟消息
     */
    String DELAY_EXCHANGE_NAME = "trade.delay.direct";

    /**
     * 队列 - 交易订单延迟队列
     */
    String DELAY_ORDER_QUEUE_NAME = "trade.delay.order.queue";

    /**
     * routeKey - 交易订单延迟 routing key
     */
    String DELAY_ORDER_KEY = "delay.order.query";
}
