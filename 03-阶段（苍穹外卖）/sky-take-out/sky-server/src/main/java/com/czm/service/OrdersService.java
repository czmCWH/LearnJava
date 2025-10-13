package com.czm.service;

import com.czm.dto.*;
import com.czm.result.PageResult;
import com.czm.vo.OrderPaymentVO;
import com.czm.vo.OrderStatisticsVO;
import com.czm.vo.OrderSubmitVO;
import com.czm.vo.OrderVO;
import io.swagger.models.auth.In;

public interface OrdersService {

    /**
     * 提交订单 - 将订单数据存入数据库 orders、order_detail 表中
     */
    OrderSubmitVO submit(OrdersSubmitDTO dto);

    /**
     * 订单支付
     */
    OrderPaymentVO payment(OrdersPaymentDTO dto) throws Exception;

    /**
     * 修改订单状态 - 支付成功
     */
    void paySuccess(String outTradeNo, Integer payMethod) throws Exception;

    /**
     * 订单历史分页查询
     */
    PageResult page(OrdersPageQueryDTO dto);

    /**
     * 根据已有订单ID再来一单
     */
    void oneMoreOrders(Long id);

    /**
     * 用户取消订单
     */
    void cancelOrderByUser(Long id);

    /**
     * 根据订单ID查询订单详情
     */
    OrderVO selectOrders(Integer id);

    /**
     * 条件搜索订单
     */
    PageResult conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO);

    /**
     * 各个状态的订单数量统计
     */
    OrderStatisticsVO statistics();

    /**
     * 查询订单详情
     */
    OrderVO details(Long id);

    /**
     * 接单
     */
    void confirm(OrdersConfirmDTO ordersConfirmDTO);

    /**
     * 拒单
     */
    void rejection(OrdersRejectionDTO ordersRejectionDTO) throws Exception;

    /**
     * 商家取消订单
     */
    void cancel(OrdersCancelDTO ordersCancelDTO) throws Exception;

    /**
     * 派送订单
     */
    void delivery(Long id);

    /**
     * 完成订单
     */
    void complete(Long id);

    /**
     * 用户催单
     */
    void reminder(Long id);
}
