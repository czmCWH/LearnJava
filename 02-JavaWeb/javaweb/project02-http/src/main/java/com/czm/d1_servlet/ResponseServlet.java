package com.czm.d1_servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;


/**
 * 演示3：在 Servlet 程序中，通过 HttpServletResponse 设置相应数据（注意：一般不设置）。
 *
 * `HttpServletResponse`：用于封装所有的响应数据；
 *
 */

@WebServlet(urlPatterns = "/response")
public class ResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、设置响应状态码
//        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);   // 手动修改为 404

        // 2、设置响应头
        resp.setHeader("study", "java");
        // 设置响应数据类型和编码，⚠️响应数据类型(text/html;)必须写在前面
        // 避免中文访问乱码，如：http://localhost:8080/demo/response?name=张三
//        resp.setHeader("content-type", "text/html;charset=utf-8");
        resp.setContentType("text/html;charset=utf-8");


        // 3、设置响应数据
        String name = req.getParameter("name");
        resp.getWriter().write("<h1>hello world</h1>" + name);

        // 注意：响应状态码 和 响应头如果没有特殊要求的话，通常不手动设定。服务器会根据请求处理的逻辑，自动设置响应状态码和响应头。
    }
}
