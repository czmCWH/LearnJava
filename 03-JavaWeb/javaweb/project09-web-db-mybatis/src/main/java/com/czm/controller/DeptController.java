package com.czm.controller;

import com.czm.entity.Dept;
import com.czm.entity.Result;
import com.czm.service.DeptService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 请求处理类
 */

@RestController     // 等价于 @Controller+@ResponseBody
public class DeptController {

    @Resource(name = "deptServiceImpl")
    private DeptService deptService;

    @GetMapping("/depts")      // 限定接口的请求方式，只能以 GET 方式请求
    public Result getAllDept() {
        // 1、调用 Service，获取数据
        List<Dept> depts = deptService.list();

        // 2、响应数据 JSON 格式
        return Result.success(depts);
    }
}
