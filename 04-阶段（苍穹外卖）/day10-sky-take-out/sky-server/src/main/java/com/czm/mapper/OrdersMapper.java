package com.czm.mapper;

import com.czm.entity.Orders;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

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

    /**
     * 分页查询订单的信息，按条件动态查询
     */
    Page<Orders> pageQuery(Orders orders);

    /**
     * 根据订单ID查询订单基本信息
     */
    @Select("select * from orders where id = #{id}")
    Orders getById(Orders orders);

    /**
     * 根据 订单状态 + 下单时间 查询订单列表
     */
    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> selectByStatusAndOrderTime(Integer status, LocalDateTime orderTime);
}