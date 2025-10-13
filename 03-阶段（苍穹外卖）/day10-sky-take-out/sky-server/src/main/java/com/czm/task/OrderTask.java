package com.czm.task;

import com.czm.entity.Orders;
import com.czm.mapper.OrdersMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时任务类，处理 超时未支付订单 与 一直派送中 订单
 */

@Slf4j
@Component
public class OrderTask {

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 每分钟检查一次 是否存在超时未支付订单（即下单时间超过15分钟，订单超时），需要修改状态为已取消。
     */
    @Scheduled(cron = "0 0/1 * * * ?")  // 或者 "0 * * * * ?"
    public void processOutTimeUnpaidOrder() {
        // 1、查询数据库 orders 表，条件：状态-待付款；下单时间 > 当前时间 - 15分钟
        // select * from orders where status = 1 and order_time < 当前时间-15分钟
        LocalDateTime time = LocalDateTime.now().minusMinutes(15);
        List<Orders> orders = ordersMapper.selectByStatusAndOrderTime(Orders.PENDING_PAYMENT, time);
        // 2、如果查询到了数据，代表存在超时订单，需要修改订单的状态为 status = 6
        if (orders != null && orders.size() > 0) {

            log.info("--- 自动取消 超时未支付订单！！！");

            // 直接通过 for 循环修改订单的状态，因为每分钟都在查，数据量不会很多，不会导致性能慢
            orders.forEach(order -> {
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("订单超时，自动取消");
                order.setCancelTime(LocalDateTime.now());
                ordersMapper.update(order);
            });
        }
    }

    /**
     * 处理派送超时订单，每天 凌晨1点钟 检查一次是否存在 “派送中” 的订单，如果存在则修改订单状态为 “已完成”
     */
    @Scheduled(cron = "0 0 1 * * ?")    // * 代表任意；? 代表未知。
    public void processDeliveryOrder() {
        // 1、查询数据库 orders 表，条件：状态-派送中；付款时间 < 当前时间 - 1小时
        // select * from orders where status = 4 and checkout_time < 当前时间 - 1小时
        LocalDateTime time = LocalDateTime.now().minusHours(1);
        List<Orders> orders = ordersMapper.selectByStatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS, time);

        // 2、如果查询到了订单，则需要把一直派送中的订单状态修改为 status = 5
        if (orders != null && orders.size() > 0) {

            log.info("--- 自动完成 派送超时的订单！！！");

            // 直接通过 for 循环修改订单的状态，因为每分钟都在查，数据量不会很多，不会导致性能慢
            orders.forEach(order -> {
                order.setStatus(Orders.COMPLETED);
                order.setCancelReason("订单超时，自动取消");
                order.setDeliveryTime(time);
                ordersMapper.update(order);
            });
        }
    }
}
