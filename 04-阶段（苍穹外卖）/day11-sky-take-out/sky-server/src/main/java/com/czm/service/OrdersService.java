package com.czm.service;

import com.czm.dto.OrdersPageQueryDTO;
import com.czm.dto.OrdersPaymentDTO;
import com.czm.dto.OrdersSubmitDTO;
import com.czm.result.PageResult;
import com.czm.vo.OrderPaymentVO;
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
}
