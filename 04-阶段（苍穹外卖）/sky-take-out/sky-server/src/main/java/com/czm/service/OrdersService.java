package com.czm.service;

import com.czm.dto.OrdersPaymentDTO;
import com.czm.dto.OrdersSubmitDTO;
import com.czm.vo.OrderPaymentVO;
import com.czm.vo.OrderSubmitVO;

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
}
