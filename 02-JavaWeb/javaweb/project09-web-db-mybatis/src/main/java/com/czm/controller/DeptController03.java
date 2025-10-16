package com.czm.controller;

import com.czm.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 3、Controller03 中接收 路径参数
 * 如：/dept1/{id}/{name}
 */

@RestController
@RequestMapping
public class DeptController03 {

    // 路径参数：通过请求URL直接传递参数，在uri资源中使用 {路径参数名} 来标识该路径参数，需要使用 @PathVariable 获取路径参数
    // 如果路径参数的参数名 与 方法形参名 相同，可以省略  @PathVariable 中 value 属性值。
    @GetMapping("/depts1/{id}/{name}")    // {id} 是一个路径参数
    public Result getById(@PathVariable Integer id, @PathVariable String name) {   // @PathVariable 注解获取路径参数
        System.out.println("---接收到 路径参数 id = " + id + ", " + name);
        return Result.success();
    }
}
