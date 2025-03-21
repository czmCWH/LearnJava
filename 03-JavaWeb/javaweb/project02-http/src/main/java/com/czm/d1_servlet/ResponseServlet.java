package com.czm.d1_servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/response")
public class ResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、设置响应状态码
        resp.setStatus(HttpServletResponse.SC_OK);

        // 2、设置响应头
        resp.setContentType("text/html;charset=utf-8"); // 设置响应数据类型和编码，⚠️响应数据类型(text/html;)必须写在前面
        resp.setHeader("study", "java");

        // 3、设置响应数据
        resp.getWriter().write("<h1>hello world</h1>");

        // 注意：响应状态码 和 响应头如果没有特殊要求的话，通常不手动设定。服务器会根据请求处理的逻辑，自动设置响应状态码和响应头。
    }
}
