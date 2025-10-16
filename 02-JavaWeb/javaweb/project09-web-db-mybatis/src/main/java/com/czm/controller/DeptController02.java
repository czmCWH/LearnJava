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
public class DeptController02 {

    /**
     * JSON 格式的参数，通常会使用一个实体对象进行接收。
     * 规则：JSON数据的键名 必须与 方法形参对象的属性名相同，并需要使用 @RequestBody 注解标识。
     * json 格式参数适用场景：主要在 POST、PUT 请求的 请求体 中传递。
     *
     */
    @PostMapping("/depts1")
    public Result save(@RequestBody Dept dept) {
        System.out.println("--- 接收到部门参数 = " + dept);
        return Result.success();
    }
}
