package com.czm.controller.admin;

import com.czm.dto.DishDTO;
import com.czm.dto.DishPageQueryDTO;
import com.czm.entity.Dish;
import com.czm.result.PageResult;
import com.czm.result.Result;
import com.czm.service.DishService;
import com.czm.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜品 Controller
 */

@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     * @param dto
     * @return
     */
    @PostMapping
    public Result<String> addDish(@RequestBody DishDTO dto) {   // @RequestBody 注解接收 json 格式请求参数
        log.info("--- 新增菜品 = {}", dto);
        dishService.addDish(dto);
        return Result.success();
    }

    /**
     * 菜品分页查询
     * @param dot
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(DishPageQueryDTO dto) {  // 接收 url查询字符串参数
        log.info("--- 分页查询菜品列表：{}", dto);
        PageResult result = dishService.page(dto);
        return Result.success(result);
    }

    /**
     * 删除菜品
     * @param ids
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids) {
        /*
        @RequestParam 注解使用的场景：
        场景1、前端传递过来的是url查询字符串参数，并且是集合类型。
        场景2、从URL查询参数中提取单个或多个参数。
         */
        log.info("--- 删除菜品 = {}", ids);
        dishService.delete(ids);
        return Result.success();
    }

    /**
     * 根据菜品ID查询菜品基本信息
     */
    @GetMapping("/{id}")
    public Result<DishVO> getById(@PathVariable Long id) {  // @PathVariable 用于接收单个路径参数
        log.info("--- 根据菜品ID查询 = {}", id);
        DishVO vo = dishService.getById(id);
        return Result.success(vo);
    }

    /**
     * 更新菜品信息
     * @param dto
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody DishDTO dto) {    // @RequestBody 注解接收json请求参数
        log.info("--- 修改菜品 = {}", dto);
        dishService.update(dto);
        return Result.success();
    }
}
