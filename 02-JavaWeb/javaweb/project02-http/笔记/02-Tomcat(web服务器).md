# 一、Tomcat

`Tomcat` 是一个开源免费的**轻量级 Web服务器**，是Apache软件基金会的核心项目，支持 `Servlet/JSP` 少量 JavaEE 规范。

* JavaSE 标准版
* JavaME 小型版
* JavaEE 企业版
    `JavaEE` 的原始名：`JakartaEE`;
    `Java Enterprise Edition`, Java 企业版。指 Java 企业级开发的技术规范总和，
    包含13项技术规范：`JDBC、JNDI、EJB、RMI、JSP、Servlet、XML、JMS、Java IDL、JTS、JTA、JavaMail、JAF`，基本上不直接使用，都有对应的高级框架；

**Tomcat 也被称为 Web容器、Servlet容器。** Servlet 程序需要依赖于 Tomcat 才能运行。
官网：<https://tomcat.apache.org/>。

# 二、Tomcat 的基本使用

## 1、Tomcat 的安装与启动

* `Tomcat` 是依赖 java 环境的，需保证java环境正常
* `Tomcat` 服务的默认端口是 8080，如果运行成功可通过 `http://localhost:8080/` 访问默认页面。
* 参考 <https://www.cnblogs.com/liyihua/p/14482401.html>

### 通过 homebrew 工具安装

* Mac 上通过 Homebrew 安装 Tomcat，Tomcat 默认会被安装到 `/opt/homebrew/Cellar/` 目录下面：

```shell
# 搜索 tomcat 是否存在
$ brew search tomcat

# 安装 tomcat
$ brew install tomcat

# 检查 tomcat 是否安装成功
$ catalina version  # 查看 Tomcat 的版本
$ catalina -h  # 显示Tomcat帮助菜单

# 启动 Tomcat 服务
$ catalina run

# 关闭 Tomcat 服务
$ catalina stop
```

### 去 Tomcat 官网直接下载安装包

1、去官网下载安装包：<https://tomcat.apache.org/>，解压到 `/Library/` 目录下，即安装成功。
2、MacOS 上配置环境变量，并运行脚本

```shell
$ open ~/.zshrc

# Tomcat 环境变量配置
export CATALINA_HOME=/Library/apache-tomcat-10.1.42
export PATH="$CATALINA_HOME/bin:$PATH"
  
$ source ~/.zshrc

# 为 Tomcat 所有脚本添加可执行权限
$ cd /Library/apache-tomcat-10.1.42/bin
$ chmod +x *.sh 

# 接下来在任意终端可运行如下脚本（mac 上是 .sh 脚本；windows 上是 .bat 脚本）
$ catalina.sh version  # 应显示 Tomcat 版本信息
$ startup.sh           # 启动 Tomcat
$ shutdown.sh          # 停止 Tomcat
```

## 2、把 web 部署到 Tomcat 服务器上

直接把打包好的前端 Vue 项目文件 或者 javaWeb 项目 放到 `/Library/apache-tomcat-10.1.42/webapps/` 目录下即可。
然后重新启动 Tomcat 服务器。通过 <http://localhost:8080/> 访问即可。


## 3、Tomcat 其它配置

### 3.1、解决 Tomcat 控制台日志编码配置

终端运行 Tomcat 时打印输出乱码，找到 Tomcat 的安装目录下的文件 `/Library/apache-tomcat-10.1.42/conf/logging.properties `，
进行如下修改：UTF-8 或 GBK 保存，重新启动。

```properties 
java.util.logging.ConsoleHandler.encoding = UTF-8
```

### 3.2、修改 Tomcat 运行端口号(默认是 8080)
根据端口找进程ID
找到 Tomcat 的安装目录下的文件 `/Library/apache-tomcat-10.1.42/conf/server.xml `，修改如下内容：

```xml
<!--直接修改 port="8080" 为 9090 即可-->
<Connector port="9090" protocol="HTTP/1.1"
           connectionTimeout="20000"
           redirectPort="8443" />
```

> 如果需要多个端口运行，则要安装多个 Tomcat 实现。