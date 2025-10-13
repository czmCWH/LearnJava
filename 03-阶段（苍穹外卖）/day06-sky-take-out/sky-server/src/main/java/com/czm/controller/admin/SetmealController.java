package com.czm.controller.admin;

import com.czm.dto.SetmealDTO;
import com.czm.dto.SetmealPageQueryDTO;
import com.czm.result.PageResult;
import com.czm.result.Result;
import com.czm.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 套餐管理 Controller
 *      1、新增套餐
 *      2、套餐分页查询
 *      3、根据 ids 批量删除套餐
 *      4、修改套餐
 *      5、起售停售套餐
 * 与 菜品管理 逻辑类似！！！
 */

@Slf4j
@RestController
@RequestMapping("/admin/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    /**
     * 新增套餐
     */
    @PostMapping
    public Result<String> addSetmeal(@RequestBody SetmealDTO dto) { // @RequestBody 接收 json 格式参数
        log.info("--- 新增套餐 = {}", dto);
        setmealService.addSetmeal(dto);
        return Result.success();
    }

    /**
     * 套餐分页查询
     */
    @GetMapping("/page")
    public Result<PageResult> page(SetmealPageQueryDTO queryDTO) {  // 接收 url字符串查询参数
        log.info("--- 套餐分页查询 = {}", queryDTO);
        PageResult result = setmealService.page(queryDTO);
        return Result.success(result);
    }

    /**
     * 根据套餐 ids 批量删除套餐
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam List<Long> ids) {    // 接收 url集合类型字符串查询参数
         /*
        @RequestParam 注解使用的场景：
        场景1、前端传递过来的是url查询字符串参数，并且是集合类型。
        场景2、从URL查询参数中提取单个或多个参数。
         */
        log.info("--- 根据IDs批量删除套餐 = {}", ids);
        setmealService.delete(ids);
        return Result.success();
    }

    /**
     * 修改套餐
     */
    @PutMapping
    public Result update(@RequestBody SetmealDTO dto) { // @RequestBody 接收 json 格式参数
        log.info("--- 修改套餐 = {}", dto);
        setmealService.update(dto);
        return Result.success();
    }

    /**
     * 修改起售停售套餐
     */
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {     // @PathVariable 注解路径参数；Long id 接收普通查询字符串参数
        log.info("--- 设置套餐起售和停售状态 = {}, {}", status, id);
        setmealService.startOrStop(status, id);
        return Result.success();
    }
}
