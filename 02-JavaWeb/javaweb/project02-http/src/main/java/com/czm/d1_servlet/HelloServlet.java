package com.czm.d1_servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

/**
 * 演示1：通过继承实现一个 HttpServlet，并返回数据。来实现 Servlet 程序。
 */

@WebServlet(urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name"); // 获取请求参数
        String respMsg = "<h1>hello " + name + "</h1>";
        System.out.println("----czm = respMsg: " + respMsg);
        resp.getWriter().write(respMsg);    // 响应请求结果
    }
}
