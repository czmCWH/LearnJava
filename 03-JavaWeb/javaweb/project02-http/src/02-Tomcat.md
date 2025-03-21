# Tomcat

Tomcat 是一个开源免费的**轻量级 Web服务器**，是Apache软件基金会的核心项目，支持 Servlet/JSP 少量 JavaEE(JavaEE 的原始名：JakartaEE) 规范。

JavaEE：Java Enterprise Edition, Java 企业版。指 Java 企业级开发的技术规范总和。包含13项技术规范：JDBC、JNDI、EJB、RMI、JSP、Servlet、XML、JMS、Java IDL、JTS、JTA、JavaMail、JAF。

**Tomcat 也被称为 Web容器、Servlet容器。** Servlet 程序需要依赖于 Tomcat 才能运行。
官网：<https://tomcat.apache.org/>。

## 一、Tomcat 的安装

<https://www.cnblogs.com/liyihua/p/14482401.html>

* Mac 上通过 Homebrew 安装 Tomcat，Tomcat 默认会被安装到 `/opt/homebrew/Cellar/` 目录下面：

```shell
# 搜索 tomcat 是否存在
$ brew search tomcat

# 安装 tomcat
$ brew install tomcat

# 检查 tomcat 是否安装成功
$ catalina -h
```

## 二、使用 Tomcat

### 启动/关闭 Tomcat 服务
分别开启2个终端窗口，运行如下命令：
``` shell
# 启动 Tomcat 服务
$ catalina run

# 关闭 Tomcat 服务
$ catalina stop
```
> Tomcat 的默认端口是 8080，如果运行成功可通过 `http://localhost:8080` 访问。
> 如果 Tomcat 启动窗口一闪而过，需要检查 `JAVA_HOME` 环境变量是否正确配置。

根据端口找进程ID

## 解决 Tomcat 控制台日志编码配置

终端运行 Tomcat 时打印输出乱码，找到 Tomcat 的安装目录下的文件 `/opt/homebrew/etc/tomcat/logging.properties `，修改如下内容为 `encoding = UTF-8`：

```properties 
java.util.logging.ConsoleHandler.encoding = UTF-8
```

### 修改 Tomcat 运行端口号(默认是 8080)

找到 Tomcat 的安装目录下的文件 `/opt/homebrew/etc/tomcat/server.xml `，修改如下内容：

```xml
<!--直接修改 port="8080" 即可-->
<Connector port="8080" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
```

### 在 Tomcat 部署 前端/后端 项目

* 部署项目：将项目放置在 webapps 目录下，即部署完成