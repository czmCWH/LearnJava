package com.czm.controller.user;

import com.czm.entity.Category;
import com.czm.result.Result;
import com.czm.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * C端 - 分类模块
 */

@Slf4j
@RestController("userCategoryController")
@RequestMapping("/user/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询分类List
     */
    @GetMapping("/list")
    public Result<List<Category>> list(Integer type) {  // 接收 URL字符串查询参数
        log.info("--- 查询分类List，type = {}", type);
        List<Category> list = categoryService.list(type);
        return Result.success(list);
    }
}
