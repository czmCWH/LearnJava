package com.czm.controller.admin;

import com.czm.dto.CategoryDTO;
import com.czm.dto.CategoryPageQueryDTO;
import com.czm.entity.Category;
import com.czm.result.PageResult;
import com.czm.result.Result;
import com.czm.service.CategoryService;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类模块
 * 1、新增菜品分类
 * 2、新增套餐分类
 * 3、条件分页查询
 * 4、根据ID删除分类
 * 5、修改分类
 * 6、启用禁用分类
 * 7、根据类型查询分类
 */

@Slf4j
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     */
    @PostMapping
    public Result<String> save(@RequestBody CategoryDTO dto) {  // @RequestBody 接收 json 请求参数
        log.info("--- 新增分类 = {} ", dto);
        categoryService.save(dto);
        return Result.success();
    }

    /**
     * 条件分页查询
     */
    @GetMapping("/page")
    public Result<PageResult> page(CategoryPageQueryDTO dto) {  // 接收普通参数，即查询字符串参数（
        log.info("--- 条件分页查询 = {} ", dto);
        PageResult result = categoryService.pageQuery(dto);
        return Result.success(result);
    }

    /**
     * 根据ID删除分类
     */
    @DeleteMapping
    public Result<String> deleteById(Long id) {  // 接收普通参数，即查询字符串参数（
        log.info("--- 根据ID删除分类 = {}", id);
        categoryService.deleteById(id);
        return Result.success();
    }

    /**
     * 修改分类
     */
    @PutMapping
    public Result<String> update(@RequestBody CategoryDTO dto) {    // @RequestBody 注解 接收json 请求参数
        categoryService.update(dto);
        return Result.success();
    }

    /**
     * 启用禁用分类
     */
    @PostMapping("/status/{status}")
    public Result<String> startOrStop(@PathVariable Integer status, Long id) {  // @PathVariable 注解路径参数；Long id 接收普通查询字符串参数
        categoryService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 根据类型查询分类
     */
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type) {  // 接收普通参数，即查询字符串参数
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
