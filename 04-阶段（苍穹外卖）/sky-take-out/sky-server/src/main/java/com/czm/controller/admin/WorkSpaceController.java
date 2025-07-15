package com.czm.controller.admin;

import com.czm.result.Result;
import com.czm.service.WorkSpaceService;
import com.czm.vo.BusinessDataVO;
import com.czm.vo.DishOverViewVO;
import com.czm.vo.OrderOverViewVO;
import com.czm.vo.SetmealOverViewVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 工作台
 */

@Slf4j
@RestController
@RequestMapping("/admin/workspace")
public class WorkSpaceController {

    @Autowired
    private WorkSpaceService workSpaceService;

    /**
     * 工作台今日数据：
     *     营业额，已完成订单的总金额；
     *     有效订单，已完成订单的数量；
     *     订单完成率，有效订单数/总订单数；
     *     平均客单价，营业额/有效订单数；
     *     新增用户：新增用户的数量；
     */
    @GetMapping("/businessData")
    public Result<BusinessDataVO> businessData(){
        // 获得当天开始时间
        LocalDateTime begin = LocalDateTime.now().with(LocalTime.MIN);
        // 获得当天结束时间
        LocalDateTime end = LocalDateTime.now().with(LocalTime.MAX);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("--- 工作台今日数据 begin:{},end:{}", begin.format(formatter), end.format(formatter));

        BusinessDataVO vo = workSpaceService.getBusinessData(begin, end);
        return Result.success(vo);
    }

    /**
     * 今日订单管理数据：待接单、待派送、已完成、已取消、全部订单；
     */
    @GetMapping("/overviewOrders")
    public Result<OrderOverViewVO> orderOverView(){
        return Result.success(workSpaceService.getOrderOverView());
    }

    /**
     * 菜品总览 - 起售/停售数量
     */
    @GetMapping("/overviewDishes")
    public Result<DishOverViewVO> dishOverView(){
        return Result.success(workSpaceService.getDishOverView());
    }

    /**
     * 套餐总览 - 起售/停售数量
     */
    @GetMapping("/overviewSetmeals")
    public Result<SetmealOverViewVO> setmealOverView(){
        return Result.success(workSpaceService.getSetmealOverView());
    }

}
