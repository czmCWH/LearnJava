package com.czm.service;

import com.czm.dto.ShoppingCartDTO;
import com.czm.entity.ShoppingCart;

import java.util.List;

/**
 * C端 - 购物车模块
 */

public interface ShoppingCartService {

    /**
     * 添加商品到购物车
     */
    void addCart(ShoppingCartDTO dto);

    /**
     * 查看购物车
     */
    List<ShoppingCart> list();

    /**
     * 清空购物车
     */
    void cleanCart();
}
