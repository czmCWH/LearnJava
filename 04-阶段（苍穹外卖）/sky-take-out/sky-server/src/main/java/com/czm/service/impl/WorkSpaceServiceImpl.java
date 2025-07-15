package com.czm.service.impl;

import com.czm.constant.StatusConstant;
import com.czm.entity.Orders;
import com.czm.mapper.DishMapper;
import com.czm.mapper.OrdersMapper;
import com.czm.mapper.SetmealMapper;
import com.czm.mapper.UserMapper;
import com.czm.service.WorkSpaceService;
import com.czm.vo.BusinessDataVO;
import com.czm.vo.DishOverViewVO;
import com.czm.vo.OrderOverViewVO;
import com.czm.vo.SetmealOverViewVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class WorkSpaceServiceImpl implements WorkSpaceService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;


    @Override
    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        // 1、查询总订单数
        Map map = new HashMap();
        map.put("beginTime",begin);
        map.put("endTime",end);
        Integer totalOrderCount = ordersMapper.countByMap(map);

        // 2、今日营业额，已完成订单的总金额；
        map.put("status", Orders.COMPLETED);
        Double turnover = ordersMapper.sumByMap(map);
        turnover = turnover == null? 0.0 : turnover;

        // 3、今日有效订单，已完成订单的数量；
        Integer validOrderCount = ordersMapper.countByMap(map);

        // 4、订单完成率，有效订单数/总订单数；
        Double orderCompletionRate = 0.0;

        // 5、平均客单价，营业额/有效订单数；
        Double unitPrice = 0.0;

        if (totalOrderCount != 0 && validOrderCount != 0) {
            //订单完成率
            orderCompletionRate = validOrderCount.doubleValue() / totalOrderCount;
            //平均客单价
            unitPrice = turnover / validOrderCount;
        }

        // 6、新增用户：新增用户的数量；
        Integer newUsers = userMapper.countByMap(map);

        return BusinessDataVO.builder()
                .turnover(turnover)
                .validOrderCount(validOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .unitPrice(unitPrice)
                .newUsers(newUsers)
                .build();
    }

    @Override
    public OrderOverViewVO getOrderOverView() {
        Map map = new HashMap();
        map.put("beginTime", LocalDateTime.now().with(LocalTime.MIN));

        //待接单
        map.put("status", Orders.TO_BE_CONFIRMED);
        Integer waitingOrders = ordersMapper.countByMap(map);

        //待派送
        map.put("status", Orders.CONFIRMED);
        Integer deliveredOrders = ordersMapper.countByMap(map);

        //已完成
        map.put("status", Orders.COMPLETED);
        Integer completedOrders = ordersMapper.countByMap(map);

        //已取消
        map.put("status", Orders.CANCELLED);
        Integer cancelledOrders = ordersMapper.countByMap(map);

        //全部订单
        map.put("status", null);
        Integer allOrders = ordersMapper.countByMap(map);

        return OrderOverViewVO.builder()
                .waitingOrders(waitingOrders)
                .deliveredOrders(deliveredOrders)
                .completedOrders(completedOrders)
                .cancelledOrders(cancelledOrders)
                .allOrders(allOrders)
                .build();
    }

    @Override
    public DishOverViewVO getDishOverView() {
        Map map = new HashMap();
        map.put("status", StatusConstant.ENABLE);
        Integer sold = dishMapper.countByMap(map);

        map.put("status", StatusConstant.DISABLE);
        Integer discontinued = dishMapper.countByMap(map);

        return DishOverViewVO.builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }

    @Override
    public SetmealOverViewVO getSetmealOverView() {

        Map map = new HashMap();
        map.put("status", StatusConstant.ENABLE);
        Integer sold = setmealMapper.countByMap(map);

        map.put("status", StatusConstant.DISABLE);
        Integer discontinued = setmealMapper.countByMap(map);

        return SetmealOverViewVO.builder()
                .sold(sold)
                .discontinued(discontinued)
                .build();
    }
}
