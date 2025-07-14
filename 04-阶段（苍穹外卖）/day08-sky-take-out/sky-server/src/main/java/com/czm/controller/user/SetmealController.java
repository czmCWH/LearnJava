package com.czm.controller.user;

import com.czm.constant.StatusConstant;
import com.czm.entity.Setmeal;
import com.czm.result.Result;
import com.czm.service.SetmealService;
import com.czm.vo.DishItemVO;
import com.czm.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * C端：套餐模块 Controller
 */

@Slf4j
@RestController("userSetmealController")
@RequestMapping("/user/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 根据分类ID查询套餐
     */
    @Cacheable(cacheNames = "setmeal", key = "#categoryId")     // SpringCache 使用 @Cacheable 注解先查询缓存，有缓存直接返回，否则调用方法后，并将返回值存入缓存
    @GetMapping("/list")
    public Result<List<SetmealVO>> listByCategoryId(Long categoryId) {  // 接收URL字符串查询参数
        log.info("--- 根据分类ID查询套餐 = {}", categoryId);
        // 1、封装查询条件，默认查询启用的套餐
        Setmeal setmeal = new Setmeal();
        setmeal.setCategoryId(categoryId);
        setmeal.setStatus(StatusConstant.ENABLE);
        // 2、调用 Service 查询
        List<SetmealVO> list = setmealService.listBySetmeal(setmeal);
        return Result.success(list);
    }

    /**
     * 根据 套餐ID 查询包含的菜品
     */
    @GetMapping("/dish/{id}")
    public Result<List<DishItemVO>> dishList(@PathVariable Long id) {   // 接收路径请求参数
        List<DishItemVO> list = setmealService.getDishItemById(id);
        return Result.success(list);
    }
}
