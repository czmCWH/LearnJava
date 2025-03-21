package com.czm.d1_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/info")  // 在注解中定义请求路径
public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、获取请求方式
        String method = req.getMethod();
        System.out.println("method: " + method);

        // 2、获取请求路径
        String uri = req.getRequestURI();   // 不带域名的请求路径
        StringBuffer url = req.getRequestURL();     // 带域名的完整请求路径
        System.out.println("uri: " + uri);
        System.out.println("url: " + url);

        // 3、获取请求协议
        String scheme = req.getScheme();
        System.out.println("scheme: " + scheme);

        // 4、获取请求头
        String host = req.getHeader("Host");
        System.out.println("host: " + host);

        // 5、获取某个请求参数
        String name = req.getParameter("name");
        System.out.println("name: " + name);

        // 6、获取所有请求参数
        String queryString = req.getQueryString();
        System.out.println("queryString: " + queryString);

    }
}
