package com.czm.mapper;

import com.czm.dto.GoodsSalesDTO;
import com.czm.dto.OrdersPageQueryDTO;
import com.czm.entity.Orders;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    Page<Orders> pageQuery(OrdersPageQueryDTO dto);

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

    /**
     * 根据状态统计订单数量
     * @param status
     */
    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);

    /**
     * 根据 时间段 统计营业额
     */
    @Select("select sum(amount) from orders where status = #{status} and order_time between #{beginTime} and #{endTime}")
    Double sumByMap(Map map);   // 因为只有一个对象参数，所以 sql 中直接写该对象的属性即可。

    /**
     * 根据 时间段、订单状态 统计订单数
     */
    Integer countByMap(Map map);

    /**
     * 查询商品销量前10
     */
    @MapKey("name")
    List<GoodsSalesDTO> sumTop10(Map map);
}