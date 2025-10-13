package com.czm.service.impl;

import com.czm.context.BaseContext;
import com.czm.dto.ShoppingCartDTO;
import com.czm.entity.Dish;
import com.czm.entity.Setmeal;
import com.czm.entity.ShoppingCart;
import com.czm.mapper.DishMapper;
import com.czm.mapper.SetmealMapper;
import com.czm.mapper.ShoppingCartMapper;
import com.czm.service.ShoppingCartService;
import com.czm.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public void addCart(ShoppingCartDTO dto) {
        // 1、把商品添加到 shopping_cart 表中
        // 创建购物车实体类
        ShoppingCart shoppingCart = new ShoppingCart();

        // 拷贝属性
        BeanUtils.copyProperties(dto, shoppingCart);    // ⚠️ 浅拷贝，拷贝dto属性到 shoppingCart

        // 判断 商品是否在购物车中，根据 商品信息+userId （只查当前用户自己的）
//        shoppingCart.setUserId(BaseContext.getCurrentId());
        shoppingCart.setUserId(4L);

        ShoppingCart cart = shoppingCartMapper.selectBy(shoppingCart);
        if (cart == null) {     // 代表购物车中暂时没有此商品
            // 补充缺失属性

            // 1、添加的商品是 菜品
            if (dto.getDishId() != null) {
                Dish dish = dishMapper.selectById(dto.getDishId());

                shoppingCart.setName(dish.getName());
                shoppingCart.setAmount(dish.getPrice());
                shoppingCart.setImage(dish.getImage());
            }

            // 2、添加的商品是 套餐
            if (dto.getSetmealId() != null) {
                SetmealVO setmealVO = setmealMapper.selectById(dto.getSetmealId());

                shoppingCart.setName(setmealVO.getName());
                shoppingCart.setAmount(setmealVO.getPrice());
                shoppingCart.setImage(setmealVO.getImage());
            }

            shoppingCart.setNumber(1);  // 添加商品的数量，到底是 +1 还是 1 ？
            shoppingCart.setCreateTime(LocalDateTime.now());

            // 3、将商品数据存入到 Shopping_cart 表中
            shoppingCartMapper.insert(shoppingCart);

        } else {    // 代表购物车中已经有此商品

            // 4、将原来的购物车商品数量 +1
            cart.setNumber(cart.getNumber() + 1);
            shoppingCartMapper.update(cart);
        }



    }

    @Override
    public List<ShoppingCart> list() {
        Long userId = BaseContext.getCurrentId();   // 获取用户ID
        userId = 4L;
        return shoppingCartMapper.list(userId);
    }

    @Override
    public void cleanCart() {
        Long userId = BaseContext.getCurrentId();   // 获取用户ID
        userId = 4L;
        shoppingCartMapper.clean(userId);
    }
}
