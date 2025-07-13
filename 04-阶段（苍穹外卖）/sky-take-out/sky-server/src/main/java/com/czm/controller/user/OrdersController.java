package com.czm.controller.user;

import com.czm.dto.OrdersDTO;
import com.czm.dto.OrdersSubmitDTO;
import com.czm.entity.Orders;
import com.czm.result.Result;
import com.czm.service.OrdersService;
import com.czm.vo.OrderSubmitVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
