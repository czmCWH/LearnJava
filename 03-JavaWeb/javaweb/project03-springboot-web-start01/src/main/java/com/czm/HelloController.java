package com.czm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求处理类
 */
@RestController     // 标识当前类是一个请求处理类
public class HelloController {
    @RequestMapping("/hello")   // 用来设置请求路径
    public String hello() {
        return "hello world!";
    }
}
