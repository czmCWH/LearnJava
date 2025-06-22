# Servlet

`Servlet` 是运行在**Web服务器中的小型java程序**，是 Java 提供的一门动态 web 资源开发技术。通常通过 HTTP 协议接收和响应来自于客户端的请求。

Servlet 是 JavaEE 规范(即接口)之一，其实就是一个接口(定义 Servlet 需实现 Servlet 接口 或 继承 HttpServlet)，**并由 web服务器 运行 Servlet**。

# 实战案例

> 使用 Servlet 开发一个 javaWeb应用，并部署到 Tomcat 服务器上。通过浏览器发起请求 /hello 之后，给浏览器返回一个字符串"Hello Xxx'。

## 实现步骤：

1、创建一个 Maven 项目，添加一个 模块，设置打包方式为 war，添加 Servlet 依赖。

> 注意 servlet 依赖的选择
> Tomcat 10 以后，Servlet API 包名变为 jakarta.servlet，不是 javax.servlet。
> 本项目的 apache-tomcat-10.1.42 需选择 jakarta.servlet 依赖。

```xml
<!--导入 servlet 依赖-->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>4.0.1</version>
    <!-- 由于 `Tomcat` 内置了 `Servlet`，所以通过 `Maven` 打包项目时，需要排除 `Servlet` 依赖。-->
    <scope>provided</scope>
</dependency>

<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>6.0.0</version>
</dependency>
```
3、开始代码开发

`HttpServletRequest`：用于封装所有的请求数据；
`HttpServletResponse`：用于封装所有的响应数据；

* Servlet 对象是由谁创建的，Servlet 的 doGet 方法是由谁调用的？
Servlet 对象由 Web 服务器（即 Tomcat 服务器）创建，Servlet 方法由 Web 服务器调用。

4、项目部署
* 使用 `IDEA` 连接 `Tomcat` 服务器，便于启动运行项目，详细见 `/img/03-IDEA连接Tomcat服务器`；
* 在浏览器中通过访问项目；

