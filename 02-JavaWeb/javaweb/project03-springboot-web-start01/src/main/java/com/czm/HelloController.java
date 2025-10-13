package com.czm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求处理类
 */
@RestController     // @RestController 注解，标识当前类是一个请求处理类
public class HelloController {

    /**
     * 接收前端发送的请求
     * @return 返回值为给前端响应的数据
     */
    @RequestMapping("/hello")   // 请求映射注解，用来设置请求路径
    public String hello(String name) {
        System.out.println("--- 返回信息给浏览器");
        return "hello " + name + "~";
    }
}
