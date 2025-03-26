# 一、数据库

数据库：DataBase(DB)，是存储和管理数据的仓库。

数据库管理系统：DataBase Management System(DBMS)，操纵和管理数据库的**大型软件**。

SQL：Structured Query Language，操作关系型数据库的编程语言，定义了一套操作关系型数据库统一标准。

## 数据库产品：

	`Oracle`：收费的大型数据库，0racle 公司的产品。如：银行、保险...
	
	`MySQL`：开源免费的中小型数据库。Sun 公司收购了 MySQL，Oracle 收购 Sun公司。
	
	`SQL Server`：MicroSoft 公司收费的中型的数据库。C#、.net等语言常使用。

	`PostgreSQL`：开源免费中小型的数据库。

	`DB2`：IBM公司的大型收费数据库产品。

	`SQLite`：嵌入式的微型数据库。如：作为Android内置数据库。

	`MariaDB`：开源免费的中小型的数据库。

# 二、MySQL 安装

MySQL 官方提供了2种不同的版本：

1、商业版(MySQL Enterprise Edition)
* 收费，可以使用30天。
* 官方提供技术支持。

2、社区版(MySQL Community Server)
* 免费。
* MySQL官方不提供技术支持。
* 企业中基本使用这种。
* 下载地址：<https://dev.mysql.com/downloads/mysql/>

## 1、Mac 上安装 MySQL
 
1、Mac 上直接下载安装包双击安装即可，中间需要配置 管理员账号密码；
2、安装完成后，【系统偏好设置】> 【MySQL】> 【Configuration】> 复制 `Base Directory` 通常为：`/usr/local/mysql`
3、配置 Mac 编辑Shell配置文件：`export PATH="/usr/local/mysql/bin:$PATH"`


```shell
# 登陆 mysql
$ mysql -u[用户名] -p[密码]

# 退出登录
$ exit
$ quit
```

```shell
# root 管理员用户登录
# 方式1
$ mysql -uroot -p123
# 方式2  
$ mysql -u root -p
# 输入密码登录成功

# 修改管理员密码，账号：root，修改密码为：123
$ mysqladmin -u root -p password 123
# 输入原老密码...
```

```shell
# 查看 mysql服务是否在运行
$ mysqladmin -u root -p ping

# 注册MySQL服务，首次安装时windows上执行
$ mysqld -install

# 启动/停止 mysql 服务 --- net 命令不兼容 macOS
# Mac端通过：【系统偏好设置】 → 【MySQL‌】
$ net start mysql
$ net stop mysql
```

## 2、连接远程 MySQL

```shell
mysql -u[用户名] -p[密码] -h[数据库服务器IP地址] -P[端口号：默认3306]
```

## 3、MySQL 客户端工具 - 图形化工具

常见的工具有：`SQLyog`、`Navicat`、`IntelliJ IDEA `、`DataGrip`

`DataGrip` 是 `JetBrains` 旗下的一款数据库管理工具，是管理和开发 MySQL、Oracle、PostgreSQL 的理想解决方案。
官网：<https://www.jetbrains.com/zh-cn/datagrip/>

开发中使用 `IDEA` 连接数据库即可。

# 三、MySQL数据模型

关系型数据库(RDBMS)：建立在关系模型基础上，由多张相互连接的二维表组成的数据库。

特点：

* 使用表存储数据，格式统一，便于维护。
* 使用SQL语言操作，标准统一，使用方便，可用于复杂查询。

```shell
# 查看当前的数据库
mysql> show databases
```

使用数据库过程：
**先登录 MySQL > 创建数据库 > 创建表 > 在表中记录数据**