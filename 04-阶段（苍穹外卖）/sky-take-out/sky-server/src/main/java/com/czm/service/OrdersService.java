package com.czm.service;

import com.czm.dto.OrdersSubmitDTO;
import com.czm.vo.OrderSubmitVO;

public interface OrdersService {

    /**
     * 提交订单 - 将订单数据存入数据库 orders、order_detail 表中
     */
    OrderSubmitVO submit(OrdersSubmitDTO dto);
}
