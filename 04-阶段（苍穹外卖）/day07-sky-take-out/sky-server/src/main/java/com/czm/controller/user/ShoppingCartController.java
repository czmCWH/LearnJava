package com.czm.controller.user;

import com.czm.dto.ShoppingCartDTO;
import com.czm.entity.ShoppingCart;
import com.czm.result.Result;
import com.czm.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车 Controller
 */

@Slf4j
@RestController
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加商品到购物车
     */
    @PostMapping("/add")
    public Result<String> addCart(@RequestBody ShoppingCartDTO dto) {   // @RequestBody 注解接收 json 参数
        log.info("--- 添加商品到购物车 = {}", dto);
        shoppingCartService.addCart(dto);
        return Result.success();
    }

    /**
     * 查看购物车
     */
    @GetMapping("/list")
    public Result<List<ShoppingCart>> list() {
        log.info("--- 查看购物车");
        List<ShoppingCart> list = shoppingCartService.list();
        return Result.success(list);
    }

    /**
     * 清空购物车
     */
    @DeleteMapping("/clean")
    public Result<String> cleanCart() {
        log.info("--- 清空购物车");
        shoppingCartService.cleanCart();
        return Result.success();
    }
}
