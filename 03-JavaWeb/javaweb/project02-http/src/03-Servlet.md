# Servlet

Servlet 是运行在Web服务器中的**小型java程序**，是 Java 提供的一门动态 web 资源开发技术。通常通过 HTTP 协议接收和响应来自于客户端的请求。

Servlet 是 JavaEE 规范之一，其实就是一个接口(定义 Servlet 需实现 Servlet 接口 或 继承 HttpServlet)，**并由 web服务器 运行 Servlet**。

Servlet 对象是由谁创建的，Servlet 的 doGet 方法是由谁调用的？
Servlet 对象由 Web 服务器创建，Servlet 方法由Web服务器调用。

HttpServletRequest：用于封装所有的请求数据
HttpServletResponse：用于封装所有的响应数据