package com.company;

import org.springframework.stereotype.Component;

@Component  // 此类会被 Spring 容器实例化为一个 Bean
public class TokenParser {
    public void parse() {
        System.out.println("--- TokenParser.parse");
    }
}
