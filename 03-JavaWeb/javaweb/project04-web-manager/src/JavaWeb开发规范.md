# JavaWeb 开发规范

前端项目部署到 nginx 服务器；后端项目部署到 tomcat 服务器。

## 前后端分离开发

## Restful 风格

REST(REpresentational State Transfer)，表述性状态转换，它是一种软件架构风格。
REST风格使用 URL定位资源，**HTTP动词**（GET/POST/PUT/DELETE）描述操作。

注意：
REST是风格，是约定方式，约定不是规定，可以打破。
描述功能模块通常使用复数形式(加s)，表示此类资源，而非单个资源。如：users、books...


## 模拟网络请求工具

* Postman：使用简单，之前使用。

* Apifox：功能强大，现在常用。


## Apifox

介绍：Apifox 是一款集成了Api文档、Api调试、Api Mock、Api测试的一体化协作平台。
作用：接口文档管理、接口请求测试、Mock服务。
官网：<https://apifox.com/>


# 案例 - 部门信息查询

实现思路
1、加载并读取 dept.txt 文本中的数据。
2、解析文本中的数据，并将其封装为集合。
3、响应数据(json格式)。


# 统一响应结果对象
如果直接返回的数据类型多种多样，这会导致前后端不方便管理，难以维护。
因此，无论执行的是增删改查什么样的业务操作，需要都返回一个统一的响应结果对象。

响应结果对象
1、响应状态标识位
2、错误信息
3、响应数据

限制接口请求方式必须为GET，可以通过 @RequestMapping 注解的 method 属性指定，也可以使用 @GetMapping。
