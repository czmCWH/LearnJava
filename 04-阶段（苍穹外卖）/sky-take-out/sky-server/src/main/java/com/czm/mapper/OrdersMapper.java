package com.czm.mapper;

import com.czm.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * C端订单模块 - mapper
 */

@Mapper
public interface OrdersMapper {

    /**
     * 插入一条订单记录
     */
    void insert(Orders orders);
}