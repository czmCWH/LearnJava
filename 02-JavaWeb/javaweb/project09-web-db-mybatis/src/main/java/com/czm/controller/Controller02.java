package com.czm.controller;

import com.czm.pojo.Dept;
import com.czm.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2、Controller02 中接收JSON格式的请求参数
 */

@RestController
public class Controller02 {

    @PostMapping("/depts1")
    public Result save(@RequestBody Dept dept) {    // @RequestBody 注解，用来接收 json 格式请求参数，参数的key与实体类属性名必须相同。
        System.out.println("--- 接收到部门参数 = " + dept);
        return Result.success();
    }
}
