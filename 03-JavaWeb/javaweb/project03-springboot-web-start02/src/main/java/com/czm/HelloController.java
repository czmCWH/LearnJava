package com.czm;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello2")
    public String hello() {
        return "hello world 222222";
    }
}
