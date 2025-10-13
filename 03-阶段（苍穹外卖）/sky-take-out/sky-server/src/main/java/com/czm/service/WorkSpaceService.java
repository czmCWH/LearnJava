package com.czm.service;

import com.czm.vo.BusinessDataVO;
import com.czm.vo.DishOverViewVO;
import com.czm.vo.OrderOverViewVO;
import com.czm.vo.SetmealOverViewVO;

import java.time.LocalDateTime;

/**
 * 工作台
 */

public interface WorkSpaceService {

    /**
     * 工作台今日数据：营业额、有效订单、订单完成率、平均客单价、新增用户
     */
    BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end);

    /**
     * 今日订单管理数据：待接单、待派送、已完成、已取消、全部订单；
     */
    OrderOverViewVO getOrderOverView();

    /**
     * 菜品总览 - 起售/停售数量
     */
    DishOverViewVO getDishOverView();

    /**
     * 套餐总览 - 起售/停售数量
     */
    SetmealOverViewVO getSetmealOverView();
}
