package com.czm.controller.admin;

import com.czm.dto.OrdersCancelDTO;
import com.czm.dto.OrdersConfirmDTO;
import com.czm.dto.OrdersPageQueryDTO;
import com.czm.dto.OrdersRejectionDTO;
import com.czm.result.PageResult;
import com.czm.result.Result;
import com.czm.service.OrdersService;
import com.czm.vo.OrderStatisticsVO;
import com.czm.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理
 */

@RestController("/admin/order")
public class OrderController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 订单搜索
     *
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/conditionSearch")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageResult pageResult = ordersService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 各个状态的订单数量统计
     *
     * @return
     */
    @GetMapping("/statistics")
    public Result<OrderStatisticsVO> statistics() {
        OrderStatisticsVO orderStatisticsVO = ordersService.statistics();
        return Result.success(orderStatisticsVO);
    }


    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    public Result<OrderVO> details(@PathVariable("id") Long id) {
        OrderVO orderVO = ordersService.details(id);
        return Result.success(orderVO);
    }

    /**
     * 接单
     *
     * @return
     */
    @PutMapping("/confirm")
    public Result confirm(@RequestBody OrdersConfirmDTO ordersConfirmDTO) {
        ordersService.confirm(ordersConfirmDTO);
        return Result.success();
    }

    /**
     * 拒单
     *
     * @return
     */
    @PutMapping("/rejection")
    public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        ordersService.rejection(ordersRejectionDTO);
        return Result.success();
    }

    /**
     * 取消订单
     *
     * @return
     */
    @PutMapping("/cancel")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        ordersService.cancel(ordersCancelDTO);
        return Result.success();
    }

    /**
     * 派送订单
     *
     * @return
     */
    @PutMapping("/delivery/{id}")
    public Result delivery(@PathVariable("id") Long id) {
        ordersService.delivery(id);
        return Result.success();
    }

    /**
     * 完成订单
     *
     * @return
     */
    @PutMapping("/complete/{id}")
    public Result complete(@PathVariable("id") Long id) {
        ordersService.complete(id);
        return Result.success();
    }
}
