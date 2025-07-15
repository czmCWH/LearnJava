package com.czm.controller.user;

import com.czm.constant.StatusConstant;
import com.czm.dto.DishDTO;
import com.czm.entity.Dish;
import com.czm.result.Result;
import com.czm.service.DishService;
import com.czm.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * C端：菜品模块
 */

@Slf4j
@RestController("userDishController")
@RequestMapping("/user/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据分类ID查询菜品 List
     */
    @GetMapping("/list")
    public Result<List<DishVO>> list(Long categoryId) {     // 接收 url查询字符串参数
        log.info("--- 根据分类ID查询菜品 categoryId = {}", categoryId);

        /*
         ⚠️ 缓存优化：
         步骤1、查询 MySql数据库前，先判断 Redis 缓存中是否存在数据
         */
        String key = "dish_" + categoryId;
        List<DishVO> rVoList = (List<DishVO>) redisTemplate.opsForValue().get(key);
        if (rVoList != null && rVoList.size() > 0) {
            // 有 Redis 缓存直接返回
            log.info("--- redis 直接返回");
            return Result.success(rVoList);
        }

        // 1、封装查询参数
        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(StatusConstant.ENABLE);
        // 2、调用 service 查询
        List<DishVO> voList = dishService.listWithFlavor(dish);

        // 步骤2、将从 MySql 数据库查询的结果存入 Redis 缓存
        redisTemplate.opsForValue().set(key, voList);

        return Result.success(voList);
    }
}
