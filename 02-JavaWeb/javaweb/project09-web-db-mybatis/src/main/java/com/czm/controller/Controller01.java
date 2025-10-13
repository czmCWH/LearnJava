package com.czm.controller;

import com.czm.entity.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 1、controller01 中接收 URL查询参数 的几种方式
 * 例如：
 *      /depts?id=1
 */

@RestController
public class Controller01 {

    // 👉 方式1，通过原始的 HttpServletRequest 对象获取请求参数。此方式使用繁琐，项目开发基本不用。
//    @RequestMapping(value = "/depts1", method = RequestMethod.DELETE)
    @DeleteMapping("/depts1")    // 简写方式
    public Result delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        int idInt = Integer.parseInt(id);
        System.out.println("--- depts1 删除ID = " + idInt);
        return Result.success();
    }

    // 👉 方式2（有可能会用），通过 Spring 提供的 `@RequestParam` 注解，将请求参数绑定给方法形参。
    @DeleteMapping("/depts2")
    // public Result delete(@RequestParam("id") Integer deptId) {
    public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
        // ⚠️
        // @RequestParam 注解的 required 属性默认为 true，代表 id 参数必须传递，如果不传递请求将报错：code=400。
        // 如果参数可选，可以将 required 属性设置为 false 避免报错。
        System.out.println("--- depts2 删除ID = " + deptId);
        return Result.success();
    }

    // 👉 方式3（🚩推荐），如果请求参数名 与 形参变量名相同，直接定义方法形参即可接收。(省略@RequestParam)
    @DeleteMapping("/depts3")    // 简写方式
    public Result deleteWithId(Integer id) {
        System.out.println("--- depts3 删除ID = " + id);
        return Result.success();
    }
}
