package com.czm.controller;

import com.czm.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 3、Controller03 中接收 路径参数
 * 如：/dept1/{id}/{name}
 */

@RestController
public class Controller03 {

    @GetMapping("/depts1/{id}/{name}")     // {id} 标识路径参数
    public Result getById(@PathVariable Integer id, @PathVariable String name) {   // @PathVariable 注解获取路径参数
        System.out.println("---接收到 路径参数 id = " + id + ", " + name);
        return Result.success();
    }
}
