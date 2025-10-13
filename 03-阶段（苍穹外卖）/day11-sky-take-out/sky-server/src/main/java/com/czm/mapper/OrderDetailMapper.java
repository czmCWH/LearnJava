package com.czm.mapper;

import com.czm.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 订单明细（即商品信息） Mapper
 */

@Mapper
public interface OrderDetailMapper {

    /**
     * 批量插入订单明细 --- 插入的是 List 数据，需要基于XML foreach 标签
     * @param orderDetailList
     */
    void insertBatch(List<OrderDetail> orderDetailList);

    /**
     * 根据订单ID查询订单所有商品
     */
    @Select("select * from order_detail where order_id = #{id}")
    List<OrderDetail> selectByOrderId(Long orderId);
}
