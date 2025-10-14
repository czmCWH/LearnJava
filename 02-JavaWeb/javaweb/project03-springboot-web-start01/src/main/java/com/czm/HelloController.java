package com.czm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 请求处理类
 */
@RestController     // @RestController 注解，标识当前类是一个请求处理类
public class HelloController {

    /**
     * 接收前端发送的请求
     * @return 返回值为给前端响应的数据
     */
    @RequestMapping("/hello")   // @RequestMapping 请求映射注解，用来设置请求路径
    public String hello(String name) {  // name 参数，为接收前端传递的参数
        System.out.println("--- name = " + name);
        return "hello " + name + "~";   // 返回相应数据给浏览器
    }

    /**
     * 基于 HttpServletRequest 对象获取 Http 请求数据的各个组成部分
     */
    @RequestMapping("/request")
    public String request(HttpServletRequest request) {
        // 1、获取请求方式
        String method = request.getMethod();
        System.out.println("请求方式：method = " + method);

        // 2、获取请求url地址
        String url = request.getRequestURL().toString();
        System.out.println("完整的：url = " + url);
        String uri = request.getRequestURI();
        System.out.println("请求资源访问路径：uri = " + uri);

        // 3、获取请求协议
        String protocol = request.getProtocol();
        System.out.println("请求协议：protocol = " + protocol);

        // 4、获取请求参数 - page
        String page = request.getParameter("page");
        System.out.println("获取请求参数：page = " + page);

        // 5、获取请求头 - Accept
        String accept = request.getHeader("Accept");
        System.out.println("获取请求头信息：accept = " + accept);

//        请求方式：method = GET
//        完整的：url = http://localhost:8080/request
//        请求资源访问路径：uri = /request
//        请求协议：protocol = HTTP/1.1
//        获取请求参数：page = 10
//        获取请求头信息：accept = text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7

        return "Ok！";
    }

    /**
     * 基于 HttpServletResponse 对象设置 http 请求的响应数据 --- 方式1
     * 由于是通过 HttpServletResponse 设置响应数据，所以本方法的返回值设置为 void。
     */
    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {
        // ⚠️ 响应状态码 和 响应头如果没有特殊要求的话，通常不手动设定。服务器会根据请求处理的逻辑，自动设置响应状态码和响应头。
        // 1、设置响应状态码
//        response.setStatus(HttpServletResponse.SC_OK);
        response.setStatus(401);
        // 2、设置响应头
        response.setHeader("name", "student");

        // 3、设置响应体
        response.getWriter().write("<h1>hello response!</h1>");
    }

    /**
     * 基于 Spring 提供的 ResponseEntity 设置 Http 请求的响应数据  --- 方式2
     */
    @RequestMapping("/response2")
    public ResponseEntity<String> response2() {
        return ResponseEntity
                .status(401)
                .header("name", "javaweb-ai")
                .body("<h1>hello response2!</h1>");
    }
}
