package com.czm.controller.user;

import com.czm.dto.OrdersDTO;
import com.czm.dto.OrdersPageQueryDTO;
import com.czm.dto.OrdersPaymentDTO;
import com.czm.dto.OrdersSubmitDTO;
import com.czm.entity.Orders;
import com.czm.result.PageResult;
import com.czm.result.Result;
import com.czm.service.OrdersService;
import com.czm.vo.OrderPaymentVO;
import com.czm.vo.OrderSubmitVO;
import com.czm.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * C 端 - 订单模块
 */

@Slf4j
@RestController
@RequestMapping("/user/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 提交订单
     */
    @PostMapping("/submit")
    public Result<OrderSubmitVO> submit(@RequestBody OrdersSubmitDTO dto) {
        log.info("--- 提交订单 = {}", dto);
        OrderSubmitVO vo = ordersService.submit(dto);

        return Result.success(vo);
    }

    /**
     * 订单支付-微信小程序支付
     */
    @PutMapping("/payment")
    public Result<OrderPaymentVO> payment(@RequestBody OrdersPaymentDTO dto) throws Exception {
        log.info("--- 订单支付 = {}", dto);
        // 1、生成预支付对象
        OrderPaymentVO paymentVO = ordersService.payment(dto);
        log.info("--- 生成预支付交易订单 = {}", paymentVO);
        // 2、修改订单状态 --- 模拟支付成功，就更新订单状态！！！
        ordersService.paySuccess(dto.getOrderNumber(), dto.getPayMethod());
        return Result.success(paymentVO);
    }

    /**
     * 分页查询订单 List
     */
    @GetMapping("/historyOrders")
    public Result<PageResult> page(OrdersPageQueryDTO dto) {    // 接收 URL字符串查询参数
        log.info("--- 订单呢历史查询 = {}", dto);
        PageResult pageResult = ordersService.page(dto);
        return Result.success(pageResult);
    }

    /**
     * 再来一单
     */
    @PostMapping("/repetition/{id}")
    public Result<String> oneMoreOrders(@PathVariable Long id) {    // 接收 url 路径请求参数
        log.info("--- 再来一单 = {}", id);
        ordersService.oneMoreOrders(id);
        return Result.success();
    }

    /**
     * 用户取消订单
     */
    @PutMapping("/cancel/{id}")
    public Result<String> cancelOrder(@PathVariable Long id) {   // 接收 url 路径请求参数
        log.info("--- 取消订单 = {}", id);
        ordersService.cancelOrderByUser(id);
        return Result.success();
    }

    /**
     * 根据订单ID查询订单详情
     */
    @GetMapping("/orderDetail/{id}")
    public Result<OrderVO> selectOrderDetail(@PathVariable Integer id) {
        log.info("--- 根据订单ID查询 = {}", id);
        OrderVO orderVO = ordersService.selectOrders(id);
        return Result.success(orderVO);
    }

    /**
     * 用户催单
     *
     * @param id
     * @return
     */
    @GetMapping("/reminder/{id}")
    public Result reminder(@PathVariable("id") Long id) {
        log.info("用户催单：{}",id);
        ordersService.reminder(id);
        return Result.success();
    }

}
