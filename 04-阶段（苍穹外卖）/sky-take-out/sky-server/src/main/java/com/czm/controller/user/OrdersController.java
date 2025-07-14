package com.czm.controller.user;

import com.czm.dto.OrdersDTO;
import com.czm.dto.OrdersPaymentDTO;
import com.czm.dto.OrdersSubmitDTO;
import com.czm.entity.Orders;
import com.czm.result.Result;
import com.czm.service.OrdersService;
import com.czm.vo.OrderPaymentVO;
import com.czm.vo.OrderSubmitVO;
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
}
