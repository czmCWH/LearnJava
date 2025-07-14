package com.czm.mapper;

import com.czm.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * C端订单模块 - mapper
 */

@Mapper
public interface OrdersMapper {

    /**
     * 插入一条订单记录
     */
    void insert(Orders orders);

    /**
     * 根据订单号、用户ID查询订单
     */
    @Select("select * from orders where number = #{orderNo} and user_id = #{userId}")
    Orders getByNumberAndUserId(String orderNo, Long userId);

    /**
     * 修改订单信息
     */
    void update(Orders orders);
}