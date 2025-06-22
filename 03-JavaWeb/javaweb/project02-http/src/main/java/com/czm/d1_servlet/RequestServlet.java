package com.czm.d1_servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


/**
 * 演示2：在 Servlet 程序中，通过 HttpServletRequest 对象获取所有的请求头信息。
 */

@WebServlet(urlPatterns = "/info")  // 在注解中定义请求路径
public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求方式
        String method = req.getMethod();
        System.out.println("---1 method: " + method);   // 打印：GET

        // 2、获取请求路径
        String uri = req.getRequestURI();   // 不带域名的请求路径
        StringBuffer url = req.getRequestURL();     // 带域名的完整请求路径
        System.out.println("---2 uri: " + uri);     // 打印：/demo/info
        System.out.println("---2 url: " + url);     // 打印： http://localhost:8080/demo/info

        // 3、获取请求协议
        String scheme = req.getScheme();
        System.out.println("---3 scheme: " + scheme);   // 打印：http

        // 4、获取请求头
        String host = req.getHeader("Host");
        System.out.println("---4 host: " + host);       // 打印：localhost:8080

        // 5、获取某个请求参数
        String name = req.getParameter("name");
        System.out.println("---5 name: " + name);

        // 6、获取所有请求参数
        String queryString = req.getQueryString();
        System.out.println("---6 queryString: " + queryString);

    }
}
